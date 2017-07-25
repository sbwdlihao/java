package com.lihao.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bencong.lh
 * @version $Id: JsonUtil, v0.1 2017年07月19日 下午5:17 bencong.lh Exp $
 */

public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    public static void testLog() {
        LOGGER.trace("trace msg from JsonUtil");
        LOGGER.debug("debug msg from JsonUtil");
        LOGGER.info("info msg from JsonUtil");
        LOGGER.warn("warn msg from JsonUtil");
        LOGGER.error("error msg from JsonUtil");
    }
}
