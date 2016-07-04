package com.lihao.basic;

/**
 * Created by sbwdlihao on 6/28/16.
 */
public class Bicycle {

  private int price;

  private static String TYPE;

  public static String getTYPE() {
    return TYPE;
  }

  public int getPrice() {
    return price;
  }

  // initialize static members
  static {
    TYPE = "Bicycle";
  }

  // initialize instance members
  {
    price = 10;
  }
}
