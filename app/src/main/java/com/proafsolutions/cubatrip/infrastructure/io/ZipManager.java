package com.proafsolutions.cubatrip.infrastructure.io;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by alex on 4/25/2016.
 */
public class ZipManager {

    public static void unzip(String zipFile, String location) {
        try  {
            FileInputStream fin = new FileInputStream(zipFile);
            ZipInputStream zin = new ZipInputStream(fin);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                if(ze.isDirectory()) {
                    File f = new File(location + ze.getName());
                    if(!f.isDirectory()) {
                        f.mkdirs();
                    }
                } else {
                    FileOutputStream fout = new FileOutputStream(location + ze.getName());
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        fout.write(c);
                    }
                    zin.closeEntry();
                    fout.close();
                }
            }
            zin.close();
        } catch(Exception e) {
            Log.e("ZipManager", "unzip", e);
        }
    }
}
