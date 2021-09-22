package com.trevzhang.demo.test.model;

/**
 * @author 春火
 * @since 2021/9/22 11:25 上午
 */
public class SuperBean {

    public void hello(String name) {
        this.say(name);
        this.self();
    }

    public void self() {
        System.out.println("I'm super bean");
    }

    public void say(String name) {
        System.out.println("hello super " + name);
    }
}
