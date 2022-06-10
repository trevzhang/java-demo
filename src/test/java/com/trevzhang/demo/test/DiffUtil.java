package com.trevzhang.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author zhangchunguang.zcg
 * @since 2022/6/10 11:10 AM
 */
public class DiffUtil {

    private static String odpsCounted = "";

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/zcg/Downloads/javaCounted.txt");
        FileInputStream input = new FileInputStream(file);
        byte[] bytes = new byte[input.available()];
        int offset = input.read(bytes);
        if (offset < input.available()) {
            throw new RuntimeException("未能完全读取资质文件");
        }
        input.close();
        String inputStr = new String(bytes);

        JSONArray javaCountedList = JSON.parseArray(inputStr);
        System.out.println(javaCountedList.size());
    }
}
