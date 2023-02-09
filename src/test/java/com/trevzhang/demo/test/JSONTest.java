package com.trevzhang.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.TypeReference;
import java.util.Map;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2022/4/24 4:42 PM
 */
public class JSONTest {

    @Test
    public void testJsonPathEval() {
        String jsonDemo = "{\n"
            + "    \"status\": 200,\n"
            + "    \"msg\": \"成功\",\n"
            + "    \"data\":\n"
            + "    {\n"
            + "        \"name\": \"Smith\",\n"
            + "        \"age\": 15,\n"
            + "        \"address\": \"青岛\"\n"
            + "    }\n"
            + "}";

        String jsonArrayDemo = "[\n"
            + "        {\n"
            + "            \"name\": \"Lucy\",\n"
            + "            \"age\": 10,\n"
            + "            \"address\": \"上海\"\n"
            + "        },\n"
            + "        {\n"
            + "            \"name\": \"Tom\",\n"
            + "            \"age\": 12,\n"
            + "            \"address\": \"北京\"\n"
            + "        },\n"
            + "        {\n"
            + "            \"name\": \"Smith\",\n"
            + "            \"age\": 15,\n"
            + "            \"address\": \"青岛\"\n"
            + "        },\n"
            + "        {\n"
            + "            \"name\": \"Jerry\",\n"
            + "            \"age\": 21,\n"
            + "            \"address\": \"秦皇岛\"\n"
            + "        }\n"
            + "    ]\n";
        JSONObject jsonObject = JSONObject.parseObject(jsonDemo);
        String path = "$.data.name";
        String response = (String) JSONPath.eval(jsonObject, path);
        System.out.printf("路径：%s，单个提取：%s\n", path, response);

        JSONArray jsonArray = JSON.parseArray(jsonArrayDemo);
        String arrayPath = "[1].name";
        String responseArray = (String)JSONPath.eval(jsonArray, arrayPath);
        System.out.printf("路径：%s，单个提取：%s\n", arrayPath, responseArray);
    }

    @Test
    public void testGetBoolean() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("canDawnBook", false);
        System.out.println(jsonObject);

        Boolean canDawnBook = jsonObject.getBoolean("canDawnBook");
        System.out.println(canDawnBook);
    }

    @Test
    public void testJSONClassParse() {
        // @type为JSONObject，反序列化为Map<String,Object>成功
        String jsonStr1 = "{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"signAlipayAgent\":false}";
        Map<String, Object> map = JSON.parseObject(jsonStr1, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map);

        // @type为HashMap，反序列化为JSONObject成功
        String jsonStr2 = "{\"@type\":\"java.util.HashMap\",\"signAlipayAgent\":true}";
        JSONObject jsonObject = JSON.parseObject(jsonStr2, JSONObject.class);
        System.out.println(jsonObject);

        // @type为JSONObject，反序列化为Map<String,String>成功
        String jsonStr3 = "{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"signAlipayAgent\":false}";
        Map<String, String> map3 = JSON.parseObject(jsonStr3, new TypeReference<Map<String, String>>() {
        });
        System.out.println(map3);
    }

    @Test
    public void testJSONObjectParse() {
        // @type是JSONObject，能正常反序列化为JSONObject
        String jsonStr1 = "{\"@type\":\"com.alibaba.fastjson.JSONObject\",\"signAlipayAgent\":false}";
        JSONObject extendInfo1 = JSON.parseObject(jsonStr1);
        System.out.println(extendInfo1.toJSONString());
        // @type不是JSONObject，不会影响反序列化为JSONObject
        String jsonStr2 = "{\"@type\":\"java.util.HashMap\",\"signAlipayAgent\":true}";
        JSONObject extendInfo2 = JSON.parseObject(jsonStr2);
        System.out.println(extendInfo2.toJSONString());
        // @type为空，也能正常反序列化为JSONObject
        String jsonStr3 = "{\"signAlipayAgent\":true}";
        JSONObject extendInfo3 = JSON.parseObject(jsonStr3);
        System.out.println(extendInfo3.toJSONString());
        // @type为不存在的类，会报错
        String jsonStr4 = "{\"@type\":\"com.trevzhang.Bean\",\"signAlipayAgent\":true}";
        JSONObject extendInfo4 = JSON.parseObject(jsonStr4);
        System.out.println(extendInfo4.toJSONString());
    }

    @Test
    public void testJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("agreementNo", "202204240627001");

        System.out.println((jsonObject.get("agreementNo")).getClass());

        String agreementNo = (String) jsonObject.get("agreementNo");
        System.out.println("agreementNo: " + agreementNo);
    }
}
