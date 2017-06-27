package com.lihao.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by sbwdlihao on 12/01/2017.
 */
public class ListTest {

    @Test
    public void testList() {
        try {
            // 会抛出异常
            List<Long> longs = new ArrayList<>();
            longs.add(3, 1L);
            System.out.println(longs.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            // 会抛出异常
            List<Long> longs = new LinkedList<>();
            longs.add(3, 1L);
            System.out.println(longs.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testListPerformance() {
        List<byte[]> arrayList = new ArrayList<>();
        List<byte[]> linkedList = new LinkedList<>();
        long arrayListTime = 0;
        long linkedListTime = 0;

        int n = 10000;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int l = random.nextInt(10);
            if (l < 0) {
                l = 0;
            }
            byte[] bytes = new byte[l];
            long now = System.nanoTime();
            arrayList.add(bytes);
            long newNow = System.nanoTime();
            arrayListTime += newNow - now;
            linkedList.add(bytes);
            linkedListTime += System.nanoTime() - newNow;
        }
        System.out.println(arrayListTime); // 1218329
        System.out.println(linkedListTime); // 2020739
        // 测试表明在列表的尾部add操作上arrayList比linkedList的效率更高
    }

    @Test
    public void testListAddFirstPerformance() {
        List<byte[]> arrayList = new ArrayList<>();
        List<byte[]> linkedList = new LinkedList<>();
        long arrayListTime = 0;
        long linkedListTime = 0;

        int n = 10000;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int l = random.nextInt(10);
            if (l < 0) {
                l = 0;
            }
            byte[] bytes = new byte[l];
            long now = System.nanoTime();
            arrayList.add(0, bytes);
            long newNow = System.nanoTime();
            arrayListTime += newNow - now;
            linkedList.add(0, bytes);
            linkedListTime += System.nanoTime() - newNow;
        }
        System.out.println(arrayListTime); // 6772720
        System.out.println(linkedListTime); // 2747851
        // 测试表明在列表的头部add操作上linkedList比arrayList的效率更高
    }
}
