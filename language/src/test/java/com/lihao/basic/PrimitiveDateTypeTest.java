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

    // byte是一个256个刻度的时钟，从0开始顺时针到127，接着是-128，最后到-1，再到0
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

  /*
  http://noalgo.info/344.html
  一 整数的计算机表示
  位运算需要操作内存中整数的二进制位，首先肯定要知道整数在计算机中是怎么表示的。整数分为原码、反码和补码三种表示。

  原码就是符号位加上真值的绝对值，最高位为符号位（正数的符号位为0，负数的为1），比如8位的二进制数：
  　　　　　　　　[+1] = [0000 0001]原
  　　　　　　　　[-1] = [1000 0001]原
  正数的反码是其本身。负数的反码是在原码的基础上，符号位不变，其余位取反。如
  　　　　　　　　[+1] = [00000001]原 = [00000001]反
  　　　　　　　　[-1] = [10000001]原 = [11111110]反
  整数的补码是其本身。负数的补码在原码的基础上，符号为不变，其余位取反，最后加1，也就是在反码的基础上加1。如
  　　　　　　　　[+1] = [00000001]原 = [00000001]反 = [00000001]补
  　　　　　　　　[-1] = [10000001]原 = [11111110]反 = [11111111]补
  整数在计算机内的表示即为补码表示法，目的是为了在运算时让符号位也参与运算，方便执行，这里不详细讨论。

  在java中负数补码的表示得到了体现，-1就是11111111
  * */

  @Test
  public void testInt1() {
    int a = 60; // 60 = 0000 0000 0000 0000 0000 0000 0011 1100
    int c;

    c = ~a; // -61 = 1111 1111 1111 1111 1111 1111 1100 0011
    Assert.assertEquals(-61, c);

    // int是一个4294967296个刻度的时钟，从0开始顺时针到2147483647，接着是-2147483648，最后到-1，再到0

    c = a >> 2; // 15 = 0000 0000 0000 0000 0000 0000 0000 1111 有符号右移
    Assert.assertEquals(15, c);
    c = -61 >> 2; // -16 = 1111 1111 1111 1111 1111 1111 1111 0000 有符号右移
    Assert.assertEquals(-16, c);
    c = -61 >>> 2; // 1073741808 = 0011 1111 1111 1111 1111 1111 1111 0000 无符号右移，忽略符号位，空位都以0补齐
    Assert.assertEquals(1073741808, c);
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
