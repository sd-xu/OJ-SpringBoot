package com.coding.oj.judge;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.coding.oj.common.exception.SystemError;
import com.coding.oj.judge.entity.JudgeDTO;
import com.coding.oj.judge.entity.JudgeGlobalDTO;
import com.coding.oj.judge.entity.LanguageConfig;
import com.coding.oj.judge.task.DefaultJudge;
import com.coding.oj.pojo.dto.TestJudgeReq;
import com.coding.oj.pojo.dto.TestJudgeRes;
import com.coding.oj.pojo.entity.Problem;
import com.coding.oj.utils.Constants;
import com.coding.oj.utils.JudgeUtils;
import com.coding.oj.utils.ThreadPoolUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description: 判题流程解耦重构，该类负责输入数据进入程序进行测评
 */
@Component
public class JudgeRun {

    @Resource
    private DefaultJudge defaultJudge;

    @Resource
    private LanguageConfigLoader languageConfigLoader;

    public List<JSONObject> judgeAllCase(Long submitId,
                                         Problem problem,
                                         String judgeLanguage,
                                         String testCasesDir,
                                         JSONObject testCasesInfo,
                                         String userFileId,
                                         String userFileContent,
                                         Boolean getUserOutput,
                                         String judgeCaseMode)
            throws SystemError, ExecutionException, InterruptedException {

        if (testCasesInfo == null) {
            throw new SystemError("The evaluation data of the problem does not exist", null, null);
        }

        JSONArray testcaseList = (JSONArray) testCasesInfo.get("testCases"); // key-value是什么时候加进去的

        // 默认给题目限制时间+200ms用来测评
        Long testTime = (long) problem.getTimeLimit() + 200;

        // 只有普通评测
        Constants.JudgeMode judgeMode = Constants.JudgeMode.DEFAULT;

        // 用户输出的文件夹
        String runDir = Constants.JudgeDir.RUN_WORKPLACE_DIR.getContent() + File.separator + submitId;

        LanguageConfig runConfig = languageConfigLoader.getLanguageConfigByName(judgeLanguage);

        // DefaultJudge
        final AbstractJudge abstractJudge = getAbstractJudge(judgeMode);

        JudgeGlobalDTO judgeGlobalDTO = JudgeGlobalDTO.builder()
                .problemId(problem.getId())
                .judgeMode(judgeMode)
                .userFileId(userFileId)
                .userFileContent(userFileContent)
                .runDir(runDir)
                .testTime(testTime)
                .maxMemory((long) problem.getMemoryLimit())
                .maxTime((long) problem.getTimeLimit())
                .maxStack(problem.getStackLimit())
                .testCaseInfo(testCasesInfo)
                .runConfig(runConfig)
                .needUserOutputFile(getUserOutput)
                .removeEOLBlank(false)
                .build();

        return defaultJudgeAllCase(testcaseList, testCasesDir, judgeGlobalDTO, abstractJudge);
    }

    /**
     * 默认会评测全部的测试点数据
     * @param testcaseList
     * @param testCasesDir
     * @param judgeGlobalDTO
     * @param abstractJudge
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private List<JSONObject> defaultJudgeAllCase(JSONArray testcaseList,
                                                 String testCasesDir,
                                                 JudgeGlobalDTO judgeGlobalDTO,
                                                 AbstractJudge abstractJudge) throws ExecutionException, InterruptedException {
        List<FutureTask<JSONObject>> futureTasks = new ArrayList<>();
        for (int index = 0; index < testcaseList.size(); index++) {
            JSONObject testcase = (JSONObject) testcaseList.get(index);
            // 将每个需要测试的线程任务加入任务列表中
            final int testCaseId = index + 1;
            // 输入文件名
            final String inputFileName = testcase.getStr("inputName"); // 这个key对吗 ?
            // 输出文件名
            final String outputFileName = testcase.getStr("outputName");
            // 题目数据的输入文件的路径
            final String testCaseInputPath = testCasesDir + File.separator + inputFileName;
            // 题目数据的输出文件的路径
            final String testCaseOutputPath = testCasesDir + File.separator + outputFileName;
            // 数据库表的测试样例id
            final Long caseId = testcase.getLong("caseId", null);
            // 该测试点的满分
            final Integer score = testcase.getInt("score", 0);
            // 该测试点的分组（用于subtask）
            final Integer groupNum = testcase.getInt("groupNum", 1);

            final Long maxOutputSize = Math.max(testcase.getLong("outputSize", 0L) * 2, 32 * 1024 * 1024L);

            JudgeDTO judgeDTO = JudgeDTO.builder()
                    .testCaseNum(testCaseId)
                    .testCaseInputFileName(inputFileName)
                    .testCaseInputPath(testCaseInputPath)
                    .testCaseOutputFileName(outputFileName)
                    .testCaseOutputPath(testCaseOutputPath)
                    .testCaseInputContent(testcase.getStr("inputContent"))
                    .maxOutputSize(maxOutputSize)
                    .score(score)
                    .build();

            futureTasks.add(new FutureTask<>(() -> {
                JSONObject result = abstractJudge.judge(judgeDTO, judgeGlobalDTO); // 判断部分, 看这里 !!
                result.set("caseId", caseId);
                result.set("score", judgeDTO.getScore());
                result.set("inputFileName", judgeDTO.getTestCaseInputFileName());
                result.set("outputFileName", judgeDTO.getTestCaseOutputFileName());
                result.set("groupNum", groupNum);
                result.set("seq", testCaseId);
                return result;
            }));

        }
        return SubmitBatchTask2ThreadPool(futureTasks); // 这里提交线程池干嘛 ?
    }

    private AbstractJudge getAbstractJudge(Constants.JudgeMode judgeMode) {
        switch (judgeMode) {
            case DEFAULT:
                return defaultJudge;
            default:
                throw new RuntimeException("The problem judge mode is error:" + judgeMode);
        }
    }

    private List<JSONObject> SubmitBatchTask2ThreadPool(List<FutureTask<JSONObject>> futureTasks)
            throws InterruptedException, ExecutionException {
        // 提交到线程池进行执行
        for (FutureTask<JSONObject> futureTask : futureTasks) {
            ThreadPoolUtils.getInstance().getThreadPool().submit(futureTask);
        }
        List<JSONObject> result = new LinkedList<>();
        while (futureTasks.size() > 0) {
            Iterator<FutureTask<JSONObject>> iterable = futureTasks.iterator();
            // 遍历一遍
            while (iterable.hasNext()) {
                FutureTask<JSONObject> future = iterable.next();
                if (future.isDone() && !future.isCancelled()) {
                    // 获取线程返回结果
                    JSONObject tmp = future.get();
                    result.add(tmp);
                    // 任务完成移除任务
                    iterable.remove();
                } else {
                    Thread.sleep(10); // 避免CPU高速运转，这里休息10毫秒
                }
            }
        }
        return result;
    }

}