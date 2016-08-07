package com.lihao.springboot.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by sbwdlihao on 5/27/16.
 */
@Repository
@Transactional
public class TeacherRepository {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public Teacher findOne(String id) {
    Query query = em.createNativeQuery("SELECT * FROM study.teacher WHERE id = :id", Teacher.class);
    query.setParameter("id", id);
    return (Teacher) query.getSingleResult();
  }

  public Object save(Teacher teacher) {
    Query query = em.createNativeQuery("INSERT INTO study.teacher VALUES (:id, :name)");
    query.setParameter("id", teacher.getId());
    query.setParameter("name", teacher.getName());
    return query.executeUpdate();
  }

  public Teacher persist(Teacher teacher) {
    em.persist(teacher);
    return teacher;
  }

  public List<Teacher> findAll() {
    return jdbcTemplate.query("SELECT * FROM study.teacher", (rs, rowNum) -> {
      Teacher teacher = new Teacher();
      teacher.setId(rs.getString("id"));
      teacher.setName(rs.getString("name"));
      return teacher;
    });
  }
}
