package com.trevzhang.demo.test.enums;

import java.util.Objects;

/**
 * @author zhangchunguang.zcg
 * @since 2022/12/15 2:54 PM
 */
public enum CustomerCategoryEnum {
    INTERNAL_GROUP_TOUR(1, "国内跟团游"),
    INTERNAL_FREE_TOUR(2, "国内自由行"),

    /**
     * 原先酒店改为境内酒店
     */
    HOTEL(3, "境内酒店"),

    /**
     * 类目新增
     */
    HOTEL_INTERNATIONAL(4, "境外酒店"),
    TICKET(5, "门票"),
    HOTEL_AND_POI_DOMESTIC(6, "境内酒景酒套"),
    HOTEL_AND_POI_INTERNATIONAL(7, "境外酒景酒套"),
    VISA(8, "签证"),
    ;

    CustomerCategoryEnum(int code, String category) {
        this.code = code;
        this.category = category;
    }

    private Integer code;
    private String category;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static CustomerCategoryEnum getByCode(Integer code) {
        for (CustomerCategoryEnum value : CustomerCategoryEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }

    /**
     * 根据code获取category
     * @param code code值
     * @return category名称
     */
    public static String getNameByCode(Integer code) {
        if (code == null) {
            return "";
        }
        for (CustomerCategoryEnum value : CustomerCategoryEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value.getCategory();
            }
        }
        return "";
    }
}