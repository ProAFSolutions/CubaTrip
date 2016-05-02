package com.proafsolutions.cubatrip.ui.map;

import android.app.Activity;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.util.PointList;
import com.graphhopper.util.StopWatch;
import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.UserSettings;
import com.proafsolutions.cubatrip.infrastructure.config.GlobalConfig;
import com.proafsolutions.cubatrip.infrastructure.config.Constants;
import com.proafsolutions.cubatrip.infrastructure.io.FileManager;
import com.proafsolutions.cubatrip.ui.listener.MapHandlerListener;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.graphics.Cap;
import org.mapsforge.core.graphics.Join;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.graphics.Style;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.MapPosition;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.datastore.MapDataStore;
import org.mapsforge.map.layer.Layer;
import org.mapsforge.map.layer.Layers;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.layer.overlay.Polyline;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.InternalRenderTheme;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by alex on 4/27/2016.
 */
public class MapHandler {
    private Activity activity;
    private MapView mapView;
    private TileCache tileCache;
    private GraphHopper hopper;
    private volatile boolean shortestPathRunning;
    private Marker startMarker, endMarker;
    private Polyline polylinePath, polylineTrack;
    private MapHandlerListener mapHandlerListener;
    private boolean prepareInProgress;
    private MyLocationOverlay myLocation;
    private List<Marker> markers;
    private PointList trackingPointList;

    private UserSettings userSettings;

    private static MapHandler _instance;

    public static MapHandler getInstance() {
        if (_instance == null) {
            reset();
        }
        return _instance;
    }

    /**
     * reset class, build a new instance
     */
    public static void reset() {
        _instance = new MapHandler();
        MapNavigator.reset();
    }


    private MapHandler() {
        this.activity = null;
        this.mapView = null;
        shortestPathRunning = false;
        startMarker = null;
        endMarker = null;
        polylinePath = null;
        markers = new ArrayList<>();
        userSettings = null;
    }

    public void init(Activity activity, MapView mapView) {

        this.activity = activity;
        this.mapView = mapView;
        this.userSettings = GlobalConfig.loadUserSettings();

        initMap();
        initMyLocation();
        loadGraphStorage();
    }

    public void initMyLocation(){
        Drawable drawable = activity.getResources().getDrawable(R.drawable.user_location);
        Bitmap myLocationIcon = AndroidGraphicFactory.convertToBitmap(drawable);
        myLocation = new MyLocationOverlay(activity, mapView.getModel().mapViewPosition, myLocationIcon);
        myLocation.enableMyLocation(true);
        mapView.getLayerManager().getLayers().add(myLocation);
    }

    public void initMap(){
        mapView.setClickable(true);
        mapView.getMapScaleBar().setVisible(true);
        mapView.setBuiltInZoomControls(true);
        mapView.getMapZoomControls().setZoomLevelMin((byte) 10);
        mapView.getMapZoomControls().setZoomLevelMax((byte) 20);
        mapView.getModel().mapViewPosition.setCenter(new LatLong(25.7902358, -80.2463184));
        mapView.getModel().mapViewPosition.setZoomLevel((byte) 16);
        initTileCache();
        initRendererLayer();
    }

    private void initTileCache() {
        tileCache = AndroidUtil.createTileCache(activity, Constants.MAP_CACHE,mapView.getModel().displayModel.getTileSize(), 1f, mapView.getModel().frameBufferModel.getOverdrawFactor());
    }

    private void initRendererLayer() {
        MapDataStore mapDataStore = new MapFile(new File(FileManager.getMapFolder(), Constants.MAP_FILE));
        TileRendererLayer tileRendererLayer = new TileRendererLayer(tileCache, mapDataStore,  mapView.getModel().mapViewPosition, false, true, AndroidGraphicFactory.INSTANCE){
            @Override
            public boolean onLongPress(LatLong tapLatLong, Point layerXY, Point tapXY) {
                return super.onLongPress(tapLatLong, layerXY, tapXY);
            }
            @Override
            public boolean onTap(LatLong tapLatLong, Point layerXY, Point tapXY) {
                return super.onTap(tapLatLong, layerXY, tapXY);
            }
        };
        tileRendererLayer.setTextScale(0.8f);
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.OSMARENDER);

