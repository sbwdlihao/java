package com.lihao.concurrency;

/**
 * Created by sbwdlihao on 29/12/2016.
 */
class ShareConstructor {

    int share = 1;

    ShareConstructor(ShareConstructor[] instances) throws InterruptedException {
        synchronized (instances) {
            System.out.println("a shared object get the reference of this object");
            instances[0] = this;
            instances.notify();
            System.out.println("monitor current thread to be a waiting thread by cpu selector");
            instances.wait();
            System.out.println("construct object complete");
        }
    }
}
