package com.trevzhang.demo.test.model;

/**
 * @author 春火
 * @since 2021/9/22 11:25 上午
 */
public class Bean extends SuperBean{

    @Override
    public void say(String name) {
        System.out.println("hello " + name);
    }

    @Override
    public void self() {
        System.out.println("I'm bean");
    }
}
