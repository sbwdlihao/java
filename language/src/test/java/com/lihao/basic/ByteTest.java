package com.lihao.basic;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by sbwdlihao on 11/28/16.
 */
public class ByteTest {

  @Test
  public void test0(){
    System.out.println(Arrays.toString(ByteBuffer.allocate(8).putLong(0).array())); // [0, 0, 0, 0, 0, 0, 0, 0]
    System.out.println(Arrays.toString(ByteBuffer.allocate(8).putLong(1234).array())); // [0, 0, 0, 0, 0, 0, 4, -46]
  }
}
