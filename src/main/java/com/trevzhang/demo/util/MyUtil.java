package com.trevzhang.demo.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 个人使用的一些工具类
 * @author Trevor Zhang
 * @since 2020/10/31
 */
public class MyUtil {
    /**
     * 将对象按某一属性去重
     * @apiNote 建议: 若需要按多个属性同时去重: <br>
     *              1) 需要同时满足所有属性时(与), 采用Stream.of(attr1, attr2, ...)提供key<br>
     *              2) 需要只要满足任一个提供的属性时(或), 连续调用本方法即可<br>
     * @param keyExtractor 处理T类型,提取出需要去重的属性
     * @param <T>          对象类型
     * @return 是否见过该属性(是否重复了)
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
