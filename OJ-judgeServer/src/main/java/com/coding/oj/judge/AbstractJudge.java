package com.coding.oj.judge;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.coding.oj.common.exception.SystemError;
import com.coding.oj.judge.entity.JudgeDTO;
import com.coding.oj.judge.entity.JudgeGlobalDTO;
import com.coding.oj.judge.entity.SandBoxRes;
import com.coding.oj.utils.Constants;
import com.coding.oj.utils.JudgeUtils;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public abstract class AbstractJudge {

    private final static Pattern EOL_PATTERN = Pattern.compile("[^\\S\\n]+(?=\\n)");

    public JSONObject judge(JudgeDTO judgeDTO, JudgeGlobalDTO judgeGlobalDTO) throws SystemError {

        JSONArray judgeResultList = judgeCase(judgeDTO, judgeGlobalDTO);

        switch (judgeGlobalDTO.getJudgeMode()) {
            case DEFAULT:
                return process(judgeDTO, judgeGlobalDTO, judgeResultList);
            default:
                throw new RuntimeException("The problem mode is error:" + judgeGlobalDTO.getJudgeMode());
        }

    }

    public abstract JSONArray judgeCase(JudgeDTO judgeDTO, JudgeGlobalDTO judgeGlobalDTO) throws SystemError;

    private JSONObject process(JudgeDTO judgeDTO, JudgeGlobalDTO judgeGlobalDTO, JSONArray judgeResultList) throws SystemError {
        JSONObject judgeResult = (JSONObject) judgeResultList.get(0);
        SandBoxRes sandBoxRes = SandBoxRes.builder()
                .stdout(((JSONObject) judgeResult.get("files")).getStr("stdout"))
                .stderr(((JSONObject) judgeResult.get("files")).getStr("stderr"))
                .time(judgeResult.getLong("time") / 1000000) //  ns->ms
                .memory(judgeResult.getLong("memory") / 1024) // b-->kb
                .exitCode(judgeResult.getInt("exitStatus"))
                .status(judgeResult.getInt("status"))
                .originalStatus(judgeResult.getStr("originalStatus"))
                .build();

        return checkResult(sandBoxRes, judgeDTO, judgeGlobalDTO);
    }

    public abstract JSONObject checkResult(SandBoxRes sandBoxRes, JudgeDTO judgeDTO, JudgeGlobalDTO judgeGlobalDTO) throws SystemError;

    protected static List<String> parseRunCommand(String command,
                                                  String testCaseInputName,
                                                  String userOutputName,
                                                  String testCaseOutputName) {

        if (testCaseInputName != null) {
            command = command.replace("{std_input}",
                    Constants.JudgeDir.TMPFS_DIR.getContent() + File.separator + testCaseInputName);
        }

        if (userOutputName != null) {
            command = command.replace("{user_output}",
                    Constants.JudgeDir.TMPFS_DIR.getContent() + File.separator + userOutputName);
        }

        if (userOutputName != null) {
            command = command.replace("{std_output}",
                    Constants.JudgeDir.TMPFS_DIR.getContent() + File.separator + testCaseOutputName);
        }

        return JudgeUtils.translateCommandline(command);
    }

    // 去除行末尾空白符
    protected String rtrim(String value) {
        if (value == null) return null;
        return EOL_PATTERN.matcher(StrUtil.trimEnd(value)).replaceAll("");
    }

}