package com.lihao.bitcoinj;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sbwdlihao on 7/5/16.
 */
public class CoreTest {

  @Test
  public void testHex0() {
    byte[] b0 = {-12};
    String s0 = Utils.HEX.encode(b0);
    Assert.assertEquals("f4", s0); // 244

    byte[] b1 = {12};
    String s1 = Utils.HEX.encode(b1);
    Assert.assertEquals("0c", s1); // 12
  }

  @Test
  public void testSha256Hash0() {
    Sha256Hash zero = Sha256Hash.ZERO_HASH;
    Sha256Hash zero1 = Sha256Hash.wrap("0000000000000000000000000000000000000000000000000000000000000000");
    Assert.assertEquals(zero, zero1);

    Sha256Hash block419516 = Sha256Hash.wrap("000000000000000004ab38fccc584798cf98cf42919ec91e08883841f09654f2");
    Assert.assertEquals("000000000000000004ab38fccc584798cf98cf42919ec91e08883841f09654f2", block419516.toString());
    Assert.assertEquals(-14, block419516.getBytes()[31]); // 0xf2 is 242 in int and is -14 in byte
  }
}
