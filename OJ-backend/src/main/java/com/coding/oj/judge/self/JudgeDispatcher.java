package com.coding.oj.judge.self;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.dao.JudgeEntityService;
import com.coding.oj.utils.Constants;
import com.coding.oj.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 */
@Component
@Slf4j(topic = "oj")
public class JudgeDispatcher {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JudgeEntityService judgeService;

    @Autowired
    private JudgeReceiver judgeReceiver;

    public void sendTask(Long submitId, Long pid) {
        JSONObject task = new JSONObject();
        task.set("submitId", submitId);
        task.set("pid", pid);  // 将提交的id和pid放入到redis队列当中
        try {
            boolean isOk;
            isOk = redisUtils.llPush(Constants.Queue.GENERAL_JUDGE_WAITING.getName(), JSONUtil.toJsonStr(task));
            if (!isOk) {
                Judge errJudge = new Judge();
                errJudge.setSubmitId(submitId);
                errJudge.setPid(pid);
                errJudge.setStatus(Constants.Judge.STATUS_SUBMITTED_FAILED.getStatus());
                errJudge.setErrorMessage("Call Redis to push task error. Please try to submit again!");
                judgeService.updateById(errJudge);  // 更新status和错误信息
            }
            // 调用判题任务处理
            judgeReceiver.processWaitingTask();
        } catch (Exception e) {
            log.error("调用redis将判题纳入判题等待队列异常--------------->{}", e.getMessage());
        }
    }

}
