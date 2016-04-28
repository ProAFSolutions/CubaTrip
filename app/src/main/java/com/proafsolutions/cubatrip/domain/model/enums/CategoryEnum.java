package com.proafsolutions.cubatrip.domain.model.enums;

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


}
