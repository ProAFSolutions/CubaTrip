package com.proafsolutions.cubatrip.infrastructure.converter;

import com.google.gson.Gson;

import java.lang.reflect.Type;

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

}
