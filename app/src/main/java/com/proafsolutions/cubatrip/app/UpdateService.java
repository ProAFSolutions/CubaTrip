package com.proafsolutions.cubatrip.app;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.proafsolutions.cubatrip.artifacts.Constants;
import com.proafsolutions.cubatrip.infrastructure.connection.InternetConnectionDetector;

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
        Log.i("info", "**************Sending review information to the server******************");
        this.stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("info", "********************Background Service Destroyed**************************");
        unregisterReceiver(_eventsReceiver);
    }

    private class EventsReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch(action){
                case Constants.ANDROID_ACTION_CONNECTIVITY_CHANGE:{
                    boolean internetConnection =  InternetConnectionDetector.isConnected(context);
                    Log.i("info", "********************Connection "+ internetConnection +"**************************");
                    if(internetConnection){
                        sendReviewsToServer();
                    }
                } break;
                //TODO Add new actions below
            }
        }
    }
}
