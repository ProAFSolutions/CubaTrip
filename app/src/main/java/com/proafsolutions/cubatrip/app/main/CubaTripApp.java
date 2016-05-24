package com.proafsolutions.cubatrip.app.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.proafsolutions.cubatrip.app.setup.AppDatasetExample;
import com.proafsolutions.cubatrip.infrastructure.io.FileManager;

import java.io.File;


/**
 * Created by alex on 4/15/2016.
 */
public class CubaTripApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initAppFolders();
        initDatabase();
        initExampleData();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void initDatabase(){
        ActiveAndroid.initialize(this);
    }

    private void initAppFolders(){
        FileManager.getAppFolder().mkdir();
        FileManager.getUpdateFolder().mkdir();
        FileManager.getMapFolder().mkdir();
        FileManager.getResourcesFolder().mkdir();
    }

    private void initExampleData(){
        new AppDatasetExample();
    }

}
