package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;
import android.os.Bundle;

/**
 * Created by alex on 4/18/2016.
 */
public abstract class AbstractPresenter {

    protected abstract Activity getCurrentActivity();

    protected Context getApplicationContext(){
        return getCurrentActivity().getApplicationContext();
    }

    protected Resources.Theme getCurrentTheme(){
        return getCurrentActivity().getApplicationContext().getTheme();
    }

    public void openNewActivity(Class newActivityClass){
        getCurrentActivity().startActivity(new Intent(getCurrentActivity().getApplicationContext(), newActivityClass));
    }

    public void openNewActivityPassingData(Class newActivityClass, Bundle parameters){
        Intent intent = new Intent(getCurrentActivity().getApplicationContext(), newActivityClass);
        if(parameters != null){
            intent.putExtras(parameters);
        }
        getCurrentActivity().startActivity(intent);
    }

    public Bundle getActivityParameters(){
        return getCurrentActivity().getIntent().getExtras();
    }


}
