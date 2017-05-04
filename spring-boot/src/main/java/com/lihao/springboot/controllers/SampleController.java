package com.lihao.springboot.controllers;

import com.lihao.springboot.models.Teacher;
import com.lihao.springboot.models.TeacherEntityManager;
import com.lihao.springboot.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sample")
public class SampleController {

    private final TeacherEntityManager teacherEntityManager;

    @Resource
    private TeacherService teacherService;

    @Autowired
    public SampleController(TeacherEntityManager teacherEntityManager) {
        this.teacherEntityManager = teacherEntityManager;
    }

    @RequestMapping("/{id}")
    public Object index(@PathVariable String id) {
        return teacherEntityManager.findOne(id);
    }

    @RequestMapping("/")
    public Object all() {
        return teacherEntityManager.findAll();
    }

    @RequestMapping("/{id}/{name}")
    public Object insert(@PathVariable String id, @PathVariable String name) {
        return teacherService.addTeacher(id, name);
    }

    @RequestMapping("/insert/{name}")
    public Object insert(@PathVariable String name) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        return teacherEntityManager.persist(teacher);
    }

    @RequestMapping("/rollback")
    public void testRollback() {
        teacherService.testRollback();
    }
}
