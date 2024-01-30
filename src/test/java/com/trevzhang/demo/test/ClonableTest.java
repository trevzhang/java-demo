package com.trevzhang.demo.test;

import lombok.Getter;
import org.junit.Test;

import java.io.*;

/**
 * @author Murakami Haruki
 * @since 2024/1/30 18:01
 */
public class ClonableTest {
    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void deepCopyBenchmark() throws IOException, ClassNotFoundException {
        Address address = new Address("New York");
        Person person1 = new Person("Alice", address);
        int num = 10000;

        long startTime = System.nanoTime();
        for (int i = 0; i < num; i++) {
            Person person2 = person1.deepCopy();
        }
        long endTime = System.nanoTime();

        long nanoDuration = endTime - startTime;
        long duration = nanoDuration / 1000000;  // milliseconds
        System.out.println("Deep copy duration: " + duration + " ms");
        System.out.println("Deep copy duration of 1 person " + nanoDuration / num + " nano seconds.");
    }

    /**
     * 浅拷贝
     */
    @Test
    public void shallowCopyBenchmark() throws CloneNotSupportedException {
        Address address = new Address("New York");
        Person person1 = new Person("Alice", address);
        int num = 10000;

        long startTime = System.nanoTime();
        for (int i = 0; i < num; i++) {
            Person person2 = person1.clone();
        }
        long endTime = System.nanoTime();

        long nanoDuration = endTime - startTime;
        long duration = nanoDuration / 1000000;  // milliseconds
        System.out.println("Shallow copy duration: " + duration + " ms");
        System.out.println("Shallow copy duration of 1 person " + nanoDuration / num + " nano seconds.");
    }

    @Test
    public void testDeepCopy() throws IOException, ClassNotFoundException {
        Address address = new Address("New York");
        Person person1 = new Person("Alice", address);
        Person person2 = person1.deepCopy();

        System.out.println(person1.getAddress() == person2.getAddress()); // Output: false
    }

    static class Person implements Serializable, Cloneable {
        private String name;
        @Getter
        private Address address;

        public Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public Person deepCopy() throws IOException, ClassNotFoundException {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bis);
            return (Person) in.readObject();
        }

        @Override
        public Person clone() throws CloneNotSupportedException {
            return (Person) super.clone();
        }
    }

    static class Address implements Serializable, Cloneable {
        private String city;

        public Address(String city) {
            this.city = city;
        }

        @Override
        public Address clone() throws CloneNotSupportedException {
            return (Address) super.clone();
        }
    }
}
