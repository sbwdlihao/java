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

    @Test
    public void testArray2() {
        // 创建一个长度为3的二维数组
        byte[][] n = new byte[3][];
        for (int i = 0; i < 3; i++) {
            n[i] = new byte[i + 1];
        }
        Assert.assertEquals(3, n.length);

        // 长度为0的数组
        byte[] n1 = new byte[0];
        Assert.assertEquals(0, n1.length);
    }

    @Test
    public void testArray3() {
        byte[] a = new byte[0];
        byte[] b = new byte[0];
        // 对于基本类型的数组，是进行引用判断，同样的值进行equal判断时是不相等的
        Assert.assertNotEquals(a, b);
    }
}
