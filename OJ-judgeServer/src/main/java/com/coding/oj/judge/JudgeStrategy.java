package com.coding.oj.judge;

import cn.hutool.json.JSONObject;
import com.coding.oj.common.exception.CompileError;
import com.coding.oj.common.exception.SubmitError;
import com.coding.oj.common.exception.SystemError;
import com.coding.oj.dao.JudgeCaseEntityService;
import com.coding.oj.dao.JudgeEntityService;
import com.coding.oj.judge.entity.LanguageConfig;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.pojo.entity.JudgeCase;
import com.coding.oj.pojo.entity.Problem;
import com.coding.oj.utils.Constants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

@Slf4j(topic = "hoj")
@Component
public class JudgeStrategy {

    @Resource
    private JudgeEntityService judgeEntityService;

    @Resource
    private ProblemTestCaseUtils problemTestCaseUtils;

    @Resource
    private JudgeCaseEntityService judgeCaseEntityService;

    @Resource
    private LanguageConfigLoader languageConfigLoader;

    @Resource
    private JudgeRun judgeRun;

    public Map<String, Object> judge(Problem problem, Judge judge) {

        HashMap<String, Object> result = new HashMap<>();
        // 编译好的临时代码文件id
        String userFileId = null;
        try {
            // 对用户源代码进行编译 获取tmpfs中的fileId
            LanguageConfig languageConfig = languageConfigLoader.getLanguageConfigByName(judge.getLanguage());
            // 有的语言可能不支持编译, 目前有js、php不支持编译
            if (languageConfig.getCompileCommand() != null) {
                userFileId = Compiler.compile(languageConfig,
                        judge.getCode(),
                        judge.getLanguage());
            }

            // 测试数据文件所在文件夹
            String testCasesDir = Constants.JudgeDir.TEST_CASE_DIR.getContent() + File.separator + "problem_" + problem.getId();

            // 从文件中加载测试数据json
            JSONObject testCasesInfo = problemTestCaseUtils.loadTestCaseInfo(problem.getId(), testCasesDir);

            // 无需检查是否为spj或者interactive, 只有default

            // 更新状态为评测数据中
            judgeEntityService.updateStatus(Constants.Judge.STATUS_JUDGING.getStatus(), judge.getSubmitId());

            // 获取题目数据的评测模式(默认为default)
            String judgeCaseMode = testCasesInfo.getStr("judgeCaseMode", Constants.JudgeCaseMode.DEFAULT.getMode());

            // 开始测试每个测试点
            List<JSONObject> allCaseResultList = judgeRun.judgeAllCase(judge.getSubmitId(),
                    problem,
                    judge.getLanguage(),
                    testCasesDir,
                    testCasesInfo,
                    userFileId,
                    judge.getCode(),
                    false,
                    judgeCaseMode);

            // 对全部测试点结果进行评判, 获取最终评判结果
            return getJudgeInfo(allCaseResultList, problem, judge, judgeCaseMode);

        } catch (SystemError systemError) {
            result.put("code", Constants.Judge.STATUS_SYSTEM_ERROR.getStatus());
            result.put("errMsg", "Oops, something has gone wrong with the judgeServer. Please report this to administrator.");
            result.put("time", 0);
            result.put("memory", 0);
            log.error("[Judge] [System Error] Submit Id:[{}] Problem Id:[{}], Error:[{}]",
                    judge.getSubmitId(),
                    problem.getId(),
                    systemError);
        } catch (SubmitError submitError) {
            result.put("code", Constants.Judge.STATUS_SUBMITTED_FAILED.getStatus());
            result.put("errMsg", mergeNonEmptyStrings(submitError.getMessage(), submitError.getStdout(), submitError.getStderr()));
            result.put("time", 0);
            result.put("memory", 0);
            log.error("[Judge] [Submit Error] Submit Id:[{}] Problem Id:[{}], Error:[{}]",
                    judge.getSubmitId(),
                    problem.getId(),
                    submitError);
        } catch (CompileError compileError) {
            result.put("code", Constants.Judge.STATUS_COMPILE_ERROR.getStatus());
            result.put("errMsg", mergeNonEmptyStrings(compileError.getStdout(), compileError.getStderr()));
            result.put("time", 0);
            result.put("memory", 0);
        } catch (Exception e) {
            result.put("code", Constants.Judge.STATUS_SYSTEM_ERROR.getStatus());
            result.put("errMsg", "Oops, something has gone wrong with the judgeServer. Please report this to administrator.");
            result.put("time", 0);
            result.put("memory", 0);
            log.error("[Judge] [System Runtime Error] Submit Id:[{}] Problem Id:[{}], Error:[{}]",
                    judge.getSubmitId(),
                    problem.getId(),
                    e);
        } finally {
            // 删除tmpfs内存中的用户代码可执行文件
            if (!StringUtils.isEmpty(userFileId)) {
                SandboxRun.delFile(userFileId);
            }
        }
        return result;
    }

