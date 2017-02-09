package com.lihao.reflect;

import org.junit.Test;

/**
 * Created by sbwdlihao on 05/02/2017.
 */
public class ReflectTest {

    @Test
    public void testClassName() {
        Short a0 = 1;

        System.out.println(a0.getClass().isAssignableFrom(Short.class)); // true
        System.out.println(a0.getClass().isAssignableFrom(Object.class)); // false

        System.out.println(a0.getClass().getName()); // java.lang.Short
        System.out.println(a0.getClass().getTypeName()); // java.lang.Short
        // 从getTypeName的源码可以看出，在不是array的情况下直接返回getName的结果

        System.out.println(long.class.getName()); // long
        System.out.println(byte[].class.getName()); // [B
        System.out.println(byte[].class.getTypeName()); // byte[]
        System.out.println(Byte[].class.getName()); // [Ljava.lang.Byte;
        System.out.println(Byte[].class.getTypeName()); // java.lang.Byte[]

        Base b = new Base();
        Extended t = new Extended();
        System.out.println(b.getClass().isAssignableFrom(Base.class)); // true，表示 Base x = b， b是一个Base对象，这样的赋值是正确的
        System.out.println(b.getClass().isAssignableFrom(Extended.class)); // true，表示 Base x = t， t是一个Extended对象，这样的赋值也是正确的

        System.out.println(b instanceof Base); // true
        System.out.println(b instanceof Extended); // false
        System.out.println(t instanceof Base); // true
        System.out.println(t instanceof Extended); // true

        Byte[] bytes = new Byte[1];
        System.out.println(bytes instanceof Byte[]); // true

        Object o = a();
        System.out.println(o.getClass().getTypeName()); // java.lang.Integer
        Object o1 = a1();
        System.out.println(o1.getClass().getTypeName()); // byte[]
        // 对基本类型对进行包装，基本类型的数组不包装

        Class c0 = int.class;
        Class c1 = int.class;
        System.out.println(c0 == c1); // true

        Class c2 = byte[].class;
        Class c3 = byte[].class;
        System.out.println(c2 == c3); // true
        // 对象的class都是同一个对象

        Base[] bases = new Base[] {
                new Base()
        };
        System.out.println(bases.getClass().isArray()); // true
        System.out.println(bases.getClass().getComponentType()); // class com.lihao.reflect.Base
    }

    private Object a() {
        return 1;
    }

    private Object a1() {
        return new byte[1];
    }
}
