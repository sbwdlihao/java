package com.lihao.concurrency;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

/**
 * Created by sbwdlihao on 28/12/2016.
 */
public class ThreadTest {

    @Test
    public void testThread() {
        new Thread(new HelloRunnable()).start();
        new HelloThread().start();
    }

    // 创建一个object，但该object的monitor持有者并不是当前线程，除非满足三种情况
    // 1.By executing a synchronized instance method of that object
    // 2.By executing the body of a synchronized statement that synchronizes on the object
    // 3.For objects of type Class by executing a synchronized static method of that class
    // 如果线程在没有object monitor的情况下就去调用object的wait会抛出IllegalMonitorStateException异常
    @Test(expected = IllegalMonitorStateException.class)
    public void testWaitNoMonitor() throws InterruptedException {
        Object object = new Object();
        object.wait();
    }

    // By executing a synchronized instance method of that object
    @Test
    @Ignore
    public void testGetMonitor0() throws InterruptedException {
        SynchronizedObject object = new SynchronizedObject();
        object.justWait();
    }

    // By executing the body of a synchronized statement that synchronizes on the object
    @Test
    @Ignore
    public void testGetMonitor1() throws InterruptedException {
        Object object = new Object();
        synchronized (object){
            object.wait();
        }
    }

    // 同一时间只能有1个线程能持有object monitor
    // 调用wait后线程都进入了object的等待队列
    @Test
    public void testWaitQueue() throws InterruptedException {
        Object object = new Object();
        Thread t0 = new Thread(()->{
            synchronized (object){
                System.out.println(new Date() + " child0 thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    object.wait(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date() + " child0 thread end");
                object.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t0.start();
        Thread t1 = new Thread(() -> {
            synchronized (object){
                try {
                    System.out.println(new Date() + " child1 thread start");
                    object.wait(2000);
                    System.out.println(new Date() + " child1 thread end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (object){
                try {
                    System.out.println(new Date() + " child2 thread start");
                    object.wait(10000);
                    System.out.println(new Date() + " child2 thread end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();

        t0.join();
        t1.join();
        t2.join();
        /*
        Wed Dec 28 21:24:44 CST 2016 child0 thread
        Wed Dec 28 21:24:45 CST 2016 child2 thread start
        Wed Dec 28 21:24:45 CST 2016 child1 thread start
        Wed Dec 28 21:24:47 CST 2016 child1 thread end
        Wed Dec 28 21:24:49 CST 2016 child0 thread end
        Wed Dec 28 21:24:50 CST 2016 child2 thread end

        当child0持有object monitor时，child1和child2都阻塞在synchronized语句那里
        直到child0调用wait释放了object monitor，child1和child2才有机会获取，这次运行中
        child2先获取到了，但由于child2立即叶调用了wait，所以child1也很快获取到了monitor，在时间上
        看child1和child2几乎是同时在运行
        child1的wait超时优先结束，所以立即获取了monitor（此时没有其它的竞争者）
        由于child0优先于child2等待结束，所以立即获取了monitor（此时没有其它的竞争者， child1已经运行结束），然后child0调用
        notify通知了child2提前结束，此时child2还无法获得monitor，因为child0还在运行，直到1秒后child0运行结束，child2才获取了
        monitor继续运行

        需要注意的是，如果一个synchronized中出现多个object，thread调用了某个object的wait只是释放了该object的monitor
        在thread的wait期间其它object的monitor依然被占有着
        */
    }

    @Test
    public void testHappensBefore() throws InterruptedException {
        /*
        *
        * happens-before原则是如果事件a在b之前发生，那么a的结果能被b可见
        * 比如a设置内存某个地址的数据为0，b在a设置好之后读取到的值也是0
        * 在多线程的情况下，有可能出现a线程先读取了某个地址上的数据，然后进行运算，在运算尚未结束或者尚未把结果写回内存的时候
        * b去读取地址上的数据，得到的就不是a处理的结果
        *
        * 因此happens-before在多线程情况下需要一定的机制去实现，java在创建多线程以及等待创建线程结束的时候确保了happens-before
        *
        * When a statement invokes Thread.start, every statement that has a happens-before relationship with that statement
        * also has a happens-before relationship with every statement executed by the new thread. The effects of the code
        * that led up to the creation of the new thread are visible to the new thread.
        *
        * When a thread terminates and causes a Thread.join in another thread to return, then all the statements executed
        * by the terminated thread have a happens-before relationship with all the statements following the successful join.
        * The effects of the code in the thread are now visible to the thread that performed the join.
        * */
        int a = 0;
        byte[] b = new byte[]{0};
        Thread t = new Thread(() -> {
            Assert.assertEquals(0, a);
            b[0] = 1;
        });
        t.start();
        t.join();
        Assert.assertEquals(1, b[0]);
    }

    @Test
    public void testConstructor() throws InterruptedException {
        ShareConstructor[] instances = new ShareConstructor[1];
        Thread child = new Thread(() -> {
            synchronized (instances) {
                while(instances[0] == null) {
                    try {
                        instances.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("another thread access the constructing object");
                Assert.assertEquals(1, instances[0].share);
                instances.notify();
            }
        });
        child.start();
        System.out.println("current thread start construct object");
        new ShareConstructor(instances);
        child.join();
        /*
        *
        * java语法上不允许在构造函数上使用synchronized，因为对象在构造的时候只有当前线程才能访问到
        *
        * 需要注意的是，在构造函数的内部，如果一个共享对象持有当前被构造对象的this，那么其它线程在构造尚未完成
        * 的时候也是有机会访问到this的
        *
        * 输出
        current thread start construct object
        a shared object get the reference of this object
        monitor current thread to be a waiting thread by cpu selector
        another thread access the constructing object
        construct object complete
        * */
    }

    @Test
    public void testIndependentAccess() {
        IndependentAccess access = new IndependentAccess();
        Thread t0 = new Thread(()-> access.setN0(1));
        t0.start();
        Thread t1 = new Thread(()->access.setN1(2));
        t1.start();
        /*
        *
        * 如果一个对象中有多个共享属性，但是这些共享属性之间又没有任何关联，则访问单个共享属性时不需要给整个
        * 对象加锁，可以给单个对象加锁，这样有利于提高并发能力
        *
        * */
    }

    private volatile long n = 0;

    @Test
    public void testAtomic() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(()->n++);
            t.start();
            t.join();
        }
        Assert.assertEquals(10000, n);
        /*
        *
        * 原子操作是要么发生，要么不发生，不会出现中断，java中对引用大部分基本变量（一共8个，long和double除外）的读写都是原子操作，而对声明为volatile的变量的读写都是原子操作
        *
        * Reads and writes are atomic for reference variables and for most primitive variables (all types except long and double)
        * Reads and writes are atomic for all variables declared volatile (including long and double variables).
        *
        * */
    }

    @Test
    public void testLiveLock() throws InterruptedException {
        final Worker w1 = new Worker("worker1", true);
        final Worker w2 = new Worker("worker2", true);
        final CommonResource resource = new CommonResource(w1);

        Thread t0 = new Thread(()-> w1.work(resource, w2));
        t0.start();
        Thread t1 = new Thread(()->w2.work(resource, w1));
        t1.start();
        t0.join();
        t1.join();

        /*
        *
        * 会循环输出如下的值，这就是LiveLock，即没有线程被block（不同于DeadLock）
        * 但是由于逻辑错误，线程间互相把资源让给对方处理，导致没有1个线程能真正的处理资源
        *
        worker1 : handover the resource to the worker worker2
        worker2 : handover the resource to the worker worker1
        worker1 : handover the resource to the worker worker2
        worker2 : handover the resource to the worker worker1
        worker1 : handover the resource to the worker worker2
        worker2 : handover the resource to the worker worker1
        .....
        * */
    }

    @Test
    public void testInterrupt() throws InterruptedException {
        Object object = new Object();
        Thread t0 = new Thread(()->{
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    System.out.println("t0 is interrupted");
                    e.printStackTrace();
                }
            }
            System.out.println("t0 is completed");
        });
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (t0) {
                System.out.println("t1 interrupt t0");
                t0.interrupt();
            }
            System.out.println("t1 is completed");
        });
        t0.start();
        t1.start();
        t0.join();
        t1.join();

        /*
        *
        t1 interrupt t0
        t1 is completed
        t0 is interrupted
        t0 is completed
        * */
    }
}
