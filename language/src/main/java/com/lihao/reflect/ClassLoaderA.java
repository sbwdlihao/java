package com.lihao.reflect;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author bencong.lh
 * @version $Id: ClassLoaderA, v0.1 2017年05月22日 下午3:11 bencong.lh Exp $
 */

public class ClassLoaderA extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String className) {
        // read class
        String[] strings = className.split(".");
        InputStream is = getClass().getClassLoader().getResourceAsStream(
                strings[strings.length - 1] + ".class.file");
        ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
        // write into byte
        int len;
        try {
            while ((len = is.read()) != -1) {
                byteSt.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // convert into byte array
        return byteSt.toByteArray();
    }
}
