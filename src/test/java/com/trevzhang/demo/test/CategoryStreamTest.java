package com.trevzhang.demo.test;

import com.trevzhang.demo.test.enums.CustomerCategoryEnum;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

/**
 * @author zhangchunguang.zcg
 * @since 2022/12/15 2:54 PM
 */
public class CategoryStreamTest {

    @Test
    public void testParseCategories() {
        List<CustomerCategoryEnum> customerCategoryList = new ArrayList<>();
        customerCategoryList.add(CustomerCategoryEnum.HOTEL);
        customerCategoryList.add(CustomerCategoryEnum.TICKET);
        customerCategoryList.add(CustomerCategoryEnum.VISA);
        if (CollectionUtils.isEmpty(customerCategoryList)) {
            return;
        }
        String categories = customerCategoryList.stream().map(CustomerCategoryEnum::getCode)
            .map(String::valueOf)
            .reduce((a, b) -> a + "," + b).orElse("");
        System.out.println(categories);
    }
}
