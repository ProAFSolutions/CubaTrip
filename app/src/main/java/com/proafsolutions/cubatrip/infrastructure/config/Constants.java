package com.proafsolutions.cubatrip.infrastructure.config;

import android.net.ConnectivityManager;

/**
 * Created by alex on 4/16/2016.
 */
public interface Constants {

    //Activity Parameters
    public static final String ACTIVITY_PARAM_LONGITUDE = "longitude";
    public static final String ACTIVITY_PARAM_LATITUDE = "latitude";
    public static final String ACTIVITY_PARAM_TARVEL_MODE = "car";

    //Actions
    public static final String ANDROID_ACTION_CONNECTIVITY_CHANGE = ConnectivityManager.CONNECTIVITY_ACTION;

    //Remote Server
    public static final String WEB_API_BASE_URL = "http://localhost:60503/";
    public static final String WEB_API_USERNAME= "proafasolutions";
    public static final String WEB_API_PASSWORD= "CUB@trip_Pr0@FS0luti0ns2016";

    //app folder name
    public static final String APP_FOLDER_NAME = "cubatrip";

    // database
    public static final String DATABASE_NAME = "cubatrip.db";

    // maps
    public static final String MAPS_FOLDER = "maps";
    public static final String MAP_FILE = "cuba.map";
    public static final String MAP_OSM_FILE = "cuba-gh";
    public static final String MAP_TAVEL_MODE_CAR= "car";
    public static final String MAP_TAVEL_MODE_FOOT= "foot";
    public static final String MAP_TAVEL_MODE_BIKE= "bike";
}
