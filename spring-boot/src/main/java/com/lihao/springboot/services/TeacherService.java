package com.lihao.springboot.services;

import com.lihao.springboot.models.Teacher;
import com.lihao.springboot.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author bencong.lh
 * @version $Id: TeacherService, v0.1 2017年04月20日 下午8:22 bencong.lh Exp $
 */

@Service
public class TeacherService {

    @Resource
    private TeacherRepository teacherRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Teacher addTeacher(String name, String no) {
        System.out.println(String.format("name = %s, no = %s", name, no));
        Teacher t = teacherRepository.findByNo(no);
        if (t != null) {
            return t;
        }
        t = new Teacher();
        t.setName(name);
        t.setNo(no);
        t = teacherRepository.save(t);
        System.out.println(String.format("updated name = %s, no = %s", t.getName(), t.getNo()));
        return t;
    }

    @Transactional
    public void updateTeacher(String name, String no) {
        System.out.println(String.format("name = %s, no = %s", name, no));
        Teacher t = teacherRepository.findByNo(no);
        if (t == null) {
            throw new RuntimeException("teacher not found");
        }
        System.out.println("修改前： " + t.getName());
        t.setName(t.getName() + name);
        t = teacherRepository.saveAndFlush(t);
        System.out.println("修改后：" + t.getName());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void aWrite() throws InterruptedException {
        System.out.println("a start write");
        Teacher t = teacherRepository.findByNo("123");
        System.out.println("a read t name = " + t.getName());
        t.setName("aWrite" + new Random().nextInt());
        System.out.println("a update name = " + t.getName());
        t = teacherRepository.save(t);
        Thread.sleep(100);
        System.out.println("a wait 100 ms to commit");
        System.out.println("a write end");
        throw new UnsupportedOperationException();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void bRead() {
        System.out.println("b start read");
        Teacher t = teacherRepository.findByNo("123");
        System.out.println("b read t name = " + t.getName());
        System.out.println("b read end");
    }

    @Transactional
    public void testRollback() {
        throw new UnsupportedOperationException();
    }
}
