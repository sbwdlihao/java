package com.lihao.bc;

import org.bouncycastle.crypto.digests.SHA3Digest;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by sbwdlihao on 11/28/16.
 */
public class CryptoTest {

  @Test
  public void testSHA30() throws IOException {
    SHA3Digest digest = new SHA3Digest();
    byte[] data = {0,1,2};
    digest.update(data, 0, data.length);
    byte[] hash = new byte[32];
    digest.doFinal(hash, 0);
    for (byte b : hash) {
      System.out.print(b);
      System.out.print(",");
    }
  }
}
