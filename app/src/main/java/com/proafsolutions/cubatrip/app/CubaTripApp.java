package com.proafsolutions.cubatrip.app;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by alex on 4/15/2016.
 */
public class CubaTripApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        new AppDatasetExample(false);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
