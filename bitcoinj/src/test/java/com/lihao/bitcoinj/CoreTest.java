package com.lihao.bitcoinj;

import com.google.common.io.Files;
import org.bitcoinj.core.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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

  @Test
  public void testUtils0() {
    byte[] bytes = {12, 13, 14, 15}; // 0x0c0d0e0f
    long l = Utils.readUint32(bytes, 0); // 0f 0e 0d 0c
    Assert.assertEquals(0x0f0e0d0c, l);
  }

  @Test
  public void testBlock0() throws IOException {
    Path path = Paths.get("src/test/resources/block/0.bin");
    byte[] payload = Files.asByteSource(path.toFile()).read();
    Assert.assertEquals(285, payload.length);
    NetworkParameters networkParameters = NetworkParameters.fromID(NetworkParameters.ID_MAINNET);
    new Context(networkParameters);
    Block block = networkParameters.getDefaultSerializer().makeBlock(payload);
    Assert.assertEquals("000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f", block.getHashAsString());
  }
}
