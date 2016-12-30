package com.lihao.concurrency;

/**
 * Created by sbwdlihao on 29/12/2016.
 */
public class Worker {
    private String name;
    private boolean active;

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public Worker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public synchronized void work(CommonResource resource, Worker otherWorker) {
        while (active) {
            // wait for the resource to become available.
            if (resource.getOwner() != this) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    //ignore
                }
                continue;
            }

            // If other worker is also active let it do it's work first
            if (otherWorker.isActive()) {
                System.out.println(getName() +
                        " : handover the resource to the worker " +
                        otherWorker.getName());
                resource.setOwner(otherWorker);
                continue;
            }

            //now use the commonResource
            System.out.println(getName() + ": working on the common resource");
            active = false;
            resource.setOwner(otherWorker);
        }
    }
}
