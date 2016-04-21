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
    public static final String WEB_SERVICE_URL = "http://localhost:60503/api/CubaTrip/%s";

    // name of database
    public static final String DATABASE_NAME = "cuba_trip.db";

    // name of the map file
    public static final String MAPFILE = "cuba.map";
}
