package com.trevzhang.demo.designpattern.factory;

/**
 * @author zhangchunguang.zcg
 * @since 2022/3/7 2:12 PM
 */
public class Tiger implements IAnimal {
    @Override
    public String getType() {
        return "tiger";
    }

    @Override
    public void speak() {
        System.out.println("Waw, I'm a tiger.");
    }
}
