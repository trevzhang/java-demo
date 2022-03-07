package com.trevzhang.demo.designpattern.factory;

/**
 * @author zhangchunguang.zcg
 * @since 2022/3/7 2:12 PM
 */
public class Panda implements IAnimal{
    @Override
    public String getType() {
        return "panda";
    }

    @Override
    public void speak() {
        System.out.println("Hi, I'm a panda.");
    }
}
