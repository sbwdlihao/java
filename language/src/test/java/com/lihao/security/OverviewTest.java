package com.lihao.security;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by sbwdlihao on 7/14/16.
 */
public class OverviewTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void test0() throws NoSuchAlgorithmException, NoSuchProviderException {
    MessageDigest.getInstance("SHA-256");

    expectedException.expect(NoSuchProviderException.class);
    MessageDigest.getInstance("SHA-256", "ProviderC");
  }
}
