package com.proafsolutions.cubatrip.domain.model;

/**
 * Created by alex on 4/21/2016.
 */
public enum CategoryEnum {

    RESTAURANTS(1, "Restaurants"),
    NIGHTLIFE(2, "Nightlife"),
    SHOPPING(3, "Shopping"),
    SPORT(4, "Sport"),
    ART(5, "Art"),
    AUTOMOTIVE(6, "Automotive"),
    BEAUTY(7, "Beauty"),
    EDUCATION(8, "Education"),
    EVENTS(9, "Events"),
    MEDICAL(10, "Medical"),
    SERVICES(11, "Services"),
    HOTEL(12, "Hotel"),
    PETS(13, "Pets"),
    GAS(14, "Gas"),
    WIFI(15, "Wifi");

    private int code;
    private String name;

    CategoryEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static RateEnum getRate(int code)
    {
        switch (code)
        {
            case 0:
            {
                return RateEnum.NONE;
            }
            case 1:
            {
                return RateEnum.VERY_BAD;
            }

            case 2:
            {
                return RateEnum.BAD;
            }
            case 3:
            {
                return RateEnum.AVERAGE;
            }
            case 4:
            {
                return RateEnum.EXCELENT;
            }
            case 5:
            {
                return RateEnum.GOOD;
            }
        }

        return RateEnum.NONE;
    }

}
