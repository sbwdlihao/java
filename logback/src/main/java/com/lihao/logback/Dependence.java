package com.lihao.logback;

import com.lihao.json.JsonUtil;

/**
 * 测试自己有配置文件，且依赖也有配置文件的情况
 * 测试结果表明，logback只会加载1个配置文件，没有配置继承覆盖的设计
 *
 * @author bencong.lh
 * @version $Id: Dependence, v0.1 2017年07月19日 下午5:08 bencong.lh Exp $
 */

public class Dependence {

    public static void main(String[] args) {
        JsonUtil.testLog();

        // 如果logback/src/main/resources下面没有logback.xml，则输出，可见会使用json下面的logback
        //SLF4J: Class path contains multiple SLF4J bindings.
        //SLF4J: Found binding in [jar:file:/Users/bencong/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.2.3/7c4f3c474fb2c041d8028740440937705ebb473a/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        //SLF4J: Found binding in [jar:file:/Users/bencong/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-simple/1.7.25/8dacf9514f0c707cbbcdd6fd699e8940d42fb54e/slf4j-simple-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        //SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
        //SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
        //19:44:15.488 [main] DEBUG com.lihao.json.JsonUtil - debug msg from JsonUtil
        //DEBUG com.lihao.json.JsonUtil - debug msg from JsonUtil
        //19:44:15.492 [main] INFO  com.lihao.json.JsonUtil - info msg from JsonUtil
        //INFO  com.lihao.json.JsonUtil - info msg from JsonUtil
        //19:44:15.493 [main] WARN  com.lihao.json.JsonUtil - warn msg from JsonUtil
        //WARN  com.lihao.json.JsonUtil - warn msg from JsonUtil
        //19:44:15.493 [main] ERROR com.lihao.json.JsonUtil - error msg from JsonUtil
        //ERROR com.lihao.json.JsonUtil - error msg from JsonUtil
        //Disconnected from the target VM, address: '127.0.0.1:52148', transport: 'socket'

        // 如果有的话，只会加载logback下面的配置，不会加载json下面的配置
        //SLF4J: Class path contains multiple SLF4J bindings.
        //SLF4J: Found binding in [jar:file:/Users/bencong/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.2.3/7c4f3c474fb2c041d8028740440937705ebb473a/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        //SLF4J: Found binding in [jar:file:/Users/bencong/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-simple/1.7.25/8dacf9514f0c707cbbcdd6fd699e8940d42fb54e/slf4j-simple-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        //SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
        //SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
        //19:45:58,683 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback-test.xml]
        //19:45:58,683 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback.groovy]
        //19:45:58,684 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Found resource [logback.xml] at [file:/Users/bencong/IdeaProjects/java/logback/out/production/resources/logback.xml]
        //19:45:58,686 |-WARN in ch.qos.logback.classic.LoggerContext[default] - Resource [logback.xml] occurs multiple times on the classpath.
        //19:45:58,686 |-WARN in ch.qos.logback.classic.LoggerContext[default] - Resource [logback.xml] occurs at [file:/Users/bencong/IdeaProjects/java/json/out/production/resources/logback.xml]
        //19:45:58,686 |-WARN in ch.qos.logback.classic.LoggerContext[default] - Resource [logback.xml] occurs at [file:/Users/bencong/IdeaProjects/java/logback/out/production/resources/logback.xml]
        //19:45:58,795 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - debug attribute not set
        //19:45:58,797 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - About to instantiate appender of type [ch.qos.logback.core.ConsoleAppender]
        //19:45:58,810 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - Naming appender as [STDOUT]
        //19:45:58,817 |-INFO in ch.qos.logback.core.joran.action.NestedComplexPropertyIA - Assuming default type [ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
        //19:45:58,875 |-INFO in ch.qos.logback.classic.joran.action.RootLoggerAction - Setting level of ROOT logger to TRACE
        //19:45:58,875 |-INFO in ch.qos.logback.core.joran.action.AppenderRefAction - Attaching appender named [STDOUT] to Logger[ROOT]
        //19:45:58,876 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - End of configuration.
        //19:45:58,877 |-INFO in ch.qos.logback.classic.joran.JoranConfigurator@76b10754 - Registering current configuration as safe fallback point
        //
        //19:45:58.881 [main] TRACE com.lihao.json.JsonUtil - trace msg from JsonUtil
        //19:45:58.884 [main] DEBUG com.lihao.json.JsonUtil - debug msg from JsonUtil
        //19:45:58.884 [main] INFO  com.lihao.json.JsonUtil - info msg from JsonUtil
        //19:45:58.884 [main] WARN  com.lihao.json.JsonUtil - warn msg from JsonUtil
        //19:45:58.884 [main] ERROR com.lihao.json.JsonUtil - error msg from JsonUtil
        //Disconnected from the target VM, address: '127.0.0.1:53271', transport: 'socket'

        // 如果使用-Dlogback.configurationFile=/Users/bencong/IdeaProjects/java/json/src/main/resources/logback.xml
        // 那么就指定加载哪个配置
    }
}
