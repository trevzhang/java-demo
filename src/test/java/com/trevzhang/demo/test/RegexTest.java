package com.trevzhang.demo.test;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.trevzhang.demo.test.StreamDemo.Bean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangchunguang.zcg
 * @since 2021/11/11 4:41 下午
 */
public class RegexTest {

    @Test
    public void testOssKey() {
        Pattern PATTERN = Pattern.compile("axFiles/(\\w*)/(.*?)");
        String key = "axFiles/ROUTE_FOREIGN_IMAGE/2023/07/06/65027239ca8448fb9772e4931d9339ed.jpeg";
        Matcher matcher = PATTERN.matcher(key);
        if (matcher.matches() && matcher.groupCount() > 1) {
            System.out.println(matcher.group(1));
        }
    }

    @Test
    public void testPerson() {
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

    @Test
    public void testDateFormat() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}");
        String a = "1996-06-27";
        System.out.println(pattern.matcher(a).matches());
        Date aDate = sdf.parse(a);
        System.out.println(aDate);

        String b = "1996-6-8";
        System.out.println(pattern.matcher(b).matches());
        Date bDate = sdf.parse(b);
        System.out.println(bDate);
    }
}
