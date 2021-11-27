package cn.peng.log4j2;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author weiyupeng
 * @create 2021/11/28 11:36
 */
public class Log4j2Test {
    private static final Logger LOGGER = LogManager.getLogger(Log4j2Test.class);
    public static void main(String[] args) {
        LOGGER.info("hello log4j2!");
        LOGGER.fatal("fatal");
        // 默认 error
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
        //输出包含一个错误信息：没有配置文件
        //ERROR StatusLogger No Log4j 2 configuration file found. Using default configuration (logging only errors to the console), or user programmatically provided configurations. Set system property 'log4j2.debug' to show Log4j 2 internal initialization logging. See https://logging.apache.org/log4j/2.x/manual/configuration.html for instructions on how to configure Log4j 2
        //13:34:43.459 [main] FATAL cn.peng.log4j2.Log4j2Test - fatal
        //13:34:43.461 [main] ERROR cn.peng.log4j2.Log4j2Test - error
    }
}
