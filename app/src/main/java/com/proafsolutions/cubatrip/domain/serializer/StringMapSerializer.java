package com.proafsolutions.cubatrip.domain.serializer;

import com.activeandroid.serializer.TypeSerializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by alex on 4/17/2016.
 */
public class StringMapSerializer extends TypeSerializer{
    @Override
    public Class<?> getDeserializedType() {
        return HashMap.class;
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
        return new Gson().toJson((Map<String, String>) data);
    }

    @Override
    public Map<String, String> deserialize(Object data) {
        if (data == null) {
            return null;
        }
        Type mapType = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> map = new Gson().fromJson((String) data, mapType);
        return map;
    }
}
