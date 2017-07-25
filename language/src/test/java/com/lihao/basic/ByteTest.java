package com.lihao.basic;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.BitSet;

/**
 * Created by sbwdlihao on 11/28/16.
 */
public class ByteTest {

    @Test
    public void test0() {
        System.out.println(Arrays.toString(ByteBuffer.allocate(8).putLong(0).array())); // [0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(Arrays.toString(ByteBuffer.allocate(8).putLong(1234).array())); // [0, 0, 0, 0, 0, 0, 4, -46]
    }

    @Test
    public void test1() {
        byte[] a0 = new byte[2]; // 默认初始化所有的数组元素为0
        Assert.assertEquals(0, a0[0]);
        Assert.assertEquals(0, a0[1]);
        byte[][] a = new byte[2][]; // a中的每个元素都是空数组
        byte[][] b = new byte[2][0]; // b中的每个元素都是长度为0的数组
    }

    @Test
    public void test2() {
        BitSet bitSet = new BitSet();
        bitSet.set(1);
        byte[] bytes = bitSet.toByteArray();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]); // 2
        }

        bitSet.set(2);
        bytes = bitSet.toByteArray();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]); // 6
        }

        bitSet.set(3);
        bytes = bitSet.toByteArray();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]); // 14
        }

        bitSet.set(8);
        bytes = bitSet.toByteArray();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]); // 14, 1
        }

        System.out.println("========");

        bitSet = new BitSet(2);
        System.out.println(bitSet.length()); // 0
        System.out.println(bitSet.size()); // 64
        bytes = bitSet.toByteArray();
        System.out.println(bytes.length); // 0
        bitSet = new BitSet(65);
        System.out.println(bitSet.length()); // 0
        System.out.println(bitSet.size()); // 128
        bytes = bitSet.toByteArray();
        System.out.println(bytes.length); // 0
    }

}
