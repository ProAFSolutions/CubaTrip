package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.ui.activity.MapActivity;
import com.proafsolutions.cubatrip.ui.util.MapUtil;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.renderer.TileRendererLayer;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by alex on 4/18/2016.
 */
public class MapPresenter extends AbstractPresenter {

    private MapView mapView;
    private TileCache tileCache;
    private TileRendererLayer tileRendererLayer;

    private MapActivity activity;

    public MapPresenter(MapActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Activity getCurrentActivity() {
        return activity;
    }

    public  void setupMapSettings(){
        this.mapView = new MapView(activity);
        activity.setContentView(this.mapView);

        this.mapView.setClickable(true);
        this.mapView.getMapScaleBar().setVisible(true);
        this.mapView.setBuiltInZoomControls(true);
        this.mapView.getMapZoomControls().setZoomLevelMin((byte) 10);
        this.mapView.getMapZoomControls().setZoomLevelMax((byte) 20);

        this.tileCache = MapUtil.createTileCache(activity, mapView);
    }

    public void Start()
    {
        //String lon = this.getActivityParameters().getString("longitude");
       // String lat = this.getActivityParameters().getString("latitude");

       // Double logLong = Double.parseDouble(lon);
       // Double latLong = Double.parseDouble(lat);
       // this.mapView.getModel().mapViewPosition.setCenter(new LatLong(logLong, latLong));
        this.mapView.getModel().mapViewPosition.setCenter(new LatLong(23.1355443, -82.3620573));
        this.mapView.getModel().mapViewPosition.setZoomLevel((byte) 16);

        this.tileRendererLayer = MapUtil.createTileRendererLayer(this.tileCache, this.mapView);

        // only once a layer is associated with a mapView the rendering starts
        this.mapView.getLayerManager().getLayers().add(tileRendererLayer);


        Drawable image = activity.getResources().getDrawable(R.drawable.location);
        MapUtil.addMarker(this.mapView, image, new LatLong(23.1355443, -82.3620573));

    }

    private Bitmap getBitmapFromAsset(String strName)
    {
        AssetManager assetManager = activity.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }

    public void Destroy()
    {
        this.mapView.destroyAll();
    }



}
