package com.coding.oj.judge;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.coding.oj.common.exception.SystemError;
import com.coding.oj.dao.TestCaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.regex.Pattern;

/**
 * @Description: 判题流程解耦重构，该类只负责题目测试数据的检查与初始化
 */
@Component
public class ProblemTestCaseUtils {

    @Autowired
    private TestCaseEntityService testCaseEntityService;

    private final static Pattern EOL_PATTERN = Pattern.compile("[^\\S\\n]+(?=\\n)");

    // 获取指定题目的info数据
    public JSONObject loadTestCaseInfo(Long problemId, String testCasesDir) throws SystemError {
        if (FileUtil.exist(testCasesDir + File.separator + "info.json")) {
            FileReader fileReader = new FileReader(testCasesDir + File.separator + "info.json", CharsetUtil.UTF_8);
            String infoStr = fileReader.readString();
            JSONObject testcaseInfo = JSONUtil.parseObj(infoStr);
            return testcaseInfo;
        } else { // 若没有测试数据，则直接判系统错误
            //throw new SystemError("problemID:[" + problemId + "] test case has not found.", null, null);
            throw new SystemError(testCasesDir + File.separator + "info.json", null, null);
        }
    }

    // 去除每行末尾的空白符
    public static String rtrim(String value) {
        if (value == null) return null;
        return EOL_PATTERN.matcher(StrUtil.trimEnd(value)).replaceAll("");
    }

}