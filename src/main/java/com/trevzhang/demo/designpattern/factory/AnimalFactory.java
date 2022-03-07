package com.trevzhang.demo.designpattern.factory;

import java.net.URL;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

/**
 * @author zhangchunguang.zcg
 * @since 2022/3/7 2:13 PM
 */
public class AnimalFactory {
    private Map<String, Class<? extends IAnimal>> animalMap;

    @PostConstruct
    public void init() {
        Reflections reflections = new Reflections("com.trevzhang.demo.designpattern.factory");
        Set<Class<? extends IAnimal>> classSet = reflections.getSubTypesOf(IAnimal.class);
        System.out.println(classSet);
    }

    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();
        animalFactory.init();
    }
}
