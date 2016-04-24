package com.proafsolutions.cubatrip.infrastructure.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.proafsolutions.cubatrip.artifacts.Constants;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alex on 4/19/2016.
 */
public class InternetConnectionDetector {

    public static boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
