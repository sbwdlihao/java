package com.lihao.hibernate;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by sbwdlihao on 6/24/16.
 */
public class JpaTest {
  protected EntityManagerFactory entityManagerFactory;

  @Before
  public void setUp() {
    entityManagerFactory = Persistence.createEntityManagerFactory("com.lihao.hibernate");
  }

  @After
  public void tearDown() {
    entityManagerFactory.close();
  }
}
