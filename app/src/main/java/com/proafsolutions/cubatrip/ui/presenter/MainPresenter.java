package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import com.proafsolutions.cubatrip.artifacts.Constants;
import com.proafsolutions.cubatrip.ui.activity.MainActivity;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.datastore.MapDataStore;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.InternalRenderTheme;

import java.io.File;

/**
 * Created by alex on 4/18/2016.
 */
public class MainPresenter extends AbstractPresenter {

    private MapView mapView;
    private TileCache tileCache;
    private TileRendererLayer tileRendererLayer;

    private MainActivity activity;

    public MainPresenter(MainActivity activity) {
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

        // create a tile cache of suitable size
        this.tileCache = AndroidUtil.createTileCache(activity, "mapcache",
                mapView.getModel().displayModel.getTileSize(), 1f,
                this.mapView.getModel().frameBufferModel.getOverdrawFactor());
    }

    private File getMapFile() {
        // File file = new File(Environment.getExternalStorageDirectory(), MAPFILE);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), Constants.MAPFILE);
        return file;
    }

    public void Start()
    {
        String lon = this.getActivityParameters().getString("longitude");
        String lat = this.getActivityParameters().getString("latitude");

        Double logLong = Double.parseDouble(lon);
        Double latLong = Double.parseDouble(lat);
       // this.mapView.getModel().mapViewPosition.setCenter(new LatLong(logLong, latLong));
        this.mapView.getModel().mapViewPosition.setCenter(new LatLong(23.1355443, -82.3620573));
        this.mapView.getModel().mapViewPosition.setZoomLevel((byte) 16);

        // tile renderer layer using internal render theme
        MapDataStore mapDataStore = new MapFile(getMapFile());
        this.tileRendererLayer = new TileRendererLayer(tileCache, mapDataStore,
                this.mapView.getModel().mapViewPosition, false, true, AndroidGraphicFactory.INSTANCE);
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.OSMARENDER);

        // only once a layer is associated with a mapView the rendering starts
        this.mapView.getLayerManager().getLayers().add(tileRendererLayer);

    }

    public void Destroy()
    {
        this.mapView.destroyAll();
    }
}
