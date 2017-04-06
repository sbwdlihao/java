package com.lihao.httpclient;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
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
    CredentialsProvider credsProvider = new BasicCredentialsProvider();
    credsProvider.setCredentials(new AuthScope("somehost", AuthScope.ANY_PORT), new UsernamePasswordCredentials("u1", "p1"));
    HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credsProvider).build();

  }
}
