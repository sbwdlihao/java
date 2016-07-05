package com.lihao.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sbwdlihao on 6/25/16.
 */
public class PrimitiveDateTypeTest {

  @Test
  public void testByte0() {
    Assert.assertTrue(Byte.MAX_VALUE == 127);
    Assert.assertTrue(Byte.MIN_VALUE == -128);
    Assert.assertEquals(1, Byte.BYTES);
  }

  @Test
  public void testByte1() {
    byte b = -12;
    char c0 = (char)b; // = 65536 - 12 = 65524
    char c1 = (char)(b & 0xff); // = 244
    Assert.assertNotEquals(c0, c1);
    Assert.assertEquals(65524, c0);
    Assert.assertEquals(244, c1);
  }

  @Test
  public void testShort0() {
    Assert.assertTrue(Short.MAX_VALUE == 32767);
    Assert.assertTrue(Short.MIN_VALUE == -32768);
    Assert.assertEquals(2, Short.BYTES);
  }

  @Test
  public void testInt0() {
    Assert.assertEquals(Integer.MAX_VALUE, (int)Math.pow(2, 31));
    Assert.assertEquals(Integer.MAX_VALUE, (int)Math.pow(2, 32));
    // false
    Assert.assertFalse(Integer.MAX_VALUE == (int)Math.pow(2, 31) - 1);
    Assert.assertFalse(Integer.MIN_VALUE == -(int)Math.pow(2, 31));
    // true
    Assert.assertTrue(Integer.MAX_VALUE == (int)(Math.pow(2, 31) - 1));
    Assert.assertTrue(Integer.MIN_VALUE == (int)-Math.pow(2, 31));
    Assert.assertEquals(4, Integer.BYTES);
  }

  @Test
  public void testFloat0() {
    Assert.assertEquals(4, Float.BYTES);
  }

  @Test
  public void testDouble0() {
    Assert.assertEquals(8, Double.BYTES);
  }

  @Test
  public void testChar0() {
    Assert.assertTrue(Character.MAX_VALUE == '\uffff'); // 65535
    Assert.assertTrue(Character.MIN_VALUE == '\u0000'); // 0
    Assert.assertEquals(2, Character.BYTES);
  }

  @Test
  public void testString0() {
    String s0 = "String is immutable";
    String s1 = s0;
    s0 = "Change s0";
    Assert.assertEquals(s0, "Change s0");
    Assert.assertEquals(s1, "String is immutable");
  }
}
