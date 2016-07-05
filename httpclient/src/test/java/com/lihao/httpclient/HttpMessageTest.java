package com.lihao.httpclient;

import org.apache.http.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sbwdlihao on 7/4/16.
 */
public class HttpMessageTest {

  @Test
  public void test0() {
    HttpRequest httpRequest = new BasicHttpRequest("GET", "/", HttpVersion.HTTP_1_1);
    Assert.assertEquals("GET", httpRequest.getRequestLine().getMethod());
    Assert.assertEquals("/", httpRequest.getRequestLine().getUri());
    Assert.assertEquals("HTTP/1.1", httpRequest.getProtocolVersion().toString());
    Assert.assertEquals("GET / HTTP/1.1", httpRequest.getRequestLine().toString());
  }

  @Test
  public void test1() {
    HttpResponse httpResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
    Assert.assertEquals("HTTP/1.1", httpResponse.getProtocolVersion().toString());
    Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    Assert.assertEquals("OK", httpResponse.getStatusLine().getReasonPhrase());
    Assert.assertEquals("HTTP/1.1 200 OK", httpResponse.getStatusLine().toString());
  }

  @Test
  public void test2() {
    HttpResponse httpResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
    httpResponse.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
    httpResponse.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
    Header h1 = httpResponse.getFirstHeader("Set-Cookie");
    Header h2 = httpResponse.getLastHeader("Set-Cookie");
    Assert.assertEquals("c1=a; path=/; domain=localhost", h1.getValue());
    Assert.assertEquals("c2=b; path=\"/\", c3=c; domain=\"localhost\"", h2.getValue());
    HeaderElement[] he1 = h1.getElements();
    Assert.assertEquals(1, he1.length);
    Assert.assertEquals("c1", he1[0].getName());
    Assert.assertEquals("a", he1[0].getValue());
    Assert.assertEquals(2, he1[0].getParameterCount());
    Assert.assertEquals("path", he1[0].getParameter(0).getName());
    Assert.assertEquals("/", he1[0].getParameter(0).getValue());
    Assert.assertEquals("domain", he1[0].getParameter(1).getName());
    Assert.assertEquals("localhost", he1[0].getParameter(1).getValue());
    HeaderElement[] he2 = h2.getElements();
    Assert.assertEquals(2, he2.length);
    Assert.assertEquals("c2", he2[0].getName());
    Assert.assertEquals("b", he2[0].getValue());
    Assert.assertEquals("c3", he2[1].getName());
    Assert.assertEquals("c", he2[1].getValue());
  }

  @Test
  public void test3() {
    StringEntity stringEntity = new StringEntity("important message", Consts.UTF_8);
    Assert.assertEquals("Content-Type", stringEntity.getContentType().getName());
    Assert.assertEquals("text/plain; charset=UTF-8", stringEntity.getContentType().getValue());
    Assert.assertEquals(1, stringEntity.getContentType().getElements().length);
    HeaderElement headerElement = stringEntity.getContentType().getElements()[0];
    Assert.assertEquals("text/plain", headerElement.getName());
    Assert.assertEquals(null, headerElement.getValue());
    Assert.assertEquals(1, headerElement.getParameterCount());
    NameValuePair nameValuePair = headerElement.getParameter(0);
    Assert.assertEquals("charset", nameValuePair.getName());
    Assert.assertEquals("UTF-8", nameValuePair.getValue());
  }
}
