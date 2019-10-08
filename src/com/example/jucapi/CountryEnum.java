package com.example.jucapi;

/**
 * @author Administrator
 * @title: CountryEnum
 * @projectName interview-demo
 * @description: TODO 国家枚举类
 * @date 2019/10/2 0002下午 5:42
 */
public enum CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "韩"), FOUR(4, "燕"), FIVE(5, "赵"), SIX(6, "魏");

    private Integer countryCode;
    private String countryName;

    public Integer getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    CountryEnum(Integer countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    // 根据索引返回对应枚举
    public static CountryEnum forEach_CountryEnum(int index) {

        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum countryEnum : countryEnums) {
            if (index == countryEnum.getCountryCode()) {
                return countryEnum;
            }
        }
        return null;
    }
}
