package com.coding.oj.dao.dispatcher;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.coding.oj.judge.self.JudgeDispatcher;
import com.coding.oj.judge.self.JudgeReceiver;
import com.coding.oj.utils.Constants;
import com.coding.oj.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j(topic = "oj")
public class JudgeDispatcherTest {

    @Autowired
    private JudgeDispatcher judgeDispatcher;

    @Autowired
    private JudgeReceiver judgeReceiver;

    @Autowired
    private RedisUtils redisUtils;

    private String judgeToken;

    @Test
    public void sendTask() {

        JSONObject task = new JSONObject();
        task.set("submitId", 2);
        task.set("token",judgeToken);
        task.set("pid", 2);  //其实就是把提交的id和pid放入到redis队列当中
        try {
            boolean isOk;
            isOk = redisUtils.llPush(Constants.Queue.GENERAL_JUDGE_WAITING.getName(), JSONUtil.toJsonStr(task));
            System.out.println(isOk);
            if (!isOk) {
                System.out.println("redis有点小错误");
            }
            judgeReceiver.handleWaitingTask(Constants.Queue.GENERAL_JUDGE_WAITING.getName());
        } catch (Exception e) {
            log.error("调用redis将判题纳入判题等待队列异常--------------->{}", e.getMessage());
        }
    }
}