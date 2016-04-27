package com.proafsolutions.cubatrip.ui.util;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.CategoryEnum;

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


}
