package com.proafsolutions.cubatrip.infrastructure.io;

import android.os.Environment;

import com.proafsolutions.cubatrip.infrastructure.config.Constants;

import java.io.File;

/**
 * Created by alejandro.clavijo on 4/27/2016.
 */
public class FileManager {

    public static File getAppFolder(){
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), Constants.FOLDER_APP);
    }

    public static File getMapFolder(){
        return new File(getAppFolder(), Constants.FOLDER_APP_MAP);
    }

    public static File getDatabaseFolder(){
        return new File(getAppFolder(), Constants.FOLDER_APP_DATABASE);
    }

    public static File getResourcesFolder(){
        return new File(getAppFolder(), Constants.FOLDER_APP_RESOURCES);
    }

    public static File loadFile(String fileRelativePath){
       return  new File(getAppFolder(), fileRelativePath);
    }


}
