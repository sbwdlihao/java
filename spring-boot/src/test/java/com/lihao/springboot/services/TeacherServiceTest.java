package com.lihao.springboot.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author bencong.lh
 * @version $Id: TeacherServiceTest, v0.1 2017年04月20日 下午8:28 bencong.lh Exp $
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeacherServiceTest {

    @Resource
    private TeacherService teacherService;

    @Resource
    private UserBalanceService userBalanceService;

    @Test
    public void testRollback() {
        teacherService.testRollback();
    }


    // 多线程测试事物
    @Test
    public void testTransactional() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Integer finalI = i;
            Thread t = new Thread(()->{
//                teacherService.addTeacher("n" + finalI.toString(), "id");
                teacherService.updateTeacher(finalI.toString(), "123");
            });
            t.start();
        }
        Thread.sleep(1000);
    }

    // 多线程测试事物测试结果，在多线程下，事务并没有并发控制作用
    // 必须要加锁，
    @Test
    public void testTransactional2() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(()->{
                userBalanceService.addOne("lihao");
            });
            t.start();
        }
        Thread.sleep(1000);
    }

    @Test
    public void testReadCommitted() throws InterruptedException {
        Thread a = new Thread(()->{
            try {
                teacherService.aWrite();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread b = new Thread(()->{
            teacherService.bRead();
        });
        a.start();
        Thread.sleep(200);
        b.start();
        Thread.sleep(1000);
    }
}
