package com.trevzhang.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Base64;

/**
 * @author zhangchunguang.zcg
 * @since 2022/7/21 8:35 PM
 */
public class Base64Test {
    @Test
    public void test1() {
        byte[] decodeBytes=Base64.getDecoder().decode("YWJjMTIzNDU2Nw==");
        String decodeStr = new String(decodeBytes);
        System.out.println(decodeStr);
    }

    @Test
    public void test2() {
        String temp = "106.11.223.%s";
        for (int i = 1; i <= 255; i++) {
            String ip = String.format(temp, i);
            System.out.println(ip);
        }
    }

    @Test
    public void test3() {
        String data = "[{\"index\":0,\"value\":\"EUR\"},{\"index\":1,\"value\":\"HKD\"},{\"index\":2,\"value\":\"IDR\"},{\"index\":3,\"value\":\"JPY\"},{\"index\":4,\"value\":\"KRW\"},{\"index\":5,\"value\":\"MYR\"},{\"index\":6,\"value\":\"SGD\"},{\"index\":7,\"value\":\"THB\"},{\"index\":8,\"value\":\"USD\"},{\"index\":9,\"value\":\"VND\"},{\"index\":10,\"value\":\"TWD\"}]";
        JSONArray jsonArray = JSON.parseArray(data);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jsonArray.size(); i++) {
            String curr = jsonArray.getJSONObject(i).getString("value");
            sb.append("\""+curr+"\"");
            sb.append(",");
        }
        System.out.println(sb.substring(0, sb.length()-1));
    }
}
