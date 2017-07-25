package com.lihao.net;

import org.junit.Test;

/**
 * @author bencong.lh
 * @version $Id: EchoTest, v0.1 2017年06月28日 上午10:29 bencong.lh Exp $
 */

public class EchoTest {

    @Test
    public void testEchoClient() {
        EchoClient client = new EchoClient("localhost", 4444);
        client.start();
    }

    @Test
    public void testEchoServer() {
        EchoServer server = new EchoServer(4444);
        server.start();
    }
}
