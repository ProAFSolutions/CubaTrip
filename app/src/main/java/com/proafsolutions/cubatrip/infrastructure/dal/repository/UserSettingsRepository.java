package com.proafsolutions.cubatrip.infrastructure.dal.repository;


import com.proafsolutions.cubatrip.domain.model.UserSettings;

/**
 * Created by alejandro.clavijo on 4/28/2016.
 */
public class UserSettingsRepository extends AbstractRepository<UserSettings>{

    @Override
    public Class<UserSettings> getModelClass() {
        return UserSettings.class;
    }

    public UserSettings getSettings(){
        return load(1);
    }
}
