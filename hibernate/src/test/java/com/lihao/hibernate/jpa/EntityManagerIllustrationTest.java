package com.lihao.hibernate.jpa;

import com.lihao.hibernate.JpaTest;
import com.lihao.hibernate.annotations.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

/**
 * Created by sbwdlihao on 6/24/16.
 */
public class EntityManagerIllustrationTest extends JpaTest{

  @Test
  public void testBasicUsage() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(new Event("Our very first event!", new Date()));
    entityManager.persist(new Event("A follow up event", new Date()));
    entityManager.getTransaction().commit();
    entityManager.close();

    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    List<Event> result = entityManager.createQuery("from Event ", Event.class).getResultList();
    result.forEach(event->System.out.println("Event (" + event.getDate() + ") : " + event.getTitle()));
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
