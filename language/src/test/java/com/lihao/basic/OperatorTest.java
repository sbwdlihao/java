package com.lihao.basic;

import org.junit.Test;

/**
 * Created by sbwdlihao on 05/01/2017.
 */
public class OperatorTest {

    @Test
    public void testMod() {
        int a1 = -17%6; // -5
        int b1 = 17%6; // 5
        int a2 = Math.floorMod(-17, 6); // 1
        int b2 = Math.floorMod(17, 6); // 5
        int a3 = 17%-6; // 5
        int b3 = Math.floorMod(17, -6); // -1
        int a4 = Math.floorMod(-17, -6); // -5
        int b4 = -17%-6; // -5
        System.out.println(a1);
        System.out.println(b1);
        System.out.println(a2);
        System.out.println(b2);
        System.out.println(a3);
        System.out.println(b3);
        System.out.println(a4);
        System.out.println(b4);
        /*
        * 使用%得到的结果是|a|%|b|，然后取a的符号
        * 如果a和b的符号相同，则floorMod和%的作用相同
        *
        * floorMod和python中的%取得的结果一样
        * 不同的程序语言在取模的计算上有差异
        * https://en.wikipedia.org/wiki/Modulo_operation
        * */
    }
}
