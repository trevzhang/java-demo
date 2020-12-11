package com.trevzhang.demo.test;

import cn.hutool.crypto.digest.DigestUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * CPU密集型 StreamAPI性能测试
 *
 * @author Trevor Zhang
 * @since 2020/11/6
 */
public class StreamTest {

    public static void main(String[] args) throws Exception {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            stringList.add("第" + i + "条数据");
        }
        System.out.println("一共" + stringList.size() + "次循环");

        //for循环
        long forNowTime = System.currentTimeMillis();
        for (int i = 0; i < stringList.size(); i++) {
            TestMethod(stringList.get(i));
        }
        long forTime = System.currentTimeMillis();
        System.out.println("for循环需要时间: " + (forTime - forNowTime) + "ms");

        //增强for循环
        long forsNowTime = System.currentTimeMillis();
        for (String s : stringList) {
            TestMethod(s);
        }
        long forsTime = System.currentTimeMillis();
        System.out.println("增强for循环需要时间: " + (forsTime - forsNowTime) + "ms");

        //forEach循环
        long forEachNowTime = System.currentTimeMillis();
        stringList.forEach(s -> TestMethod(s));
        long forEachTime = System.currentTimeMillis();
        System.out.println("forEach循环需要时间: " + (forEachTime - forEachNowTime) + "ms");

        //Stream.forEach
        long StreamNowTime = System.currentTimeMillis();
        stringList.stream().forEach(s -> TestMethod(s));
        long StreamTime = System.currentTimeMillis();
        System.out.println("Stream.forEach需要时间: " + (StreamTime - StreamNowTime) + "ms");

        //Stream.forEachOrdered
        long StreamOrderedNowTime = System.currentTimeMillis();
        stringList.stream().forEachOrdered(s -> TestMethod(s));
        long StreamOrderedTime = System.currentTimeMillis();
        System.out.println("Stream.forEachOrdered需要时间: " + (StreamOrderedTime - StreamOrderedNowTime) + "ms");

        //parallelStream.forEach
        long parallelStreamNowTime = System.currentTimeMillis();
        stringList.parallelStream().forEach(s -> TestMethod(s));
        long parallelStreamTime = System.currentTimeMillis();
        System.out.println("ParallelStream.forEach需要时间: " + (parallelStreamTime - parallelStreamNowTime) + "ms");

        //parallelStream.forEachOrdered
        long parallelStreamOrderedNowTime = System.currentTimeMillis();
        stringList.parallelStream().forEachOrdered(s -> TestMethod(s));
        long parallelStreamOrderedTime = System.currentTimeMillis();
        System.out.println("ParallelStream.forEachOrdered需要时间: "
            + (parallelStreamOrderedTime - parallelStreamOrderedNowTime) + "ms");
    }

    private static void TestMethod(String str) {
        try {
            for (int i = 0; i < 500; i++) {
                str = DigestUtil.md5Hex(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
