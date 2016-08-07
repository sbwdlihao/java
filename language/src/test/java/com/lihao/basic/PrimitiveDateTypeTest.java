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
  public void testByte2() {
    byte b0 = -12;
    byte b1 = 0;
    byte b2 = 12;
    int i0 = (int)b0; // -12
    int i1 = (int)b1; // 0
    int i2 = (int)b2; // 12
    int j0 = b0; // -12
    int j1 = b1; // 0
    int j2 = b2; // 12
    int k0 = b0 & 0xff; // 244
    int k1 = b1 & 0xff; // 0
    int k2 = b2 & 0xff; // 12

    // byte & 0xff is to convert to unsigned int

    byte c0 = (byte)b0; // -12
    byte c1 = (byte)k1; // 0
    byte c2 = (byte)k2; // 12

    byte d0 = (byte)0; // 0
    byte d1 = (byte)127; // 127
    byte d2 = (byte)128; // -128
    byte d3 = (byte)255; // -1
    byte d4 = (byte)256; // 0
    byte d5 = (byte)257; // 1
    byte d6 = (byte)-1; // -1
    byte d7 = (byte)-128; // -128
    byte d8 = (byte)-129; // 127

    byte e0 = 0b00000001; // 1
    byte e1 = 0b01111111; // 127
    byte e2 = (byte) 0b10000000; // -128
    byte e3 = (byte) 0b10000001; // -127
    byte e4 = (byte) 0b11111111; // -1

    // one byte & oxff is the index on the clock which has 256 positions from 0 to 255
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