        mapView.getLayerManager().getLayers().add(tileRendererLayer);
    }

    public void centerPointOnMap(LatLong latLong, int zoomLevel) {
        if (zoomLevel == 0) {
            mapView.getModel().mapViewPosition.setMapPosition(new MapPosition(latLong, mapView.getModel().mapViewPosition.getZoomLevel()));
        } else {
            mapView.getModel().mapViewPosition.setMapPosition(new MapPosition(latLong, (byte) zoomLevel));
        }
    }

    public void removeLayer(Layer layer) {
        if (mapView.getLayerManager().getLayers().contains(layer)) {
            mapView.getLayerManager().getLayers().remove(layer);
        }
    }

    public void addMarker(LatLong p, int resource) {
        Marker marker = createMarker(p, resource);
        mapView.getLayerManager().getLayers().add(marker);
        markers.add(marker);
    }

    public void addDefaultMarker(LatLong p) {
        addMarker(p, R.drawable.source_location);
    }

    public void addStartMarker(LatLong startPoint) {
        addSourceAndTargetMarkers(startPoint, null);
    }

    public void addEndMarker(LatLong endPoint) {
        addSourceAndTargetMarkers(null, endPoint);
    }

    public void addSourceAndTargetMarkers(LatLong startPoint, LatLong endPoint) {
        Layers layers = mapView.getLayerManager().getLayers();
        if (startPoint != null) {
            removeLayer(startMarker);
            startMarker = createMarker(startPoint, R.drawable.source_location);
            layers.add(startMarker);
        }
        if (endPoint != null) {
            removeLayer(endMarker);
            endMarker = createMarker(endPoint, R.drawable.target_location);
            layers.add(endMarker);
        }
    }

    public void removeMarkers() {
        Layers layers = mapView.getLayerManager().getLayers();
        for (Marker marker: markers ) {
            removeLayer(marker);
        }
    }

    private Marker createMarker(LatLong p, int resource) {
        Drawable drawable = activity.getResources().getDrawable(resource);
        Bitmap bitmap = AndroidGraphicFactory.convertToBitmap(drawable);
        return new Marker(p, bitmap, 0, -bitmap.getHeight() / 2);
    }

    private void loadGraphStorage() {
        new AsyncTask<Void, Void, Path>() {
            String error = "";
            protected Path doInBackground(Void... v) {
                try {
                    GraphHopper tmpHopp = new GraphHopper().forMobile();
                    tmpHopp.load(new File(FileManager.getMapFolder(), Constants.MAP_GH_FOLDER).getAbsolutePath());
                    hopper = tmpHopp;
                } catch (Exception e) {
                    error = "error: " + e.getMessage();
                }
                return null;
            }

            protected void onPostExecute(Path o) {
                if (error != "") {
                    logToast("An error happened while creating graph:" + error);
                }
                prepareInProgress = false;
            }
        }.execute();
    }

    public void calcPath(final double fromLat, final double fromLon, final double toLat, final double toLon) {
        removeLayer(polylinePath);
        polylinePath = null;
        new AsyncTask<Void, Void, GHResponse>() {
            float time;

            protected GHResponse doInBackground(Void... v) {
                StopWatch sw = new StopWatch().start();
                GHRequest req = new GHRequest(fromLat, fromLon, toLat, toLon);
                req.setAlgorithm(userSettings.getRoutingAlgorithm());
                req.getHints().put("instructions", userSettings.getDirectionsON());
                req.setVehicle(userSettings.getTravelMode());
                req.setWeighting(userSettings.getWeighting());
                GHResponse resp = hopper.route(req);
                time = sw.stop().getSeconds();
                return resp;
            }

            protected void onPreExecute() {
                super.onPreExecute();
                shortestPathRunning = true;
            }

            protected void onPostExecute(GHResponse resp) {
                if (!resp.hasErrors()) {
                    polylinePath = createPolyline(resp.getBest().getPoints(), activity.getResources().getColor(R.color.colorPrimaryDark), 20);
                    mapView.getLayerManager().getLayers().add(polylinePath);
                    if (userSettings.getDirectionsON().equals(Constants.MAP_DIRECTION_ON)) {
                        MapNavigator.getInstance().setGhResponse(resp);
                    }
                } else {
                    logToast("Error:" + resp.getErrors());
                }
                try {
                   // activity.findViewById(R.id.map_nav_settings_path_finding).setVisibility(View.GONE);
                   // activity.findViewById(R.id.nav_settings_layout).setVisibility(View.VISIBLE);
                } catch (Exception e) {e.getStackTrace();}
                shortestPathRunning = false;
            }
        }.execute();
    }

    boolean isReady() {
        if (hopper != null)
            return true;
        if (prepareInProgress) {
            return false;
        }
        return false;
    }

    public void startTrack() {
        if (polylineTrack != null) {
            removeLayer(polylineTrack);
        }
        polylineTrack = null;
        trackingPointList = new PointList();

        polylineTrack = createPolyline(trackingPointList, activity.getResources().getColor(R.color.colorPrimaryDark), 25);

        mapView.getLayerManager().getLayers().add(polylineTrack);
    }

    public void addTrackPoint(LatLong point) {
        int i = mapView.getLayerManager().getLayers().indexOf(polylineTrack);
        ((Polyline) mapView.getLayerManager().getLayers().get(i)).getLatLongs().add(point);
    }

    /**
     * draws a connected series of line segments specified by a list of LatLongs.
     *
     * @param pointList
     * @param color:       the color of the polyline
     * @param strokeWidth: the stroke width of the polyline
     * @return Polyline
     */
    public Polyline createPolyline(PointList pointList, int color, int strokeWidth) {
        Paint paintStroke = AndroidGraphicFactory.INSTANCE.createPaint();

        paintStroke.setStyle(Style.STROKE);
        paintStroke.setStrokeJoin(Join.ROUND);
        paintStroke.setStrokeCap(Cap.ROUND);
        paintStroke.setColor(color);
        //        paintStroke.setDashPathEffect(new float[]{25, 25});
        paintStroke.setStrokeWidth(strokeWidth);

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

    public boolean isShortestPathRunning() {
        return shortestPathRunning;
    }

    public GraphHopper getHopper() {
        return hopper;
    }

    public void setHopper(GraphHopper hopper) {
        this.hopper = hopper;
    }


    public void setMapHandlerListener(MapHandlerListener mapHandlerListener) {
        this.mapHandlerListener = mapHandlerListener;
    }

    public List<Marker> getMarkers() {
        return markers;
    }

    public PointList getTrackingPointList() {
        return trackingPointList;
    }

    public MyLocationOverlay getMyLocation() {
        return myLocation;
    }


    private void log(String str) {
        Log.i(this.getClass().getSimpleName(), "-----------------" + str);
    }

    private void logToast(String str) {
        Toast.makeText(activity, str, Toast.LENGTH_LONG).show();
    }

}
