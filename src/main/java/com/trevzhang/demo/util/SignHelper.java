package com.trevzhang.demo.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.TreeMap;

public class SignHelper {

    /**
     * 为请求参数生成签名
     *
     * @param params    基于字典排序的请求参数键值对
     * @param secretKey 用于签名的秘钥
     * @return 签名
     */
    public static String sign(TreeMap<String, String> params, String secretKey) {
        StringBuilder supposedSignBuilder = new StringBuilder();
        for (Map.Entry<String, String> item : params.entrySet()) {
            String key = item.getKey();
            String value = item.getValue();

            // 保留字段不参与签名
            if (skipOf(key)) {
                continue;
            }
            // 空值不参与签名
            if (value != null) {
                supposedSignBuilder.append(value);
            }

        }

        // 预期待签名的参数字符串+秘钥
        String supposedSignStr = supposedSignBuilder.append(secretKey).toString();
        // md5签名
        return DigestUtils.md5Hex(supposedSignStr);
    }

    private static boolean skipOf(String key) {
        return "item_name".equalsIgnoreCase(key) || "item_desc".equalsIgnoreCase(key) || "token".equalsIgnoreCase(key) || "sign".equalsIgnoreCase(key);
    }

    public static void main(String[] args) {
        String data = "area_id=0&brand_id=101&global_channel_id=1001&global_game_id=1000001&sign=7678f7381813af780950a958fe3ab382&timestamp=1729498917&uid=1234567&version=1";
        TreeMap<String, String> params = new TreeMap<>();
        String[] pairs = data.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            params.put(kv[0], kv[1]);
        }

        System.out.println("1、参数字典序排序: " + params.keySet().stream().reduce((k1, k2) -> k1 + "、" + k2).orElse(""));
        String step2 = params.values().stream().reduce((v1, v2) -> v1 + " " + v2).orElse("");
        System.out.println("2、排序后参数值拼接顺序: " + step2);
        String sign = sign(params, "pHcNbNb4vtvn8HXT");
        System.out.println("3、sign=md5(" + step2 + ")=" + sign);
    }

}
