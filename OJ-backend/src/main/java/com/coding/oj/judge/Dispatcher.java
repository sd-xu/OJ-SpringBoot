package com.coding.oj.judge;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.coding.oj.common.result.CommonResult;
import com.coding.oj.common.result.ResultStatus;
import com.coding.oj.pojo.dto.ToJudgeDTO;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.pojo.entity.JudgeServer;
import com.coding.oj.dao.JudgeServerService;
import com.coding.oj.dao.JudgeEntityService;
import com.coding.oj.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 分发调用判题机执行任务
 */
@Component
@Slf4j(topic = "oj")
public class Dispatcher {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JudgeEntityService judgeEntityService;

    @Autowired
    private JudgeServerService judgeServerService;

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20);

    private static final Map<String, Future> futureTaskMap = new ConcurrentHashMap<>(20);

    // 每个提交任务尝试50次失败则判为提交失败
    protected static final Integer maxTryNum = 50; // 300

    public CommonResult dispatch(Constants.TaskType taskType, Object data) {
        switch (taskType) {
            case JUDGE:
                defaultJudge((ToJudgeDTO) data, taskType.getPath());
                break;
            default:
                throw new IllegalArgumentException("判题机不支持此调用类型");
        }
        return null;
    }

    /**
     * 普通评测
     * @param data
     * @param path
     */
    public void defaultJudge(ToJudgeDTO data, String path) {

        Long submitId = data.getJudge().getSubmitId();
        AtomicInteger count = new AtomicInteger(0);
        String taskKey = UUID.randomUUID().toString() + submitId;

        Runnable getResultTask = () -> {
            if (count.get() > maxTryNum) {
                checkResult(null, submitId);
                releaseTaskThread(taskKey);
                return;
            }
            count.getAndIncrement();
            JudgeServer judgeServer = judgeServerService.selectById(1); // 只有本机自身的判题机
            if (judgeServer != null) { // 获取到判题机资源
                JSONObject result = null;
                try {
                    String url = "http://" + judgeServer.getUrl() + path;
                    result = restTemplate.postForObject(url, data, JSONObject.class);
                    System.out.println(result);
                } catch (Exception e) {
                    log.error("[Self Judge] Request the judge server [" + judgeServer.getUrl() + "] error -------------->", e); //这里有问题
                } finally {
                    checkResult(result, submitId);
                    releaseJudgeServer(judgeServer.getId());
                    releaseTaskThread(taskKey);
                }
            }
        };
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleWithFixedDelay(getResultTask, 0, 2, TimeUnit.SECONDS);
        futureTaskMap.put(taskKey, scheduledFuture);
    }

    private void checkResult(JSONObject result, Long submitId) {

        Judge judge = new Judge();
        if (result == null) { // 调用失败
            judge.setSubmitId(submitId);
            judge.setStatus(Constants.Judge.STATUS_SUBMITTED_FAILED.getStatus());
            judge.setErrorMessage("Failed to connect the judgeServer. Please resubmit this submission again!");
            judgeEntityService.updateById(judge);
        } else {
            if (result.getInteger("status") != ResultStatus.SUCCESS.getStatus()) { // 如果是结果码不是200 说明调用有错误
                // 判为系统错误
                judge.setStatus(Constants.Judge.STATUS_SYSTEM_ERROR.getStatus());
                judge.setErrorMessage(result.getString("msg"));
                judgeEntityService.updateById(judge);
            }
        }

    }

    /**
     * 成功与否，皆需移除任务，释放线程
     * @param taskKey
     */
    private void releaseTaskThread(String taskKey) {
        Future future = futureTaskMap.get(taskKey);
        if (future != null) {
            boolean isCanceled = future.cancel(true);
            if (isCanceled) {
                futureTaskMap.remove(taskKey);
            }
        }
    }

    /**
     * 释放评测机资源
     * @param judgeServerId
     */
    public void releaseJudgeServer(Integer judgeServerId) {
        boolean isOk = judgeServerService.release(judgeServerId);
        if (!isOk) { // 重试八次
            tryAgainUpdateJudge(judgeServerId);
        }
    }

    public void tryAgainUpdateJudge(Integer judgeServerId) {
        for (int i = 0; i < 8; i++) {
            boolean success = judgeServerService.release(judgeServerId);
            if (success) {
                return;
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


