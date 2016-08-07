package com.lihao.security;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import sun.security.util.SecurityConstants;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.*;
import java.net.SocketPermission;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;

/**
 * Created by sbwdlihao on 7/15/16.
 */
public class ArchitectureTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void test0() throws IOException {
    File file = new File("build.gradle");
    Permission permission = new FilePermission(file.getAbsolutePath(), SecurityConstants.FILE_READ_ACTION);
    Permission permission1 = new SocketPermission("java.example.com", SecurityConstants.SOCKET_ACCEPT_ACTION);
    expectedException.expect(AccessControlException.class);
    AccessController.checkPermission(permission);
  }

  @Test
  public void test1() {
    ClassLoader classLoader = this.getClass().getClassLoader();
    if (classLoader != null) {
      SecurityManager securityManager = System.getSecurityManager();
      if (securityManager != null) {
        securityManager.checkRead("settings.gradle");
      }
    }
  }

  @Test
  public void test2() {
    Provider[] providers = Security.getProviders();
    for (Provider provider : providers) {
      System.out.println(provider);
    }
  }

  @Test
  public void test3() throws NoSuchAlgorithmException {
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
    System.out.println(sr.getAlgorithm());
    SecureRandom secureRandom = SecureRandom.getInstanceStrong();
    System.out.println(secureRandom.getAlgorithm());
    Provider provider = sr.getProvider();
    System.out.println(provider);
    byte[] seed = sr.generateSeed(5);
    sr.setSeed(seed);
    byte[] bytes = new byte[10];
    sr.nextBytes(bytes);
  }

  @Test
  public void test4() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    KeyPair kp = kpg.generateKeyPair();
    Signature signature = Signature.getInstance("SHA1withRSA");
    signature.initSign(kp.getPrivate());
    byte[] data = new byte[]{1, 2, 3, 4, 5};
    signature.update(data);
    byte[] sign = signature.sign();
    signature.initVerify(kp.getPublic());
    signature.update(data);
    Assert.assertTrue(signature.verify(sign));
  }

  @Test
  public void test5() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
    KeyGenerator kg = KeyGenerator.getInstance("AES");
    Key k = kg.generateKey();
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, k);
    AlgorithmParameters ap = cipher.getParameters();
  }
}
