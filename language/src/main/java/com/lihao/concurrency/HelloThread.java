package com.lihao.concurrency;

/**
 * Created by sbwdlihao on 28/12/2016.
 */
public class HelloThread extends Thread{
    @Override
    public void run() {
        System.out.println("hello thread");
    }
}
