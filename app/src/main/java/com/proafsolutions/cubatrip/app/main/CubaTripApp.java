package com.proafsolutions.cubatrip.app.main;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.proafsolutions.cubatrip.app.setup.ApplicationSetup;


/**
 * Created by alex on 4/15/2016.
 */
public class CubaTripApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationSetup.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

}
