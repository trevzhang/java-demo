package com.trevzhang.demo.test;

import com.trevzhang.demo.util.MaskUtil;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2023/8/28 11:26
 */
public class MaskUtilTest {

    @Test
    public void testMaskUtil() {
        System.out.println(MaskUtil.mosaicCustomerName("张"));
        System.out.println(MaskUtil.mosaicCustomerName("张三"));
        System.out.println(MaskUtil.mosaicCustomerName("张三丰"));
        System.out.println(MaskUtil.mosaicCustomerName("张三丰的"));
        System.out.println(MaskUtil.mosaicCustomerName("张三丰的公"));
        System.out.println(MaskUtil.mosaicCustomerName("张三丰的公司"));
    }
}