    // 进行最终测试结果的判断（除编译失败外的评测状态码和时间, 空间, OI题目的得分）
    public Map<String, Object> getJudgeInfo(List<JSONObject> testCaseResultList,
                                                Problem problem,
                                                Judge judge,
                                                String judgeCaseMode) {

        List<JSONObject> errorTestCaseList = new LinkedList<>();

        List<JudgeCase> allCaseResList = new LinkedList<>();

        // 记录所有测试点的结果
        testCaseResultList.forEach(jsonObject -> {
            Integer time = jsonObject.getLong("time").intValue();
            Integer memory = jsonObject.getLong("memory").intValue();
            Integer status = jsonObject.getInt("status");

            Long caseId = jsonObject.getLong("caseId", null);
            Integer groupNum = jsonObject.getInt("groupNum", null);
            Integer seq = jsonObject.getInt("seq", 0);
            String inputFileName = jsonObject.getStr("inputFileName");
            String outputFileName = jsonObject.getStr("outputFileName");
            String msg = jsonObject.getStr("errMsg");

            JudgeCase judgeCase = JudgeCase.builder()
                    .time(time)
                    .memory(memory)
                    .status(status)
                    .inputData(inputFileName)
                    .outputData(outputFileName)
                    .pid(problem.getId())
                    .uid(judge.getUid())
                    .caseId(caseId)
                    .seq(seq)
                    .groupNum(groupNum)
                    .mode(judgeCaseMode)
                    .submitId(judge.getSubmitId())
                    .build();

            if (!StringUtils.isEmpty(msg) && !Objects.equals(status, Constants.Judge.STATUS_COMPILE_ERROR.getStatus())) {
                judgeCase.setUserOutput(msg);
            }

            // 默认是ACM, 非OI
            if (!Objects.equals(status, Constants.Judge.STATUS_ACCEPTED.getStatus())) {
                errorTestCaseList.add(jsonObject);
            }

            allCaseResList.add(judgeCase);
        });

        // 更新到数据库
        boolean addCaseRes = judgeCaseEntityService.saveBatch(allCaseResList);
        if (!addCaseRes) {
            log.error("题号为：" + problem.getId() + "，提交id为：" + judge.getSubmitId() + "的各个测试数据点的结果更新到数据库操作失败");
        }

        // 获取判题的运行时间，运行空间，OI得分(无)
        HashMap<String, Object> result = (HashMap<String, Object>) computeResultInfo(allCaseResList);

        // 如果该题为ACM类型的题目，多个测试点全部正确则AC，否则取第一个错误的测试点的状态
        if (errorTestCaseList.isEmpty()) { // 全部测试点正确，则为AC
            result.put("code", Constants.Judge.STATUS_ACCEPTED.getStatus());
        } else {
            errorTestCaseList.sort(Comparator.comparingInt(jsonObject -> jsonObject.getInt("seq")));
            result.put("code", errorTestCaseList.get(0).getInt("status"));
            result.put("errMsg", errorTestCaseList.get(0).getStr("errMsg", ""));
        }
        return result;
    }

    // 获取判题的运行时间，运行空间，OI得分(无)
    public Map<String, Object> computeResultInfo(List<JudgeCase> allTestCaseResultList) {

        HashMap<String, Object> result = new HashMap<>();
        // 用时和内存占用保存为多个测试点中最长的
        allTestCaseResultList.stream().max(Comparator.comparing(JudgeCase::getTime))
                .ifPresent(t -> result.put("time", t.getTime()));

        allTestCaseResultList.stream().max(Comparator.comparing(JudgeCase::getMemory))
                .ifPresent(t -> result.put("memory", t.getMemory()));

        return result;
    }


    public String mergeNonEmptyStrings(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            if (!StringUtils.isEmpty(str)) {
                sb.append(str.substring(0, Math.min(1024 * 1024, str.length()))).append("\n");
            }
        }
        return sb.toString();
    }

}
