# setup
参考官方[文档](https://logback.qos.ch/setup.html)，一般只需要引入logback-classic，因为
logback-classic依赖logback-core和slf4j-api
```groovy
dependencies {
    compile group: 'ch.qos.logback', name: 'logback-classic', version: '1.2.3'
}
```
# 体系
logger按照名字形成字典树，根是root logger，除了根之外的logger节点都有父节点，比如java是java.util的父节点。
logger有自己的level(TRACE, DEBUG, INFO, WARN and ERROR)，如果自己没有设置，会集成父节点的level，直到根节点，根节点默认是debug。
logger发起请求（比如logger.info(...)）时，要想这个请求生效要求logger自己的level小于等于请求的level，如果logger的level是warn，那么
info请求就会被忽略。

# appender
每个logger可以有多个appender（console, files, remote socket servers, MySQL，JMS等），appender也被集成，如果root有console
那么所有的有效的logger请求至少都会发往console。通过L的logger请求会发往L的appender以及所有祖先的appender，这种叫累计性，可以禁止这种特性。
如果L的祖先P的additivity设置为false，那么L的logger请求会发往\[L\>...\>P\]到P为止。

在debug前先测试debug是否有效可以避免debug参数计算的性能损失
```java
if(logger.isDebugEnabled()) { 
  logger.debug("...");
}
```

# 性能
可以将root logger的level设置成OFF彻底的关掉日志，那么引入日志的开销就是日志函数的调用+参数的运算+level的比较。不要将logger代码放在
运行频率非常高的地方（及时关掉日志）。
往本地文件写大概花费9~12微妙，往数据库或者远程服务器上写是毫秒级。
logback的第一目标是性能，其次才是可靠性。

# 配置
* 先在classpath中找logback-test.xml
* 如果没有，再在classpath中找logback.groovy
* 如果没有，再在classpath中找logback.xml
* 如果没有，再找ch.qos.logback.classic.spi.Configurator的实现
* 如果没有，再使用默认的实现BasicConfigurator（直接输出到控制台）