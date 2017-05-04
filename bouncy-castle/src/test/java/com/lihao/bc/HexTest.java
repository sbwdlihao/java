package com.lihao.bc;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author bencong.lh
 * @version $Id: HexTest, v0.1 2017年04月19日 下午2:11 bencong.lh Exp $
 */

public class HexTest {

    @Test
    public void testHex() {
        SHA256Digest digest = new SHA256Digest();
        byte[] s = new byte[]{0,10,1};
        digest.update(s, 0, s.length);
        byte[] d = new byte[32];
        digest.doFinal(d, 0);
        String hexString = Hex.toHexString(d);
        Assert.assertEquals(64, hexString.length());
        System.out.println(hexString);
    }
}
