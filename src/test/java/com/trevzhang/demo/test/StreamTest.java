package com.trevzhang.demo.test;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.trevzhang.demo.test.StreamDemo.Bean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * CPU密集型 StreamAPI性能测试
 *
 * @author Trevor Zhang
 * @since 2020/11/6
 */
public class StreamTest {

    /**
     * List做groupingBy后再复原回List测试
     */
    @Test
    public void testFlatMap() {
        List<Person> list = Arrays.asList(
            new Person("Trevor", "26"),
            new Person("Michael", "26"),
            new Person("Kanda", "17"),
            new Person("Catherine", "16"),
            new Person("Persona", "888"),
            new Person("Kyo", "18"),
            new Person("Yukio", "12"),
            new Person("Hikky", "18")
        );
        System.out.println(JSON.toJSONString(list));
        Map<String, List<Person>> map = list.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(JSON.toJSONString(map));
        for (String age : map.keySet()) {
            //do something
        }
        List<Person> personResultList = map.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(personResultList));
    }

    private static class Person {
        private String name;
        private String age;

        public Person(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

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
