package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.PathWrapper;
import com.graphhopper.routing.AlgorithmOptions;
import com.graphhopper.util.PointList;
import com.graphhopper.util.StopWatch;
import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.infrastructure.config.Constants;
import com.proafsolutions.cubatrip.infrastructure.io.FileLocator;
import com.proafsolutions.cubatrip.ui.activity.MapActivity;
import com.proafsolutions.cubatrip.ui.map.MyLocationOverlay;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.graphics.Cap;
import org.mapsforge.core.graphics.Join;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.graphics.Style;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.datastore.MapDataStore;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.layer.overlay.Polyline;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.InternalRenderTheme;
import java.util.List;

/**
 * Created by alex on 4/18/2016.
 */
public class MapPresenter extends AbstractPresenter {

    private static final String MAP_CACHE = "mapcache";

    private MapActivity activity;

    //Location
    private MyLocationOverlay myLocation;

    //Maps
    private MapView mapView;
    private TileCache tileCache;
    private TileRendererLayer tileRendererLayer;

    //Routing
    private GraphHopper hopper;
    private LatLong targetLatLong;
    private String travelMode;
    private Polyline polylinePath, polylineTrack;

    public MapPresenter(MapActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Activity getCurrentActivity() {
        return activity;
    }

    public void start()
    {
        setupMapSettings();
        setupMyLocation();
        setupRoutesSettings();
    }

    public void destroy()
    {
        mapView.destroyAll();
    }

    public void addMarker(Drawable drawable, LatLong latLong) {
        Bitmap bitmap = AndroidGraphicFactory.convertToBitmap(drawable);
        Marker marker = new Marker(latLong, bitmap, 0, -bitmap.getHeight() / 2);
        mapView.getLayerManager().getLayers().add(marker);
       // mapView.getLayerManager().redrawLayers();
    }

    public void addMarker(int resource, LatLong latLong) {
        Drawable drawable = activity.getResources().getDrawable(resource);
        addMarker(drawable, latLong);
    }


    //Maps setup
    public  void setupMapSettings(){
        mapView = new MapView(activity);
        activity.setContentView(this.mapView);

        mapView.setClickable(true);
        mapView.getMapScaleBar().setVisible(true);
        mapView.setBuiltInZoomControls(true);
        mapView.getMapZoomControls().setZoomLevelMin((byte) 10);
        mapView.getMapZoomControls().setZoomLevelMax((byte) 20);

        Double logLong = this.getActivityParameters().getDouble(Constants.ACTIVITY_PARAM_LATITUDE);
        Double latLong = this.getActivityParameters().getDouble(Constants.ACTIVITY_PARAM_LONGITUDE);
        targetLatLong = new LatLong(logLong, latLong);

        // this.mapView.getModel().mapViewPosition.setCenter(new LatLong(logLong, latLong));
        mapView.getModel().mapViewPosition.setCenter(new LatLong(23.1355443, -82.3620573));
        mapView.getModel().mapViewPosition.setZoomLevel((byte) 16);

        tileCache = createTileCache();
        tileRendererLayer = createTileRendererLayer();

        mapView.getLayerManager().getLayers().add(tileRendererLayer);
    }

    private TileCache createTileCache() {
        return AndroidUtil.createTileCache(activity, MAP_CACHE, mapView.getModel().displayModel.getTileSize(), 1f,
                mapView.getModel().frameBufferModel.getOverdrawFactor());
    }

    private TileRendererLayer createTileRendererLayer() {
        MapDataStore mapDataStore = new MapFile(FileLocator.loadFile(Constants.MAPS_FOLDER, Constants.MAP_FILE));
        TileRendererLayer tileRendererLayer = new TileRendererLayer(tileCache, mapDataStore,  mapView.getModel().mapViewPosition, false, true, AndroidGraphicFactory.INSTANCE);
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.OSMARENDER);
        return tileRendererLayer;
    }


    //Location setup
    public void setupMyLocation(){
        Drawable drawable = activity.getResources().getDrawable(R.drawable.user_location);
        Bitmap myLocationIcon = AndroidGraphicFactory.convertToBitmap(drawable);
        myLocation = new MyLocationOverlay(activity, mapView.getModel().mapViewPosition, myLocationIcon);
        myLocation.enableMyLocation(true);
        mapView.getLayerManager().getLayers().add(myLocation);
    }

