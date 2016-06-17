package com.lihao.hibernate.hbm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by sbwdlihao on 6/17/16.
 */
public class NativeApiIllustrationTest {

  private SessionFactory sessionFactory;

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

  @Test
  public void testBasicUsage() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(new Event("Our very first event!", new Date()));
    session.save(new Event("A follow up event", new Date()));
    session.getTransaction().commit();
    session.close();

    session = sessionFactory.openSession();
    session.beginTransaction();
    List<Event> result = session.createQuery("from Event ", Event.class).list();
    result.forEach(event -> System.out.println("Event (" + event.getDate() + ") : " + event.getTitle()));
    session.getTransaction().commit();
    session.close();
  }
}
