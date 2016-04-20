package com.proafsolutions.cubatrip.app;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.proafsolutions.cubatrip.artifacts.Constants;
import com.proafsolutions.cubatrip.infrastructure.connection.InternetConnectionDetector;

public class BackgroundService extends Service implements IUpdateService {

    private EventsReceiver _eventsReceiver;

    public BackgroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _eventsReceiver = new EventsReceiver();
    }

    @Override
    public void sendReviewsToServer() {
        Log.i("info", "Sending review information to the server");
        this.stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("info", "BackgrounfService Destroyed!!!!");
    }

    private class EventsReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch(action){
                case Constants.ANDROID_ACTION_CONNECTIVITY_CHANGE:{
                    boolean internetConnection =  InternetConnectionDetector.isConnected(context);
                    if(internetConnection){
                        sendReviewsToServer();
                    }
                } break;
                //TODO Add new actions below
            }
        }
    }
}
