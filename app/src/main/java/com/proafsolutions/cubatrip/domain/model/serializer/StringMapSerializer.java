package com.proafsolutions.cubatrip.domain.model.serializer;

import com.activeandroid.serializer.TypeSerializer;
import com.google.gson.reflect.TypeToken;
import com.proafsolutions.cubatrip.infrastructure.converter.JsonConverter;

import java.lang.reflect.Type;
import java.util.Map;


/**
 * Created by alex on 4/17/2016.
 */
public class StringMapSerializer extends TypeSerializer{
    @Override
    public Class<?> getDeserializedType() {
        return Map.class;
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
        return JsonConverter.toJson((Map<String, String>) data);
    }

    @Override
    public Map<String, String> deserialize(Object data) {
        if (data == null) {
            return null;
        }
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        return JsonConverter.toObject(data.toString(), type);
    }
}
