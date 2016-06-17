package com.lihao.lambda;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by sbwdlihao on 6/16/16.
 */
public class LambdaTest {

  @Test
  public void test0() {
    Arrays.asList( "a", "b", "d" ).forEach(System.out::println);
  }
}
