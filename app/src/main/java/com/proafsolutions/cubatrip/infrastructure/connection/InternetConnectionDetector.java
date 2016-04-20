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
        boolean isActive = activeNetwork != null && activeNetwork.isConnected();

        if(isActive){
            /*try {
                URL url = new URL(Constants.SERVER_URL);
                HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
                urlc.setRequestProperty("User-Agent", "test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1000); // mTimeout is in seconds
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                Log.e("error",e.getMessage());
                return false;
            }*/
            return true;
        }
        return false;
    }
}
