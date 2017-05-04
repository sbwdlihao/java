package com.lihao.basic;

import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author bencong.lh
 * @version $Id: SetTest, v0.1 2017年04月28日 下午2:32 bencong.lh Exp $
 */

public class SetTest {

    @Test
    public void testSort() {
        SortedSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(1);
        set.add(5);
        set.add(3);
        set.add(10);
        set.add(9);
        System.out.println(set);
    }
}
