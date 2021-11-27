package cn.peng.jul;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

/**
 * @author weiyupeng
 * @create 2021/11/27 16:29
 */
public class JULTest {

    private static final Logger LOGGER = Logger.getLogger("cn.peng.jul.JULTest");

    public static void main(String[] args) throws IOException {
        test5();
    }

    /**
     * 简单日志使用
      */
    private static void test1() {
        LOGGER.info("hello jul");

        LOGGER.log(Level.INFO, "info msg");

        String name = "weiyupeng";
        int age = 25;
        LOGGER.log(Level.INFO, "profile:{0},{1}", new Object[]{name, age});
    }

    /**
     * 日志级别
     */
    private static void test2() {
        LOGGER.severe("severe");
        LOGGER.warning("warning");
        // JUL 默认的级别 INFO
        LOGGER.info("info");
        LOGGER.config("config");
        LOGGER.fine("fine");
        LOGGER.finer("finer");
        LOGGER.finest("finest");

        //输出：
        //十一月 27, 2021 6:19:01 下午 cn.peng.jul.JULTest test2
        //严重: severe
        //十一月 27, 2021 6:19:01 下午 cn.peng.jul.JULTest test2
        //警告: warning
        //十一月 27, 2021 6:19:01 下午 cn.peng.jul.JULTest test2
        //信息: info
    }

    /**
     * 自定义配置日志级别
     */
    private static void test3() throws IOException {
        // 关闭默认配置
        LOGGER.setUseParentHandlers(false);

        // 控制台输出
        ConsoleHandler consoleHandler = new ConsoleHandler();

        SimpleFormatter simpleFormatter = new SimpleFormatter();

        // 关联
        consoleHandler.setFormatter(simpleFormatter);
        LOGGER.addHandler(consoleHandler);

        // 配置日志级别
        LOGGER.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        // 通过日志文件输出
        FileHandler fileHandler = new FileHandler("Z:\\IDEA Project\\tools\\log4j\\logs\\jul.log");
        fileHandler.setFormatter(simpleFormatter);
        fileHandler.setLevel(Level.INFO);
        LOGGER.addHandler(fileHandler);

        LOGGER.severe("severe");
        LOGGER.warning("warning");
        LOGGER.info("info");
        LOGGER.config("config");
        LOGGER.fine("fine");
        LOGGER.finer("finer");
        LOGGER.finest("finest");
    }

    /**
     * Logger 的父子关系
     */
    private static void test4() {
        Logger logger1 = Logger.getLogger("cn");
        Logger logger2 = Logger.getLogger("cn.peng");

        Logger parent = logger2.getParent();
        System.out.println(logger1 == parent);
        System.out.println(logger1.getParent());
        // 输出：java.util.logging.LogManager$RootLogger@6d6f6e28
        // 祖宗 Logger 对象
    }

    /**
     * 加载自定义配置文件
     */
    private static void test5() throws IOException {
        // 通过类加载器读取配置文件
        InputStream resourceAsStream = JULTest.class.getClassLoader().getResourceAsStream("logging.properties");

        // 创建 LogManager
        LogManager logManager = LogManager.getLogManager();

        // 加载配置
        logManager.readConfiguration(resourceAsStream);

        test2();
    }
}
