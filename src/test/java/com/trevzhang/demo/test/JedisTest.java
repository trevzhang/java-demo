package com.trevzhang.demo.test;

import java.util.List;
import java.util.Set;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author zhangchunguang.zcg
 * @since 2023/4/13 09:53
 */
public class JedisTest {

    private static Jedis jedis = new Jedis("127.0.0.1", 6379);

    @Test
    public void testPing() {
        String value = jedis.ping();
        System.out.println("Ping: " + value);
    }

    //string
    @Test
    public void testString() {
        String value = jedis.ping();
        System.out.println(value);
        //添加
        jedis.set("name", "GooReey");
        //获取
        String name = jedis.get("name");
        System.out.println(name);

        jedis.set("age", "30");
        jedis.set("city", "dalian");
        //获取全部的key
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key + " --> " + jedis.get(key));
        }

        //加入多个key和value
        jedis.mset("name1", "zs", "name2", "ls", "name3", "ww");
        List<String> mget = jedis.mget("name1", "name2");
        System.out.println(mget);//[zs, ls]
    }

    //list
    @Test
    public void testList() {
        jedis.lpush("key1", "01", "02", "03");
        List<String> values = jedis.lrange("key1", 0, -1);
        System.out.println(values);//[03, 02, 01]
    }

    //set
    @Test
    public void testSet() {
        jedis.sadd("username", "zs", "ls", "ww");
        Set<String> names = jedis.smembers("username");
        System.out.println(names);//[ww, zs, ls]
    }

    //hash
    @Test
    public void testHash() {
        jedis.hset("users", "age", "20");
        String hget = jedis.hget("users", "age");
        System.out.println(hget);
    }

    //zset
    @Test
    public void testSortedSet() {
        jedis.zadd("china", 100d, "shanghai");
        Set<String> names = jedis.zrange("china", 0, -1);
        System.out.println(names);//[shanghai]
    }
}