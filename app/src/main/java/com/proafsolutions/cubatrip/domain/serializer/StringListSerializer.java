package com.proafsolutions.cubatrip.domain.serializer;

import com.activeandroid.serializer.TypeSerializer;
import com.google.gson.reflect.TypeToken;
import com.proafsolutions.cubatrip.artifacts.JsonManager;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by alex on 4/17/2016.
 */
public class StringListSerializer extends TypeSerializer {

    @Override
    public Class<?> getDeserializedType() {
        return List.class;
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
        return JsonManager.toJson((List<String>)data);
    }

    @Override
    public List<String> deserialize(Object data) {
        if (data == null) {
            return null;
        }
        Type type = new TypeToken<List<String>>(){}.getType();
        return JsonManager.toObject(data.toString(), type);
    }
}
