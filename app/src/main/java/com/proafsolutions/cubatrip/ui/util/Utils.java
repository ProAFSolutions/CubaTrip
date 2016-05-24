package com.proafsolutions.cubatrip.ui.util;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.enums.CategoryEnum;

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

            case R.id.imgNightlife:
            {
                return CategoryEnum.NIGHTLIFE;
            }

            case R.id.imgShopping:
            {
                return CategoryEnum.SHOPPING;
            }

            case R.id.imgSport:
            {
                return CategoryEnum.SPORT;
            }
            case R.id.imgArt:
            {
                return CategoryEnum.ART;
            }
            case R.id.imgAutomotive:
            {
                return CategoryEnum.AUTOMOTIVE;
            }
            case R.id.imgBeauty:
            {
                return CategoryEnum.BEAUTY;
            }

            case R.id.imgEvents:
            {
                return CategoryEnum.EVENTS;
            }

            case R.id.imgMedical:
            {
                return CategoryEnum.MEDICAL;
            }

            case R.id.imgServices:
            {
                return CategoryEnum.SERVICES;
            }
            case R.id.imgPets:
            {
                return CategoryEnum.PETS;
            }

            case R.id.imgGas:
            {
                return CategoryEnum.GAS;
            }

            case R.id.imgWifi:
            {
                return CategoryEnum.WIFI;
            }
        }

        return CategoryEnum.RESTAURANTS;
    }


}
