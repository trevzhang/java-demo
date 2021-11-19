package com.trevzhang.demo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 探索stream的深层原理
 */
public class StreamDemo extends HashMap {

    @Override
    public Object putIfAbsent(Object key, Object value) {
        System.out.println(Thread.currentThread().getName());
        return super.putIfAbsent(key, value);
    }

    public static void main(String[] args) {
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
