package com.lihao.lambda;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by sbwdlihao on 6/18/16.
 */
public class EncodeTest {

  @Test
  public void testChinese() throws UnsupportedEncodingException {
    String s = "我";
    byte[] bytes0 = s.getBytes("UTF-8"); // -26, -120, -111
    byte[] bytes1 = s.getBytes("ASCII"); // 63
    byte[] bytes2 = s.getBytes("ISO-8859-1"); // 63
    String s0 = new String(bytes0);
    String s1 = new String(bytes1, "ASCII");
    String s2 = new String(bytes2, "ISO-8859-1");
    System.out.println(s0); // 我
    System.out.println(s1); // ?
    System.out.println(s2); // ?
  }
}
