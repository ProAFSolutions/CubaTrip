package com.proafsolutions.cubatrip.infrastructure.dal.repository;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by alex on 4/17/2016.
 */
public abstract class AbstractRepository<T extends Model> {

    public abstract Class<T> getModelClass();

    public void save(T object) {
        object.save();
    }

    public void saveAll(List<T> objects){
        ActiveAndroid.beginTransaction();
        try {
            for (T obj: objects) {
                save(obj);
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }


    public void detete(T object) {
        object.delete();
    }

    public void delete(long generatedId) {
        new Delete().from(getModelClass()).where("id = ?", generatedId).execute();
    }

    public T load(long generatedId){
        return Model.load(getModelClass(), generatedId);
    }

    public List<T> loadAll() {
        return new Select()
                .from(getModelClass())
                .execute();
    }
}
