package com.proafsolutions.cubatrip.infrastructure.io;

import android.os.Environment;

import com.proafsolutions.cubatrip.infrastructure.config.Constants;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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

    public static File getUpdateFolder(){
        return new File(getAppFolder(), Constants.FOLDER_APP_UPDATE);
    }

    public static File getResourcesFolder(){
        return new File(getAppFolder(), Constants.FOLDER_APP_RESOURCES);
    }

    public static File createResourceFolder(String folderName){
        File folder = new File(getResourcesFolder(), folderName);
        if(!folder.exists()){
            folder.mkdir();
        }
        return folder;
    }

    public static File loadFile(String relativeFilePath){
       return  new File(getAppFolder(), relativeFilePath);
    }

    public static void downloadResourcesFromURL(String url, File targetFile) throws IOException {
        FileUtils.copyURLToFile(new URL(url), targetFile);
    }

    public static void deleteFolder(File folder) throws IOException {
        FileUtils.deleteDirectory(folder);
    }
}
