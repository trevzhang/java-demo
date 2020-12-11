package com.trevzhang.demo.test;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 探索stream的深层原理II
 */
public class StreamDemo2 {

    public static void main(String[] args) {
        List<Bean2> beanList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            beanList.add(new Bean2<>(RandomUtil.randomInt(10), RandomUtil.randomInt(1, 3)));
        }
        System.out.println(beanList);

        Map<Object, List<Bean2>> beanMap = beanList.stream().collect(Collectors.groupingBy(bean ->
            Arrays.asList(bean.getArg1(), bean.getArg2())
        ));

        System.out.println(JSONUtil.parse(beanMap));
    }

    static class Bean2<T> {

        private T arg1;
        private T arg2;

        public T getArg1() {
            return arg1;
        }

        public void setArg1(T arg1) {
            this.arg1 = arg1;
        }

        public T getArg2() {
            return arg2;
        }

        public Bean2(T arg1, T arg2) {
            this.arg1 = arg1;
            this.arg2 = arg2;
        }

        @Override
        public String toString() {
            return "Bean2{" +
                "arg1=" + arg1 +
                ", arg2=" + arg2 +
                '}';
        }
    }
}
