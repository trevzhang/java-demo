package com.trevzhang.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author zhangchunguang.zcg
 * @since 2022/6/10 11:10 AM
 */
public class DiffUtil2 {

    private static String odpsCounted = "";

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/zcg/Downloads/javaCountMap.txt");
        File file2 = new File("/Users/zcg/Downloads/odpsCountMap.txt");
        String inputStr = readFileString(file);
        String inputStr2 = readFileString(file2);

        Map<Long, Number> javaCountMap = JSON.parseObject(inputStr, Map.class);
        Map<Long, Number> odpsCountMap = JSON.parseObject(inputStr2, Map.class);

        for (Long subOrderId : odpsCountMap.keySet()) {
            Number javaCount = javaCountMap.get(subOrderId);
            if (javaCount == null) {
                continue;
            }
            Number odpsCount = odpsCountMap.get(subOrderId);
            if (!javaCount.equals(odpsCount)) {
                System.out.println("subOrderId:" + subOrderId + ",javaCount:" + javaCount + ",odpsCount:" + odpsCount);
            }
        }
    }

    private static String readFileString(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        byte[] bytes = new byte[input.available()];
        int offset = input.read(bytes);
        if (offset < input.available()) {
            throw new RuntimeException("未能完全读取资质文件");
        }
        input.close();
        String inputStr = new String(bytes);
        return inputStr;
    }
}
