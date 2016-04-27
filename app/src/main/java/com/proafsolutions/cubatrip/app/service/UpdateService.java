package com.proafsolutions.cubatrip.app.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

import com.proafsolutions.cubatrip.infrastructure.config.Constants;

public class UpdateService extends Service implements IUpdateService {

    private EventsReceiver _eventsReceiver;

    public UpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initReceiver();
    }

    private void initReceiver(){
        _eventsReceiver = new EventsReceiver();

        IntentFilter aFilter = new IntentFilter();
        aFilter.addAction(Constants.ANDROID_ACTION_CONNECTIVITY_CHANGE);

        registerReceiver(_eventsReceiver, aFilter);
    }

    @Override
    public void sendReviewsToServer() {
        Log.i("UpdateService", "**************Sending review information to the server******************");
       // this.stopSelf();
    }

    @Override
    public void getReviewsFromServer() {
        Log.i("UpdateService", "**************Receiving reviews from server******************");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("UpdateService", "********************Background Service Destroyed**************************");
        unregisterReceiver(_eventsReceiver);
    }


    private class EventsReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch(action){
                case Constants.ANDROID_ACTION_CONNECTIVITY_CHANGE:{
                    boolean internetConnection =  checkConnection(context);
                    if(internetConnection){
                        sendReviewsToServer();
                    }
                } break;
                //TODO Add new actions below
            }
        }

        private boolean checkConnection(Context context){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
    }
}
