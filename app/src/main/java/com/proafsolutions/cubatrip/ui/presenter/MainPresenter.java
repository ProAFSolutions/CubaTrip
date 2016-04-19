package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;

import com.proafsolutions.cubatrip.ui.activity.MainActivity;

/**
 * Created by alex on 4/18/2016.
 */
public class MainPresenter extends AbstractPresenter {

    private MainActivity activity;

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Activity getCurrentActivity() {
        return activity;
    }
}
