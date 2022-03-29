package com.trevzhang.demo.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射测试类
 *
 * @author zhangchunguang.zcg
 * @since 2022/3/29 11:12 AM
 */
public class ReflectionTest {
    public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        Method m = A.class.getDeclaredMethod("getName");
        System.out.println("getName.isAccessible: " + m.isAccessible());
        // getName是public的,猜猜输出是true还是false
        A a = new A();
        a.setName("Mr Lee");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            m.invoke(a);
        }
        System.out.println("Simple              :" + (System.currentTimeMillis() - start));
        // 关闭JDK安全检查，提升反射速度
        m.setAccessible(true); // 注意此处不同
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            m.invoke(a);
        }
        System.out.println("setAccessible(true) :" + (System.currentTimeMillis() - start1));
    }

    static class A {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
