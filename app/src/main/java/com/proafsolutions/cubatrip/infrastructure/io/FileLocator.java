package com.proafsolutions.cubatrip.infrastructure.io;

import android.os.Environment;

import com.proafsolutions.cubatrip.infrastructure.config.Constants;

import java.io.File;

/**
 * Created by alejandro.clavijo on 4/27/2016.
 */
public class FileLocator {

    public static File getAppFolderPath(){
        File appFolder =  new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), Constants.APP_FOLDER_NAME);
        return appFolder.exists() && appFolder.isDirectory() ? appFolder : null;
    }

    public static File loadFile(String folderRealativePath, String fileName){
       return  new File(getAppFolderPath(), folderRealativePath + "/" + fileName);
    }
}
