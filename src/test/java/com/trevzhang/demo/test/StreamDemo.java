package com.trevzhang.demo.test;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

/**
 * 探索stream的深层原理
 */
public class StreamDemo extends HashMap {

    @Test
    public void test2() {
        List<Bean> list = new ArrayList<>();
        Bean<String> bean1 = new Bean<>();
        bean1.setArg1("1");
        list.add(bean1);

        Bean<String> bean2 = new Bean<>();
        bean2.setArg1("2");
        list.add(bean2);

        Bean<String> bean3 = new Bean<>();
        bean3.setArg1("3");
        list.add(bean3);

        Bean<String> bean11 = new Bean<>();
        bean11.setArg1("1");
        list.add(bean11);

        Map<Object, Bean> map1 = list.stream().collect(Collectors.toMap(
            Bean::getArg1, Function.identity(), (existing, replacement) -> existing));
        System.out.println(JSON.toJSONString(map1));
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        System.out.println(Thread.currentThread().getName());
        return super.putIfAbsent(key, value);
    }

    @Test
    public void test1() {
        StreamDemo demo = new StreamDemo();
        List<Bean> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Bean<>(String.valueOf(i)));
            list.add(new Bean<>(String.valueOf(i)));
            list.add(new Bean<>(String.valueOf(i)));
            list.add(new Bean<>(String.valueOf(i)));
            list.add(new Bean<>(String.valueOf(i)));
            list.add(new Bean<>(String.valueOf(i)));
            list.add(new Bean<>(String.valueOf(i)));
        }
        list.add(new Bean("1"));
//        List<Bean> list1 = list.stream().filter(demo.distinctByKey(Bean::getArg1)).collect(Collectors.toList());
        List<Bean> list1 = list.parallelStream().filter(demo.distinctByKey(Bean::getArg1)).collect(Collectors.toList());
//        list1.forEach(System.out::println);
    }

    @Test
    public void testSorted() {
        // List<Integer> intList = new ArrayList<>();
        // for (int i = 0; i < 20; i++) {
        //     intList.add(RandomUtil.randomInt(20));
        // }
        // intList = intList.stream().sorted((a, b) -> a > b ? 1 : a == b ? 0 : -1).collect(Collectors.toList());
        // System.out.println(intList);

        List<BoolBean> boolBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BoolBean boolBean = new BoolBean(RandomUtil.randomBoolean(), RandomUtil.randomNumbers(2));
            boolBeans.add(boolBean);
        }
        boolBeans = boolBeans.stream().sorted((a, b) -> a.getHighlight() ? -1 : b.getHighlight() ? 1 : 0).collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonPrettyStr(boolBeans));
    }

    @AllArgsConstructor
    @Data
    private static class BoolBean {
        private Boolean highlight;
        private String name;
    }

    /**
     * 将对象按某一属性去重
     *
     * @param keyExtractor 处理T类型,提取出需要去重的属性
     * @param <T>          对象类型
     * @return 是否见过该属性(是否重复了)
     * @apiNote 建议: 若需要按多个属性同时去重: <br> 1) 需要同时满足所有属性时(与), 采用Stream.of(attr1, attr2, ...)提供key<br> 2) 需要只要满足任一个提供的属性时(或),
     * 连续调用本方法即可<br>
     */
    private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new StreamDemo();
        System.out.println("初始化HashMap: " + seen);
        System.out.println(Thread.currentThread().getName());
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    static class Bean<T> {

        private T arg1;

        public Bean() {
        }

        public T getArg1() {
            return arg1;
        }

        public void setArg1(T arg1) {
            this.arg1 = arg1;
        }

        public Bean(T arg1) {
            this.arg1 = arg1;
        }

        @Override
        public String toString() {
            return "Bean{" +
                "arg1='" + arg1 + '\'' +
                '}';
        }
    }

}
