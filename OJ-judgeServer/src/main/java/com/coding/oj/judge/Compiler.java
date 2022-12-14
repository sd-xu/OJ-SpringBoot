package com.coding.oj.judge;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.coding.oj.common.exception.CompileError;
import com.coding.oj.common.exception.SubmitError;
import com.coding.oj.common.exception.SystemError;
import com.coding.oj.judge.entity.LanguageConfig;
import com.coding.oj.utils.Constants;
import com.coding.oj.utils.JudgeUtils;
import org.springframework.util.StringUtils;


import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 判题流程解耦重构, 该类只负责编译
 */
public class Compiler {

    public static String compile(LanguageConfig languageConfig, String code, String language)
            throws SystemError, CompileError, SubmitError {

        if (languageConfig == null) {
            throw new RuntimeException("Unsupported language " + language);
        }

        // 调用安全沙箱进行编译
        JSONArray result = SandboxRun.compile(languageConfig.getMaxCpuTime(),
                languageConfig.getMaxRealTime(),
                languageConfig.getMaxMemory(),
                256 * 1024 * 1024L,
                languageConfig.getSrcName(),
                languageConfig.getExeName(),
                parseCompileCommand(languageConfig.getCompileCommand()),
                languageConfig.getCompileEnvs(),
                code,
                true,
                false,
                null
        );
        JSONObject compileResult = (JSONObject) result.get(0);
        System.out.println(compileResult.getInt("status").intValue());
        if (compileResult.getInt("status").intValue() != Constants.Judge.STATUS_ACCEPTED.getStatus()) {

            throw new CompileError("Compile Error.", ((JSONObject) compileResult.get("files")).getStr("stdout"),
                    ((JSONObject) compileResult.get("files")).getStr("stderr"));
        }

        String fileId = ((JSONObject) compileResult.get("fileIds")).getStr(languageConfig.getExeName());
        if (StringUtils.isEmpty(fileId)) {
            throw new SubmitError("Executable file not found.", ((JSONObject) compileResult.get("files")).getStr("stdout"),
                    ((JSONObject) compileResult.get("files")).getStr("stderr"));
        }

        return fileId;
    }

    private static List<String> parseCompileCommand(String command) {
        return JudgeUtils.translateCommandline(command);
    }
}