    //Routes setup
    private void setupRoutesSettings()
    {
        travelMode = this.getActivityParameters().getString(Constants.ACTIVITY_PARAM_TARVEL_MODE);

        new AsyncTask<Void, Void, Path>() {
            String error = "";
            protected Path doInBackground(Void... v) {
                try {
                    GraphHopper tmpHopp = new GraphHopper().forMobile();
                    tmpHopp.load(FileLocator.loadFile(Constants.MAPS_FOLDER, Constants.MAP_OSM_FILE).getAbsolutePath());
                    hopper = tmpHopp;
                } catch (Exception e) {
                    error = "error: " + e.getMessage();
                }
                return null;
            }

            protected void onPostExecute(Path o) {
                if (error != null && !error.isEmpty()) {
                    Log.e("MapPresenter", error);
                }
                searchRoute();
            }

        }.execute();
    }

    private void searchRoute(){
        if (myLocation.getPosition() != null && targetLatLong != null)
        {
            addMarker(R.drawable.target_location, targetLatLong);
            calcPath(myLocation.getPosition().latitude, myLocation.getPosition().longitude, targetLatLong.latitude, targetLatLong.longitude);
        }
    }

    private void calcPath(final double fromLat, final double fromLon,
                          final double toLat, final double toLon){

       // removeLayer(layers, polylinePath);
        polylinePath = null;

        new AsyncTask<Void, Void, GHResponse>()
        {
            float time;

            protected GHResponse doInBackground(Void... v) {
                StopWatch sw = new StopWatch().start();
                GHRequest req = new GHRequest(fromLat, fromLon, toLat, toLon);
                req.setAlgorithm(AlgorithmOptions.DIJKSTRA_BI);
                req.getHints().put("instructions", "false");
                req.setVehicle(travelMode);
                GHResponse resp = hopper.route(req);
                time = sw.stop().getSeconds();
                return resp;
            }

            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected void onPostExecute(GHResponse resp)
            {
                if (!resp.hasErrors()) {
                    polylinePath = createPolyline(resp.getBest().getPoints());
                    mapView.getLayerManager().getLayers().add(polylinePath);
//                    if (Variable.getVariable().isDirectionsON()) {
//                        Navigator.getNavigator().setGhResponse(resp);
//                    }
                } else {
                    Log.e("MapPresenter", "Error: " + resp.getErrors().toString());
                }
                try {
//                    activity.findViewById(R.id.map_nav_settings_path_finding).setVisibility(View.GONE);
//                    activity.findViewById(R.id.nav_settings_layout).setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    Log.e("MapPresenter", e.getMessage() + "->" + e.getStackTrace());
                }
            }
        }.execute();
    }

    private Polyline createPolyline(PathWrapper response )
    {
        Paint paintStroke = AndroidGraphicFactory.INSTANCE.createPaint();
        paintStroke.setStyle(Style.STROKE);
        paintStroke.setColor(Color.argb(128, 0, 0xCC, 0x33));
        //paintStroke.setDashPathEffect(new float[] { 25, 15 });
        paintStroke.setStrokeWidth(8);

        Polyline line = new Polyline((Paint)paintStroke, AndroidGraphicFactory.INSTANCE);
        List<LatLong> geoPoints = line.getLatLongs();
        PointList tmp = response.getPoints();
        for (int i = 0; i < response.getPoints().getSize(); i++)
        {
            geoPoints.add(new LatLong(tmp.getLatitude(i), tmp.getLongitude(i)));
        }
        return line;
    }

    public Polyline createPolyline(PointList pointList) {

        Paint paintStroke = AndroidGraphicFactory.INSTANCE.createPaint();

        paintStroke.setStyle(Style.STROKE);
        paintStroke.setStrokeJoin(Join.ROUND);
        paintStroke.setStrokeCap(Cap.ROUND);
        paintStroke.setColor(Color.argb(128, 0, 0xCC, 0x33));
        //        paintStroke.setDashPathEffect(new float[]{25, 25});
        paintStroke.setStrokeWidth(20);

        // TODO: new mapsforge version wants an mapsforge-paint, not an android paint.
        // This doesn't seem to support transparceny
        //paintStroke.setAlpha(128);
        Polyline line = new Polyline((Paint) paintStroke, AndroidGraphicFactory.INSTANCE);
        List<LatLong> geoPoints = line.getLatLongs();
        PointList tmp = pointList;
        for (int i = 0; i < pointList.getSize(); i++) {
            geoPoints.add(new LatLong(tmp.getLatitude(i), tmp.getLongitude(i)));
        }
        return line;
    }





}
