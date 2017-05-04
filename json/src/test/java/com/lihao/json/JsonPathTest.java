package com.lihao.json;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

/**
 * @author bencong.lh
 * @version $Id: JsonPathTest, v0.1 2017年04月27日 上午10:40 bencong.lh Exp $
 */

public class JsonPathTest {

    @Test
    public void testJsonPath() {
        DocumentContext wrap = JsonPath.parse("{}");
        wrap = wrap.put("$", "index", 1);
        wrap = wrap.put("$","payload", "a");
        System.out.println(wrap.jsonString());
        try {
            JsonPath.compile(""); // json can not be null or empty
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            JsonPath.compile("dsads."); // Path must not end with a '.' or '..'
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
