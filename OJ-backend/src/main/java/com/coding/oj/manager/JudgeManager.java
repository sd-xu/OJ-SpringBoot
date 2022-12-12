package com.coding.oj.manager;

import com.coding.oj.common.exception.MyException;
import com.coding.oj.common.exception.StatusForbiddenException;
import com.coding.oj.common.result.ResultStatus;
import com.coding.oj.judge.self.JudgeDispatcher;
import com.coding.oj.pojo.dto.SubmitJudgeDTO;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.service.JudgeService;

import com.coding.oj.service.ProblemService;
import com.coding.oj.utils.Constants;
import com.coding.oj.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;


@Component
public class JudgeManager {
    @Autowired
    private JudgeDispatcher judgeDispatcher;

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private ProblemService problemService;


    /**
     * @Description 核心方法 判题通过openfeign调用判题系统服务
     */
    public Judge submitProblemJudge(SubmitJudgeDTO judgeDto) throws StatusForbiddenException {
        // 目前先实现普通提交, 普通评测

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        // 将提交先写入数据库，准备调用判题服务器
        Judge judge = new Judge();
        judge.setPid(judgeDto.getPid());
        judge.setUid(judgeDto.getUid());
        judge.setUsername(judgeDto.getUsername());
        judge.setLid(judgeDto.getLid());
        judge.setCode(judgeDto.getCode());
        judge.setLength(judgeDto.getCode().length());
        judge.setSubmitTime(new Date());
        judge.setShare(false); // 默认设置代码为单独自己可见
        judge.setStatus(Constants.Judge.STATUS_PENDING.getStatus()); // 开始进入判题队列
        judge.setIp(IpUtils.getUserIpAddr(request));

        if (problemService.selectProblemById(judge.getPid()) == null) {
            throw new StatusForbiddenException("错误！当前题目已不存在，不可提交！");
        }
        judgeService.addJudge(judge);

        // 将提交加入任务队列
        judgeDispatcher.sendTask(judge.getSubmitId(), judge.getPid());

        return judge;
    }

}
