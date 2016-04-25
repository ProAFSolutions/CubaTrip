package com.proafsolutions.cubatrip.domain.model;

/**
 * Created by alex on 4/24/2016.
 */
public enum RateEnum {

    NONE(0),
    VERY_BAD(1),
    BAD(2),
    AVERAGE(3),
    GOOD(4),
    EXCELENT(5);

    private int rate;

    RateEnum(int rate){
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
