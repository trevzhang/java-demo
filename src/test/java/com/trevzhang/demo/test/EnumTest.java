package com.trevzhang.demo.test;

import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2022/12/6 2:49 PM
 */
public class EnumTest {

    /**
     * Enum.valueOf(Class,String) 返回指定枚举类型中匹配的枚举
     * 注意：如果没有匹配到的枚举类型，会抛出java.lang.IllegalArgumentException异常
     */
    @Test
    public void testEnumValueOf() {
        AnimalEnum animalEnum = Enum.valueOf(AnimalEnum.class, "TIGER");
        System.out.println(animalEnum);
    }

    /**
     * AnimalEnum.valueOf(String) 等价于 Enum.valueOf(AnimalEnum.class, String)
     */
    @Test
    public void testEnumValueOf2() {
        AnimalEnum animalEnum = AnimalEnum.valueOf("TIGER");
        System.out.println(animalEnum);
    }

    public enum AnimalEnum {
        TIGER,
        LION;
    }
}
