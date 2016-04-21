package com.proafsolutions.cubatrip.artifacts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by alex on 4/20/2016.
 */
public class JsonManager {

    public static <T>  String toJson(T object){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
           return null;
        }
    }

    public static <T> T toObject(String jsonString, TypeReference<T> type){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString, type);
        } catch (IOException e) {
            return null;
        }
    }

}
