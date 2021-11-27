package cn.peng.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;

/**
 * @author weiyupeng
 * @create 2021/11/27 19:20
 */
public class Log4jTest {
    private static final Logger LOGGER = Logger.getLogger(Log4jTest.class);
    public static void main(String[] args) {
        test4();
    }

    private static void test1() {
        LOGGER.info("hello log4j");

        /**
         * 日志级别
         * 常用 error warn info debug
         * 默认级别是 debug
         */
        // 严重错误，系统崩溃
        LOGGER.fatal("fatal");

        // 错误信息，不会影响系统运行
        LOGGER.error("error");
        // 警告，可能会发生问题
        LOGGER.warn("warn");
        // 运行信息，数据连接、网络连接、IO操作等
        LOGGER.info("info");
        // 调试信息，开发中使用，log4j默认级别
        LOGGER.debug("debug");

        // 追踪信息，记录程序所有的流程信息
        LOGGER.trace("trace");

        //输出：得到 2 个 WARN
        //log4j:WARN No appenders could be found for logger (cn.peng.log4j.Log4jTest).
        //log4j:WARN Please initialize the log4j system properly.
        //原因：没有初始化配置
    }

    private static void test2() {
        // 初始化配置信息
        BasicConfigurator.configure();

        test1();
    }

    private static void test3() {
        // 开启 log4j 内置日志记录
        LogLog.setInternalDebugging(true);

        test2();
    }

    /**
     * 模拟大量日志，文件分块
     */
    private static void test4() {
        for (int i = 0; i < 10000; i++) {
            test1();
        }
    }
}
