package com.lihao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;

/**
 * Created by sbwdlihao on 6/24/16.
 */
public class SessionFactoryTest {
  protected SessionFactory sessionFactory;

  @Before
  public void setUp() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  @After
  public void tearDown() {
    if (sessionFactory != null) {
      sessionFactory.close();
    }
  }
}
