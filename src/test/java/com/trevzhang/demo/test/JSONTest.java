package com.trevzhang.demo.test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhangchunguang.zcg
 * @since 2022/4/24 4:42 PM
 */
public class JSONTest {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("agreementNo", "202204240627001");

        System.out.println((jsonObject.get("agreementNo")).getClass());

        String agreementNo = (String) jsonObject.get("agreementNo");
        System.out.println("agreementNo: "+agreementNo);
    }
}
