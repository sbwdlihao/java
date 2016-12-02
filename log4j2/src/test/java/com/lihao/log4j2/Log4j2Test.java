package com.lihao.log4j2;

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.message.StructuredDataMessage;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by sbwdlihao on 01/12/2016.
 */
public class Log4j2Test {

    @Test
    public void TestNoConfiguration() {
        Logger logger = LogManager.getLogger();

        // 使用默认configuration的情况下，获取的logger是rootLogger，而rootLogger默认的level是error，输出是console
        Assert.assertEquals(Level.ERROR, logger.getLevel());

        // console上不显示
        logger.info("this is a info message");

        // 17:27:28.108 [main] ERROR com.lihao.log4j2.Log4j2Test - this is an error message
        logger.error("this is an error message");

        // console上会输出提示信息，提醒当前使用的是默认配置
        // ERROR StatusLogger No log4j2 configuration file found. Using default configuration: logging only errors to the console.
    }

    @Test
    public void TestConfigurationStatus() {
        System.setProperty("log4j.configurationFile", "configuration-status-test.xml");
        Logger logger = LogManager.getLogger();
        // console上不显示
        logger.info("this is a info message");

        // status logger上会输出log4j2内部debug信息
//        2016-12-01 18:44:18,764 main DEBUG Initializing configuration org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration@3930015a
//        2016-12-01 18:44:18,784 main DEBUG Installed script engines
//        2016-12-01 18:44:19,107 main DEBUG Oracle Nashorn Version: 1.8.0_112, Language: ECMAScript, Threading: Not Thread Safe, Compile: true, Names: {nashorn, Nashorn, js, JS, JavaScript, javascript, ECMAScript, ecmascript}
//        2016-12-01 18:44:19,176 main DEBUG Took 0.064673 seconds to load 190 plugins from sun.misc.Launcher$AppClassLoader@5c647e05
//        2016-12-01 18:44:19,178 main DEBUG PluginManager 'Core' found 107 plugins
//        2016-12-01 18:44:19,178 main DEBUG PluginManager 'Level' found 0 plugins
//        2016-12-01 18:44:19,182 main DEBUG Log4j2 ConfigurationScheduler: No scheduled items
//        。。。
    }

    @Test
    public void TestPatternLayout() {
        System.setProperty("log4j.configurationFile", "configuration-pattern-test.xml");
        Logger logger = LogManager.getLogger();

        // 使用 try-with-resources，会自动清除thread context map
        try (final CloseableThreadContext.Instance ignored = CloseableThreadContext.put("userId", "BC13234")) {
            Marker ID = MarkerManager.getMarker("ID");
            logger.info(ID, "this is a info message");
        }
    }

    @Test
    public void TestApi() {
        System.setProperty("log4j.configurationFile", "configuration-default-test.xml");
        Logger logger = LogManager.getLogger();
        // 07:57:47.590 [main] TRACE com.lihao.log4j2.Log4j2Test - Enter params(arg0, arg1)
        logger.entry("arg0", "arg1");
        // 07:46:08.060 [main] INFO  com.lihao.log4j2.Log4j2Test - this is a info message, uuid f8911d21-a0f3-4b0e-9ac3-ff28974e3745
        logger.info("this is a info message, uuid {}", UUID.randomUUID());
        // 如果root logger的level是INFO则console上不显示
        logger.debug("this is a debug message, uuid {}", UUID.randomUUID());
        // 07:47:35.775 [main] INFO  com.lihao.log4j2.Log4j2Test - this is a info message, uuid = 12
        logger.printf(Level.INFO, "this is a info message, uuid = %d", 12);

        try{
            throw new Exception("this is an exception");
        } catch (Exception e) {
            // 输出trace stack
            logger.catching(e);
        }
        // 07:57:47.607 [main] TRACE com.lihao.log4j2.Log4j2Test - Exit
        logger.traceExit();

        // 10:10:31.841 [main] OFF   EventLogger - event_test [e0 ] this is an event message
        StructuredDataMessage event = new StructuredDataMessage("e0", "this is an event message", "event_test");
        EventLogger.logEvent(event);
    }

    @Test
    public void TestLogger() {
        System.setProperty("log4j.configurationFile", "configuration-logger-test.xml");
        Logger logger = LogManager.getLogger();
        // 只会输出一次 10:46:46.199 [main] INFO  com.lihao.log4j2.Log4j2Test - this is a info message
        // 如果additivity设置成true，则会输出两次
        logger.info("this is a info message");
    }

    @Test
    public void TestAppender() {
        System.setProperty("log4j.configurationFile", "configuration-appender-test.xml");
        Logger logger = LogManager.getLogger();
        logger.info("this is a info message");
    }

    @Test
    public void TestFilter() {
        System.setProperty("log4j.configurationFile", "configuration-filter-test.xml");
        Logger logger = LogManager.getLogger();
        logger.info("this is a info message");
        logger.error("this is an error message");
        logger.fatal("this is a fatal message");
    }
}
