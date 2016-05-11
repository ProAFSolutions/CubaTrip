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

    //Remote Server
    public static final String WEB_API_BASE_URL = "http://localhost:60503/";
    public static final String WEB_API_USERNAME= "proafasolutions";
    public static final String WEB_API_PASSWORD= "CUB@trip_Pr0@FS0luti0ns2016";

    //app folders
    public static final String FOLDER_APP = "cubatrip";
    public static final String FOLDER_APP_UPDATE = "update";
    public static final String FOLDER_APP_MAP = "maps";
    public static final String FOLDER_APP_RESOURCES = "resources";

    // database
    public static final String DATABASE_NAME = "cubatrip.db";
    public static final int DATABASE_VERSION = 1;

    // maps
    public static final String MAP_FILE = "florida.map";
    public static final String MAP_CACHE = "mapcache";

    //Routes
    public static final String MAP_ROUTES_GH_FOLDER = "florida-gh";
    public static final String MAP_ROUTES_TAVEL_MODE_CAR = "car";
    public static final String MAP_ROUTES_TAVEL_MODE_FOOT = "foot";
    public static final String MAP_ROUTES_TAVEL_MODE_BIKE = "bike";

    public static final String MAP_ROUTES_WEIGHTING_FASTEST = "fastest";
    public static final String MAP_ROUTES_WEIGHTING_SHORTEST = "shortest";

    public static final boolean MAP_ROUTES_DIRECTION_ON = true;
}
