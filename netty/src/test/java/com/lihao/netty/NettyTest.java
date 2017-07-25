package com.lihao.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.IllegalReferenceCountException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author bencong.lh
 * @version $Id: NettyTest, v0.1 2017年07月07日 下午7:20 bencong.lh Exp $
 */

public class NettyTest {

    @Test
    public void testReferenceCount() {
        ByteBufAllocator allocator = ByteBufAllocator.DEFAULT;
        ByteBuf buf = allocator.directBuffer();
        Assert.assertEquals(1, buf.refCnt());

        ByteBuf buf1 = buf;
        Assert.assertEquals(1, buf.refCnt());
        Assert.assertEquals(1, buf1.refCnt());

        ByteBuf buf2 = buf.copy();
        Assert.assertEquals(1, buf.refCnt());
        Assert.assertEquals(1, buf2.refCnt());

        boolean destroyed = buf.release();
        Assert.assertTrue(destroyed);
        Assert.assertEquals(0, buf.refCnt());

        try {
            buf.release();
        } catch (Exception e) {
            Assert.assertEquals(IllegalReferenceCountException.class, e.getClass());
        }
    }

    @Test
    public void testRead() {
        ByteBufAllocator allocator = ByteBufAllocator.DEFAULT;
        ByteBuf buf = allocator.directBuffer();
        buf.writeInt(1);
        ByteBuf buf1 = buf.readSlice(4);
        Assert.assertEquals(1, buf.refCnt());
        Assert.assertEquals(1, buf1.refCnt());

        // buf1是共享出来的，所以buf1 release之后会导致buf也release
        buf1.release();
        Assert.assertEquals(0, buf1.refCnt());
        Assert.assertEquals(0, buf.refCnt());
    }
}
