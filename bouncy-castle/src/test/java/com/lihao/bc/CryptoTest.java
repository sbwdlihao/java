package com.lihao.bc;

import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.Signature;

/**
 * Created by sbwdlihao on 11/28/16.
 */
public class CryptoTest {

    @Test
    public void testSHA30() throws IOException {
        SHA3Digest digest = new SHA3Digest();
        byte[] data = {0, 1, 2};
        digest.update(data, 0, data.length);
        byte[] hash = new byte[32];
        digest.doFinal(hash, 0);
        for (byte b : hash) {
            System.out.print(b);
            System.out.print(",");
        }
    }

    @Test
    public void testMD5() {
        MD5Digest digest = new MD5Digest();
        byte[] data = {0, 1, 2};
        digest.update(data, 0, data.length);
        byte[] hash = new byte[16];
        digest.doFinal(hash, 0);
        for (byte b : hash) {
            System.out.print(b);
            System.out.print(",");
        }
        // md5的digest长度是16个字节
    }

    @Test
    public void testECDSA() throws NoSuchAlgorithmException {
        // 是否存在某种算法跟provider有关，bouncle可以当做一种provider，不过需要配置
//        Security.getProviders() 查询所有的provider，进而查看支持的算法
//        Security.addProvider(new BouncyCastleProvider()); 添加一种provider，另外还可以在security文件中配置
        Signature signature = Signature.getInstance("SHA256withECDSA");
    }
}
