package com.proafsolutions.cubatrip.app.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


/**
 * Created by alex on 5/1/2016.
 */
public class InternetConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            boolean internetConnection = checkConnection(context);
            if(internetConnection){
                Log.i("InternetConnectionReceiver", "Internet Connection Detected!!!");
                Log.i("InternetConnectionReceiver", "Starting Review Update Service");
               // context.startService(new Intent(context, ReviewsUpdateService.class));
            }
        }
    }

    private boolean checkConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
