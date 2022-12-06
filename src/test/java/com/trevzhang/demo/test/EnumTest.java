package com.trevzhang.demo.test;

import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2022/12/6 2:49 PM
 */
public class EnumTest {

    /**
     * Enum.valueOf(Class,String) 返回指定枚举类型中匹配的枚举
     */
    @Test
    public void testEnumValueOf() {
        AnimalEnum animalEnum = Enum.valueOf(AnimalEnum.class, "TIGER");
        System.out.println(animalEnum);
    }

    public enum AnimalEnum {
        TIGER,
        LION;
    }
}
