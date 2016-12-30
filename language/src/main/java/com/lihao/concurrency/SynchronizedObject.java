package com.lihao.concurrency;

/**
 * Created by sbwdlihao on 7/6/16.
 */
public class SynchronizedObject {

    private int[] ints;

    public final int[] getInts() {
        if (ints == null) {
            // why we must use synchronized here?
            synchronized (this) {
                // why we must check ints if null again?
                if (ints == null) {
                    ints = new int[10];
                }
            }
        }
        return ints;
    }

    // not concurrency safe
    @Deprecated
    public final int[] getInts0() {
        if (ints == null) {
            ints = new int[10];
        }
        return ints;
    }

    // concurrency safe, but one concurrency get ints will block other threads every time
    @Deprecated
    public final int[] getInts1() {
        synchronized (this) {
            if (ints == null) {
                ints = new int[10];
            }
            return ints;
        }
    }

    // not concurrency safe, when n(n > 1) threads get ints at the same time and ints is null, the ints will be init n times
    @Deprecated
    public final int[] getInts2() {
        if (ints == null) {
            synchronized (this) {
                ints = new int[10];
            }
        }
        return ints;
    }

    synchronized void justWait() throws InterruptedException {
        wait();
    }
}
