package com.proafsolutions.cubatrip.infrastructure.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import com.proafsolutions.cubatrip.infrastructure.io.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by alex on 4/20/2016.
 */
public class JsonConverter {

    private static final Gson _converter = new Gson();

    public static <T>  String toJson(T object){
       return _converter.toJson(object);
    }

    public static <T> T toObject(String jsonString, Type type){
       return _converter.fromJson(jsonString, type);
    }

    public static <T> void writeJson(T object, File jsonFile) throws IOException {
        String fileName = "update_" + String.valueOf(new Date().getTime()) + ".json";
        FileWriter writer = new FileWriter(new File(FileManager.getUpdateFolder(), fileName));
        writer.write(_converter.toJson(object));
        writer.flush();
        writer.close();
    }

    public static <T> T readJson(Class<T> classType, File jsonFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
        T result =_converter.fromJson(reader, classType);
        reader.close();
        return result;
    }
}
