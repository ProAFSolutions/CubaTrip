package com.proafsolutions.cubatrip.ui.activity;

import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.proafsolutions.cubatrip.ui.presenter.MapPresenter;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;

public class MapActivity extends AppCompatActivity {


    private MapPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidGraphicFactory.createInstance(this.getApplication());
        presenter = new MapPresenter(MapActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

}