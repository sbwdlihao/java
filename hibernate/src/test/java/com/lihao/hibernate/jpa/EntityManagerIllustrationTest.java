package com.lihao.hibernate.jpa;

import com.lihao.hibernate.JpaTest;
import com.lihao.hibernate.annotations.Event;
import com.lihao.hibernate.annotations.Event_;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Event> criteriaQuery = builder.createQuery(Event.class);
    Root<Event> root = criteriaQuery.from(Event.class);
    criteriaQuery.select(root);
    criteriaQuery.where(builder.equal(root.get(Event_.id), 1L));
    Event event = entityManager.createQuery(criteriaQuery).getSingleResult();
    System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
