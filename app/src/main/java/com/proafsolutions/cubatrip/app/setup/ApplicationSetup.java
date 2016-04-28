package com.proafsolutions.cubatrip.app.setup;

import com.activeandroid.ActiveAndroid;

import com.activeandroid.Configuration;
import com.activeandroid.app.Application;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.ProductCheckIn;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.domain.model.UserSettings;
import com.proafsolutions.cubatrip.domain.serializer.DateSerializer;
import com.proafsolutions.cubatrip.domain.serializer.StringListSerializer;
import com.proafsolutions.cubatrip.domain.serializer.StringMapSerializer;
import com.proafsolutions.cubatrip.infrastructure.config.Constants;
import com.proafsolutions.cubatrip.infrastructure.io.FileManager;

import java.io.File;

/**
 * Created by alejandro.clavijo on 4/28/2016.
 */
public class ApplicationSetup {

    public static void init(Application context){
        initAppFolders();
        initDatabase(context);
        initExampleData();
    }

    private static void initDatabase(Application context){
         Configuration.Builder builder = new Configuration.Builder(context);
         builder.setDatabaseName(new File(FileManager.getDatabaseFolder(), Constants.DATABASE_NAME).getAbsolutePath());
         builder.setDatabaseVersion(1);
         builder.setModelClasses(Product.class, ProductCheckIn.class, Review.class, UserSettings.class);
         builder.setTypeSerializers(DateSerializer.class, StringListSerializer.class, StringMapSerializer.class);
         Configuration dbConfig = builder.create();
         ActiveAndroid.initialize(dbConfig);
    }

    public static boolean initAppFolders(){
        File appFolder =  FileManager.getAppFolder();
        if(!appFolder.exists()){
            appFolder.mkdir();

            File databaseFolder = FileManager.getDatabaseFolder();
            databaseFolder.mkdir();

            File mapFolder = FileManager.getMapFolder();
            mapFolder.mkdir();

            File resFolder = FileManager.getResourcesFolder();
            resFolder.mkdir();

            return true;
        }
        return false;
    }

    private static void initExampleData(){
        new AppDatasetExample(false);
    }

}
