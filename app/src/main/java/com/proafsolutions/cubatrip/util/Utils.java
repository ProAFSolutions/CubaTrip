package com.proafsolutions.cubatrip.util;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.RateEnum;

/**
 * Created by Fily on 4/24/2016.
 */
public class Utils {
    public static CategoryEnum GetCategoryFromResourceId(int id)
    {
        switch (id)
        {
            case R.id.imgRestaurants:
            {
                return CategoryEnum.RESTAURANTS;
            }
            case R.id.imgHotel:
            {
                return CategoryEnum.HOTEL;
            }

            case R.id.imgEducation:
            {
                return CategoryEnum.EDUCATION;
            }
        }

        return CategoryEnum.RESTAURANTS;
    }

    public static RateEnum GetRateEnum(int id)
    {
        switch (id)
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
