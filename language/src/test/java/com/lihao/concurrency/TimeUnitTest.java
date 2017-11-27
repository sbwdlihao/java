package com.lihao.concurrency;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author bencong.lh
 * @version $Id: TimeUnitTest, v0.1 2017年08月22日 上午10:18 bencong.lh Exp $
 */

public class TimeUnitTest {

    @Test
    public void testTime() {
        System.out.println(TimeUnit.SECONDS.toNanos(60)); // 60000000000
        System.out.println(TimeUnit.SECONDS.toMillis(60)); // 60000
    }
}
