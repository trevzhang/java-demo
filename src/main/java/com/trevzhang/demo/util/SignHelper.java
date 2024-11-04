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
        String secretKey = "wgfykwenagycuf92zzuragjm7mxhvytt";
        String data = "area_id=0&brand_id=101&global_channel_id=1001&global_game_id=1010001&timestamp=1730373550278&uid=2052462816608518&version=1&sign=edf2098022da5c7ddee7768eca12f32e";
        TreeMap<String, String> params = new TreeMap<>();
        String[] pairs = data.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            params.put(kv[0], kv[1]);
        }

        StringBuilder step1 = new StringBuilder();
        StringBuilder step2 = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if ("sign".equals(entry.getKey())) {
                continue;
            }
            step1.append(entry.getKey()).append("、");
            step2.append(entry.getValue()).append(" ");
        }
        String step1Str = step1.substring(0, step1.length() - 1);
        String step2Str = step2.toString();
        String step3Str = step2.append(secretKey).toString();

        System.out.println("1、参数字典序排序: " + step1Str);
        System.out.println("2、排序后参数值拼接顺序: " + step2Str);
        String sign = sign(params, secretKey);
        System.out.println("3、sign=md5(" + step3Str + ")=" + sign);
    }

}
