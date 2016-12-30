package com.lihao.basic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by sbwdlihao on 28/12/2016.
 */
public class OptionalTest {

    @Test
    public void testEmpty() {
        Optional<byte[]> b = Optional.empty();
        boolean is = b.isPresent(); // false
        Assert.assertFalse(is);
        byte[] orElse = b.orElse(new byte[0]); // byte[0]
        Assert.assertArrayEquals(new byte[0], orElse);
        byte[] orElseGet = b.orElseGet(()->new byte[]{1}); // byte[1]
        Assert.assertArrayEquals(new byte[]{1}, orElseGet);
    }
}
