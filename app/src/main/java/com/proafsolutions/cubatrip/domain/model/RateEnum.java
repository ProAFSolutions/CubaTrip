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
