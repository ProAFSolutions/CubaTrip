package com.proafsolutions.cubatrip.infrastructure.config;

import com.proafsolutions.cubatrip.domain.model.UserSettings;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;

/**
 * Created by alejandro.clavijo on 4/28/2016.
 */
public class GlobalConfig {

    public static boolean downloadInProgress = false;

    public static UserSettings loadUserSettings(){
        return RepositoryProvider.getUserSettingsRepository().getSettings();
    }

    public static void saveUserSettings(UserSettings userSettings){
        RepositoryProvider.getUserSettingsRepository().save(userSettings);
    }
}
