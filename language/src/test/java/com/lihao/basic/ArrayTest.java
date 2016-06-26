package com.lihao.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sbwdlihao on 6/26/16.
 */
public class ArrayTest {

  @Test
  public void testArray0() {
    // initialize array when create
    int[] intArray = {1, 2, 3};
    Assert.assertEquals(3, intArray.length);
  }

  @Test
  public void testArray1() {
    // multi dimensional array
    String[][] strings = {
      {"a", "b"},
      {"c", "d", "e"}
    };
    Assert.assertEquals("e", strings[1][2]);
  }
}
