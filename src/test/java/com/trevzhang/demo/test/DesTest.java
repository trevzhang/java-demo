package com.trevzhang.demo.test;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Haruki
 * @since 2024/9/30 10:57
 */
@Slf4j
public class DesTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String json = "{\n"
                + "  \"username\": \"username\",\n"
                + "  \"role_name\": \"某角色\",\n"
                + "  \"zone_id\": 0,\n"
                + "  \"out_trade_no\": \"1728281725800\",\n"
                + "  \"money\": 1,\n"
                + "  \"game_money\": 1,\n"
                + "  \"currency\": \"CNY\",\n"
                + "  \"product_id\": \"game.center.pom.package.1\",\n"
                + "  \"product_name\": \"泡姆豪华版\",\n"
                + "  \"product_list\": \"[{\\\"product_id\\\":\\\"pom.body.1\\\",\\\"product_name\\\":\\\"泡姆本体\\\"},{\\\"product_id\\\":\\\"pom.dlc.1\\\",\\\"product_name\\\":\\\"泡姆DLC1\\\"},{\\\"product_id\\\":\\\"pom.dlc.2\\\",\\\"product_name\\\":\\\"泡姆DLC2\\\"}]\",\n"
                + "  \"extension_info\": \"10004|serverId|{\\\"test\\\":false}\",\n"
                + "  \"notify_url\": \"http://localhost:8080/mock/game_center/notify\",\n"
                + "  \"order_sign\": \"d9610a64cf77aef34c7ed6d87e8ef11f\",\n"
                + "  \"uid\": \"8803412433651689\",\n"
                + "  \"timestamp\": 1728282084390,\n"
                + "  \"trace_id\": \"123\",\n"
                + "  \"request_id\": \"123\",\n"
                + "  \"sign\": \"95fdaaac210a76892102bae7c0870d6b\",\n"
                + "  \"appkey\": \"7XUmCk1bbRNC2r\",\n"
                + "  \"game_base_id\": 14,\n"
                + "  \"sdk_type\": 11\n"
                + "}";
        Map<String, String> map = new Gson().fromJson(json, (new TypeToken<Map<String, String>>() {
        }).getType());
        TreeMap<String, String> paramsTreeMap = new TreeMap<>(map);
        System.out.println(checkSign3(paramsTreeMap, "rfQPDgCSC7EhFi501Tw","95fdaaac210a76892102bae7c0870d6b"));
    }

    public static boolean checkSign3(Map<String, String> params, String appSecret, String sign)
            throws UnsupportedEncodingException {
        TreeMap<String, String> treeMap = Maps.newTreeMap();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            treeMap.put(key, params.get(key));
        }
        return checkSign2(treeMap, appSecret, sign);
    }

    public static boolean checkSign2(TreeMap<String, String> params, String appSecret, String sign)
            throws UnsupportedEncodingException {
        //sign不参与签名
        params.remove("sign");
        String doSign = getSign(params, appSecret);
        return sign.equals(doSign);
    }

    public static String getSign(TreeMap<String, String> paramsTreeMap, String appSecret) throws UnsupportedEncodingException {
        String signCalc = "";
        for (Map.Entry<String, String> entry : paramsTreeMap.entrySet()) {
            String key = entry.getKey(); // map中的key
            String value;
            value = String.valueOf(entry.getValue());
            signCalc = String.format("%s%s=%s&", signCalc, key, encodedFix(URLEncoder.encode(value, "UTF-8")), "&");
        }
        if (!signCalc.isEmpty()) {
            signCalc = signCalc.substring(0, signCalc.length() - 1);
        }

        signCalc = String.format("%s%s", signCalc, appSecret);
        String sign = DigestUtils.md5Hex(signCalc);
        log.info("get sign, sign_str: {}, sign:{}", signCalc, sign);

        return sign;
    }

    private static String encodedFix(String encoded) {
        // required
        encoded = encoded.replace("+", "%20");
        encoded = encoded.replace("*", "%2A");
        encoded = encoded.replace("%7E", "~");

        // optional
        encoded = encoded.replace("!", "%21");
        encoded = encoded.replace("(", "%28");
        encoded = encoded.replace(")", "%29");
        encoded = encoded.replace("'", "%27");
        return encoded;
    }
}
