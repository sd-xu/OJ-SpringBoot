package com.coding.oj.utils;

public class Constants {

    /**
     * @Description 提交评测结果的状态码
     */
    public enum Judge {
        STATUS_NOT_SUBMITTED(-10, "Not Submitted", null),
        STATUS_SUBMITTED_UNKNOWN_RESULT(-5, "Submitted Unknown Result", null),
        STATUS_CANCELLED(-4, "Cancelled","ca"),
        STATUS_PRESENTATION_ERROR(-3, "Presentation Error", "pe"),
        STATUS_COMPILE_ERROR(-2, "Compile Error", "ce"),
        STATUS_WRONG_ANSWER(-1, "Wrong Answer", "wa"),
        STATUS_ACCEPTED(0, "Accepted", "ac"),
        STATUS_TIME_LIMIT_EXCEEDED(1, "Time Limit Exceeded", "tle"),
        STATUS_MEMORY_LIMIT_EXCEEDED(2, "Memory Limit Exceeded", "mle"),
        STATUS_RUNTIME_ERROR(3, "Runtime Error", "re"),
        STATUS_SYSTEM_ERROR(4, "System Error", "se"),
        STATUS_PENDING(5, "Pending", null),
        STATUS_COMPILING(6, "Compiling", null),
        STATUS_JUDGING(7, "Judging", null),
        STATUS_PARTIAL_ACCEPTED(8, "Partial Accepted", "pa"),
        STATUS_SUBMITTING(9, "Submitting", null),
        STATUS_SUBMITTED_FAILED(10, "Submitted Failed", null),
        STATUS_NULL(15, "No Status", null),
        JUDGE_SERVER_SUBMIT_PREFIX(-1002, "Judge SubmitId-ServerId:", null);

        private Judge(Integer status, String name, String columnName) {
            this.status = status;
            this.name = name;
            this.columnName = columnName;
        }

        private final Integer status;
        private final String name;
        private final String columnName;

        public Integer getStatus() {
            return status;
        }

        public String getName() {
            return name;
        }

        public String getColumnName() {
            return columnName;
        }
    }

    /**
     * 等待判题的redis队列
     * @Since 2021/12/22
     */
    public enum Queue {
        CONTEST_JUDGE_WAITING("Contest_Waiting_Handle_Queue"),
        GENERAL_JUDGE_WAITING("General_Waiting_Handle_Queue"),
        TEST_JUDGE_WAITING("Test_Judge_Waiting_Handle_Queue"),
        CONTEST_REMOTE_JUDGE_WAITING_HANDLE("Contest_Remote_Waiting_Handle_Queue"),
        GENERAL_REMOTE_JUDGE_WAITING_HANDLE("General_Remote_Waiting_Handle_Queue");

        private Queue(String name) {
            this.name = name;
        }
        private final String name;
        public String getName() {
            return name;
        }
    }

    public enum JudgeMode {
        DEFAULT("default"),

        SPJ("spj"),

        INTERACTIVE("interactive");

        private final String mode;

        JudgeMode(String mode) {
            this.mode = mode;
        }

        public String getMode() {
            return mode;
        }

        public static JudgeMode getJudgeMode(String mode) {
            for (JudgeMode judgeMode : JudgeMode.values()) {
                if (judgeMode.getMode().equals(mode)) {
                    return judgeMode;
                }
            }
            return null;
        }
    }

    public enum JudgeCaseMode {
        DEFAULT("default"),
        SUBTASK_LOWEST("subtask_lowest"),
        SUBTASK_AVERAGE("subtask_average"),
        ERGODIC_WITHOUT_ERROR("ergodic_without_error");

        private final String mode;

        JudgeCaseMode(String mode) {
            this.mode = mode;
        }

        public String getMode() {
            return mode;
        }


        public static JudgeCaseMode getJudgeCaseMode(String mode) {
            for (JudgeCaseMode judgeCaseMode : JudgeCaseMode.values()) {
                if (judgeCaseMode.getMode().equals(mode)) {
                    return judgeCaseMode;
                }
            }
            return DEFAULT;
        }
    }

    public enum JudgeDir {

        RUN_WORKPLACE_DIR("/judge/run"),

        TEST_CASE_DIR("/judge/test_case"),

        SPJ_WORKPLACE_DIR("/judge/spj"),

        INTERACTIVE_WORKPLACE_DIR("/judge/interactive"),

        TMPFS_DIR("/w");


        private final String content;

        JudgeDir(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    public enum TaskType {
        /**
         * 自身评测
         */
        JUDGE("/judge"),
        /**
         * 远程评测
         */
        REMOTE_JUDGE("/remote-judge"),
        /**
         * 在线调试
         */
        TEST_JUDGE("/test-judge"),
        /**
         * 编译特判程序
         */
        COMPILE_SPJ("/compile-spj"),
        /**
         * 编译交互程序
         */
        COMPILE_INTERACTIVE("/compile-interactive");

        private final String path;

        TaskType(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

    }

}
