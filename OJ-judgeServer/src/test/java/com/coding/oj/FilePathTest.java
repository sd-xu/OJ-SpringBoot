package com.coding.oj;

import cn.hutool.core.io.FileUtil;
import com.coding.oj.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FilePathTest {

    @Test
    public void test() {
        assertTrue(FileUtil.exist("test_case/problem_1001/info.json"));
    }

}
