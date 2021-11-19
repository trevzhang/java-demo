package com.trevzhang.demo.test;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.trevzhang.demo.test.StreamDemo.Bean;
import java.util.regex.Pattern;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangchunguang.zcg
 * @since 2021/11/11 4:41 下午
 */
public class RegexTest {

    public static void main(String[] args) {
        String certNoRegex = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        String certNo = "410702199606270012";
        boolean isMatch = Pattern.matches(certNoRegex, certNo);
        System.out.println("isMatch: "+isMatch);

        String certNameRegex= "^[\\u4e00-\\u9fa5]+(·[\\u4e00-\\u9fa5]+)*$";
        String certName = "张春光·慕斯";
        boolean isCertNameMatch = Pattern.matches(certNameRegex, certName);
        System.out.println("isCertNameMatch: "+isCertNameMatch);

        String phoneRegex = "^(([0-9]{3,4}-)?[0-9]{7,8}|(1[3456789]\\d{9}))$";
        String phone = "19901010101";
        boolean isPhoneMatch = Pattern.matches(phoneRegex, phone);
        System.out.println("isPhoneMatch: " + isPhoneMatch);

        String userNameRegex = "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$";
        String userName = "chin627$";
        boolean isUserNameMatch = Pattern.matches(userNameRegex, userName);
        System.out.println("isUserNameMatch: " + isUserNameMatch);

    }
}
