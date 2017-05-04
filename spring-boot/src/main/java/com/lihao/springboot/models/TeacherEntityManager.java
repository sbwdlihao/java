package com.lihao.springboot.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class TeacherEntityManager {

    @PersistenceContext
    private EntityManager em;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherEntityManager(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Teacher findOne(String id) {
        Query query = em.createNativeQuery("SELECT * FROM study.teacher WHERE id = :id", Teacher.class);
        query.setParameter("id", id);
        try {
            return (Teacher) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
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
