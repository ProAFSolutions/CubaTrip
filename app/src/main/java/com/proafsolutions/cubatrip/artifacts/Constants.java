package com.proafsolutions.cubatrip.artifacts;

import android.net.ConnectivityManager;

/**
 * Created by alex on 4/16/2016.
 */
public interface Constants {

    //Activities Parameters
    public static final String ACTIVITY_PARAM_LONGITUDE = "param1";
    public static final String ACTIVITY_PARAM_LATITUDE = "param2";
    public static final String ACTIVITY_PARAM_TEST = "param3";

    //Android Actions
    public static final String ANDROID_ACTION_CONNECTIVITY_CHANGE = ConnectivityManager.CONNECTIVITY_ACTION;

    //Remote Server Settings
    public static final String SERVER_URL = "http://www.google.com/";

    // name of database
    public static final String DATABASE_NAME = "cuba_trip.db";

    // name of the map file
    public static final String MAPFILE = "cuba.map";

    public static enum Categories {
        Restaurants,
        Nightlife,
        Shopping,
        Sport,
        Art,
        Automotive,
        Beauty,
        Education,
        Events,
        Medical,
        Services,
        Hotel,
        Pets,
        Gas,
        Wifi
    }
}
