package com.lihao.httpclient;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by sbwdlihao on 7/9/16.
 */
public class HttpProtocolProcessorTest {

  @Test
  public void test0() throws IOException, HttpException {
    HttpProcessor httpProcessor = HttpProcessorBuilder.create()
            .add(new RequestContent())
            .add(new RequestTargetHost())
            .add(new RequestConnControl())
            .add(new RequestUserAgent("MyAgent-HTTP/1.1"))
            .build();

    HttpCoreContext httpCoreContext = HttpCoreContext.create();
    HttpRequest httpRequest = new BasicHttpRequest("GET", "/", HttpVersion.HTTP_0_9);
    httpProcessor.process(httpRequest, httpCoreContext);
  }
}
