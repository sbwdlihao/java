package com.lihao.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sbwdlihao on 6/28/16.
 */
public class ParamTest {

  @Test
  public void testPrimitive() {
    char c = 'a';
    Assert.assertEquals('a', c);
    Assert.assertEquals('b', changeChar(c));
    Assert.assertEquals('a', c);
  }

  @Test
  public void testString() {
    String s = "abc";
    Assert.assertEquals("abc", s);
    Assert.assertEquals("123", changeString(s));
    Assert.assertEquals("abc", s);
  }

  @Test
  public void testArray() {
    char[] chars = new char[1];
    chars[0] = 'a';
    Assert.assertEquals('a', chars[0]);
    Assert.assertEquals('b', changeArray(chars)[0]);
    Assert.assertEquals('b', chars[0]);
  }

  private char changeChar(char c) {
    c = 'b';
    return c;
  }

  private String changeString(String s) {
    s = "123";
    return s;
  }

  private char[] changeArray(char[] chars) {
    chars[0] = 'b';
    return chars;
  }
}
