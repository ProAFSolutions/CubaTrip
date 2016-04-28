package com.proafsolutions.cubatrip.infrastructure.config;

import com.proafsolutions.cubatrip.domain.model.UserSettings;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;

/**
 * Created by alejandro.clavijo on 4/28/2016.
 */
public class ConfigurationManager {


    public static UserSettings getUserSettings(){
        return RepositoryProvider.getUserSettingsRepository().getSettings();
    }


}
