package com.coding.oj.judge.self;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.coding.oj.judge.AbstractReceiver;
import com.coding.oj.judge.Dispatcher;
import com.coding.oj.pojo.dto.ToJudgeDTO;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.dao.JudgeEntityService;
import com.coding.oj.utils.Constants;
import com.coding.oj.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j(topic = "oj")
public class JudgeReceiver extends AbstractReceiver {
    @Autowired
    private Dispatcher dispatcher;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JudgeEntityService judgeEntityService;

    public void processWaitingTask() {
        // 优先处理比赛的提交任务
        // 其次处理普通提交的提交任务
        // 最后处理在线调试的任务

        // 目前只完成普通提交
        handleWaitingTask(Constants.Queue.GENERAL_JUDGE_WAITING.getName());
    }

    @Override
    public String getTaskByRedis(String queue) {
        long size = redisUtils.lGetListSize(queue);
        if (size > 0) {
            return (String) redisUtils.lrPop(queue);
        } else {
            return null;
        }
    }

    @Override
    public void handleJudgeMsg(String taskStr, String queueName) {
        JSONObject task = JSONUtil.parseObj(taskStr);
        Long submitId = task.getLong("submitId");
        Judge judge = judgeEntityService.getBySubmitId(submitId);
        if (judge != null) {
            // 调度评测时发现该评测任务被取消，则结束评测
            if (!Objects.equals(judge.getStatus(), Constants.Judge.STATUS_CANCELLED.getStatus())) {
                String token = task.getStr("token");
                // 调用判题服务
                ToJudgeDTO toJudgeDTO = new ToJudgeDTO();
                toJudgeDTO.setJudge(judge);
                toJudgeDTO.setToken(token);
                dispatcher.dispatch(Constants.TaskType.JUDGE, toJudgeDTO);
            }
        }

        // 接着处理任务
        processWaitingTask();
    }
}
