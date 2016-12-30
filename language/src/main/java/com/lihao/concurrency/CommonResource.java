package com.lihao.concurrency;

/**
 * Created by sbwdlihao on 29/12/2016.
 */
public class CommonResource {
    private Worker owner;

    public Worker getOwner() {
        return owner;
    }

    public synchronized void setOwner(Worker owner) {
        this.owner = owner;
    }

    public CommonResource(Worker d) {
        owner = d;
    }
}
