package cn.peng.slf4jLog4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author weiyupeng
 * @create 2021/11/28 11:44
 */
public class Slf4jLog4j2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jLog4j2.class);

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        // 去掉了 fatal，默认级别 error
        LOGGER.error("error");
        LOGGER.warn("warn");
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
