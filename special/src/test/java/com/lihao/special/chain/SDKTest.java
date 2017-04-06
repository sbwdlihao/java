package com.lihao.special.chain;

import com.chain.exception.ChainException;
import com.chain.http.Client;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * SDKTest
 *
 * Created by sbwdlihao on 2017/3/22.
 */
public class SDKTest {

    @Test
    public void testSdk() {
        Client client = new Client();
        client.accessToken();
        client.hasAccessToken();
        client.pinCertificate("", "");
        try {
            client.request("", new Object(), Object.class);
        } catch (ChainException e) {
            e.printStackTrace();
        }
        try {
            client.batchRequest("", new Object(), Object.class, Object.class);
        } catch (ChainException e) {
            e.printStackTrace();
        }
        try {
            client.singletonBatchRequest("", new Object(), Object.class, Object.class);
        } catch (ChainException e) {
            e.printStackTrace();
        }
        client.setConnectTimeout(1, TimeUnit.DAYS);
        client.setWriteTimeout(1, TimeUnit.DAYS);
    }
}
