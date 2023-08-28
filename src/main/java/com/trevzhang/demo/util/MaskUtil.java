package com.trevzhang.demo.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangchunguang.zcg
 * @since 2023/8/28 11:26
 */
public class MaskUtil {

    public static String mosaicCustomerName(String name) {
        // 商户名称规则：
        // 名称为n位（n<=4）从第n位开始隐藏，最多展示3位，*展示3位；
        // 名称为2个字，则脱敏第2个字，例如王二，展示王***；
        // 名称为3个字，则脱敏第3个字，例如陈小二，展示陈小***；
        // 名称为4个字及以上，则脱敏第4个字及以后，例如中青旅杭州，展示中青旅***；
        if (StringUtils.isEmpty(name)) {
            return name;
        }
        char[] chars = name.toCharArray();
        int start = chars.length > 1 ? chars.length < 5 ? chars.length - 1 : 4 : 1;
        // 第一部分
        String str1 = name.substring(0, start);
        // 第二部分
        String str2 = "***";
        return str1 + str2;
    }
}
