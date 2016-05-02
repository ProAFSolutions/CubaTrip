package com.proafsolutions.cubatrip.domain.model.serializer;

import com.activeandroid.serializer.TypeSerializer;

import java.util.Date;

/**
 * Created by alex on 4/17/2016.
 */
public class DateSerializer  extends TypeSerializer {
    @Override
    public Class<?> getDeserializedType() {
        return Date.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return Long.class;
    }

    @Override
    public Long serialize(Object data) {
        if (data == null) {
            return null;
        }
        return ((Date) data).getTime();
    }

    @Override
    public Date deserialize(Object data) {
        if (data == null) {
            return null;
        }

        return new Date((Long) data);
    }
}
