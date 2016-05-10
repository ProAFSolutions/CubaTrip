package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;

import com.proafsolutions.cubatrip.ui.activity.CategoriesActivity;
import com.proafsolutions.cubatrip.ui.activity.SplashActivity;


public class SplashPresenter extends AbstractPresenter {

    private SplashActivity activity;

    public SplashPresenter(SplashActivity activity) {
        this.activity = activity;
    }

    public void OpenMenu(){
        this.openNewActivityPassingData(CategoriesActivity.class,null);
    }

    @Override
    protected Activity getCurrentActivity() {
        return activity;
    }
}
