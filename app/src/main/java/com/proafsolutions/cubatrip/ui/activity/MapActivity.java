package com.proafsolutions.cubatrip.ui.activity;

import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.proafsolutions.cubatrip.ui.presenter.MapPresenter;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;

public class MapActivity extends AppCompatActivity implements LocationListener{


    private MapPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidGraphicFactory.createInstance(this.getApplication());
        presenter = new MapPresenter(MapActivity.this);
        presenter.setupMapSettings();
        //setContentView(R.layout.activity_main);
    }



    @Override
    protected void onStart() {
        super.onStart();

          presenter.Start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.Destroy();
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}