package com.lihao.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * 基本类型的包装类测试
 *
 * Created by bencong on 2017/3/13.
 */
public class WrapperTest {

    @Test
    public void testCache() {
        Integer i0 = 100;
        Integer i1 = 100;
        Integer i2 = 200;
        Integer i3 = 200;
        Assert.assertTrue(i0 == i1);
        Assert.assertFalse(i2 == i3);

        // 基本类型的装箱过程是调用valueOf方法，Integer的valueOf中会对-128到127的int值返回缓存的Integer
        // 类似的还有Character,Byte,Short,Long
        // Float和Double没有缓存
    }

    @Test(expected = NullPointerException.class)
    public void testNull() {
        Integer i0 = null;
        int i1 = i0;
    }
}
