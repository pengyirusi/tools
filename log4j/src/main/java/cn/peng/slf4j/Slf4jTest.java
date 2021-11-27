package cn.peng.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author weiyupeng
 * @create 2021/11/28 9:43
 */
public class Slf4jTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jTest.class);

    public static void main(String[] args) {
        test1();
    }

    /**
     * 日志输出
     */
    private static void test1() {
        LOGGER.error("error");
        LOGGER.warn("warn");
        // 默认级别 info
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");

        // 使用占位符
        String name = "weiyupeng";
        int age = 25;
        LOGGER.info("profile:{},{}", name, age);

        try {
            int i = 1/0;
        } catch (Exception e) {
            LOGGER.error("出现异常：", e);
        }
    }
}
