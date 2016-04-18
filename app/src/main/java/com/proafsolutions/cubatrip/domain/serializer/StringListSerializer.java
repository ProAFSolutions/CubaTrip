package com.proafsolutions.cubatrip.domain.serializer;

import android.util.JsonReader;

import com.activeandroid.serializer.TypeSerializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 4/17/2016.
 */
public class StringListSerializer<T> extends TypeSerializer {

    @Override
    public Class<?> getDeserializedType() {
        return ArrayList.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }


    @Override
    public String serialize(Object data) {
        if (data == null) {
            return null;
        }
        return  new Gson().toJson((List<String>) data);
    }

    @Override
    public List<String> deserialize(Object data) {
        if (data == null) {
            return null;
        }
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> list= new Gson().fromJson((String) data, listType);
        return list;
    }
}
