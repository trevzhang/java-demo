package com.trevzhang.demo.test;

import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2023/5/22 17:50
 */
public class MapTest {

    @Test
    public void testComputeIfAbsent() {
        Map<String, Set<String>> divisionMap = new HashMap<>();
        Set<String> china = new HashSet<>();
        china.add("Hangzhou");

        divisionMap.put("China", china);
        System.out.println(divisionMap);
        // computeIfAbsent: 先尝试通过指定key获取value，
        // 如果value为空，调用指定的mappingFunction生成对应value，再put回Map里，
        // 最后返回生成的value值。
        Set<String> val = divisionMap.computeIfAbsent("America", key -> Sets.newHashSet("New York", "Nevada"));
        System.out.println(val);
        Set<String> val1 = divisionMap.computeIfAbsent("India", key -> new HashSet<>());
        System.out.println(val1);

        System.out.println(divisionMap);
    }
}
