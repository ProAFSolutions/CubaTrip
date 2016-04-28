package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;

import com.proafsolutions.cubatrip.ui.activity.MapActivity;
import com.proafsolutions.cubatrip.ui.map.MapHandler;

import org.mapsforge.map.android.view.MapView;

/**
 * Created by alex on 4/18/2016.
 */
public class MapPresenter extends AbstractPresenter {

    private MapActivity activity;
    private MapView mapView;


    public MapPresenter(MapActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Activity getCurrentActivity() {
        return activity;
    }

    public void start()
    {
        mapView = new MapView(activity);
        activity.setContentView(this.mapView);
        MapHandler.getInstance().init(activity, mapView);
    }

    public void destroy()
    {
        mapView.destroyAll();
        MapHandler.reset();
    }
}
