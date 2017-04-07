package com.lihao.springboot.controllers;

import com.lihao.springboot.models.Teacher;
import com.lihao.springboot.models.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sbwdlihao on 5/26/16.
 */
@RestController
@RequestMapping("/sample")
public class SampleController {

  @Autowired
  private TeacherRepository teacherRepository;

  @RequestMapping("/{id}")
  public Object index(@PathVariable String id) {
    return teacherRepository.findOne(id);
  }

  @RequestMapping("/")
  public Object all() {
    return teacherRepository.findAll();
  }

  @RequestMapping("/{id}/{name}")
  public Object insert(@PathVariable String id, @PathVariable String name) {
    Teacher teacher = new Teacher();
    teacher.setId(id);
    teacher.setName(name);
    return teacherRepository.save(teacher);
  }

  @RequestMapping("/insert/{name}")
  public Object insert(@PathVariable String name) {
    Teacher teacher = new Teacher();
    teacher.setName(name);
    return teacherRepository.persist(teacher);
  }
}
