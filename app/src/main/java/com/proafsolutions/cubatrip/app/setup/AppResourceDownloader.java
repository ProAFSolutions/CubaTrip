package com.proafsolutions.cubatrip.app.setup;

import android.os.AsyncTask;
import android.util.Log;

import com.proafsolutions.cubatrip.infrastructure.config.GlobalConfig;
import com.proafsolutions.cubatrip.infrastructure.io.FileManager;
import com.proafsolutions.cubatrip.infrastructure.io.ZipManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by alejandro.clavijo on 4/28/2016.
 */
public class AppResourceDownloader extends AsyncTask<String, Void, File> {

    @Override
    protected File doInBackground(String... params) {
        String fileUrl = params[0];
        String provinceCode = params[1];
        String fileName = params[2];
        File download= null;
        try {
            GlobalConfig.downloadInProgress = true;
            File provinceFolder = FileManager.createResourceFolder(provinceCode);
            download = new File(provinceFolder, fileName + ".zip");
            FileManager.downloadResourcesFromURL(fileUrl, download);
        } catch (IOException e) {
            Log.e("AppResourceDownloader", e.getMessage(), e);
            download = null;
        }
        return download;
    }

    @Override
    protected void onPostExecute(File download) {
        GlobalConfig.downloadInProgress = false;
        if(download != null && download.exists()){
            try {
                ZipManager.unzip(download.getPath(), download.getParentFile().getAbsolutePath());
                download.delete();
            } catch (Exception e) {
                Log.e("AppResourceDownloader", e.getMessage(), e);
                try {
                    FileManager.deleteFolder(download.getParentFile());
                } catch (IOException e1) {
                    Log.e("AppResourceDownloader", e1.getMessage(), e1);
                }
            }
        }
    }

}
