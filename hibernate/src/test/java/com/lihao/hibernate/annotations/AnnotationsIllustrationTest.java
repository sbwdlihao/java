package com.lihao.hibernate.annotations;

import com.lihao.hibernate.SessionFactoryTest;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by sbwdlihao on 6/24/16.
 */
public class AnnotationsIllustrationTest extends SessionFactoryTest {

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
