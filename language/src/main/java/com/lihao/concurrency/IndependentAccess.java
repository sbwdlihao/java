package com.lihao.concurrency;

/**
 * Created by sbwdlihao on 29/12/2016.
 */
class IndependentAccess {
    private int n0;
    private int n1;
    private final Object lock0 = new Object();
    private final Object lock1 = new Object();

    void setN0(int n0) {
        synchronized(lock0) {
            this.n0 = n0;
            System.out.println(this.n0);
        }
    }

    void setN1(int n1) {
        synchronized(lock1) {
            this.n1 = n1;
            System.out.println(this.n1);
        }
    }
}
