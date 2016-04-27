package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.PathWrapper;
import com.graphhopper.routing.AlgorithmOptions;
import com.graphhopper.util.PointList;
import com.graphhopper.util.StopWatch;
import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.artifacts.Constants;
import com.proafsolutions.cubatrip.ui.activity.MapActivity;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.graphics.Style;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.datastore.MapDataStore;
import org.mapsforge.map.layer.Layers;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.layer.overlay.Polyline;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.InternalRenderTheme;

import java.io.File;
import java.util.List;

/**
 * Created by alex on 4/18/2016.
 */
public class MapPresenter extends AbstractPresenter {

    private static final String MAP_CACHE = "mapcache";

    private MapActivity activity;

    //Maps
    private MapView mapView;
    private TileCache tileCache;
    private TileRendererLayer tileRendererLayer;

    //Routing
    private GraphHopper hopper;
    private LatLong start;
    private LatLong end;
    private volatile boolean prepareInProgress = false;
    private volatile boolean shortestPathRunning = false;

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

        this.tileCache = createTileCache();
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

        this.tileRendererLayer = createTileRendererLayer();

        // only once a layer is associated with a mapView the rendering starts
        this.mapView.getLayerManager().getLayers().add(tileRendererLayer);

    }

    public void Destroy()
    {
        this.mapView.destroyAll();
    }

    /*
    * Maps Helper
    */
    public TileCache createTileCache() {
        return AndroidUtil.createTileCache(activity, MAP_CACHE, mapView.getModel().displayModel.getTileSize(), 1f,
                mapView.getModel().frameBufferModel.getOverdrawFactor());
    }

    public TileRendererLayer createTileRendererLayer() {
        MapDataStore mapDataStore = new MapFile(loadMapFile());
        TileRendererLayer tileRendererLayer = new TileRendererLayer(tileCache, mapDataStore,  mapView.getModel().mapViewPosition, false, true, AndroidGraphicFactory.INSTANCE);
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.OSMARENDER);
        return tileRendererLayer;
    }

    public void addMarker(Drawable drawable, LatLong latLong) {
        Bitmap bitmap = AndroidGraphicFactory.convertToBitmap(drawable);
        Marker marker = new Marker(latLong, bitmap, 0, -bitmap.getHeight() / 2);
        mapView.getLayerManager().getLayers().add(marker);
        mapView.getLayerManager().redrawLayers();
    }


    public void addMarker(int resource, LatLong latLong) {
        Drawable drawable = activity.getResources().getDrawable(resource);
        addMarker(drawable, latLong);
    }

    public void showMyLocation(Drawable drawable){
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        try {
            boolean gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if(gps_enabled){

                /*Marker myLocationMarker = this.getResources().getDrawable(R.drawable.person);
                myLocationOverlay = new MyLocationOverlay(this, mapView, myLocationMarker);
                myLocationOverlay.enableMyLocation(true);
                mapView.getOverlays().add(myLocationOverlay);*/
            }

        } catch(Exception ex) {

        }
    }

    public static File loadMapFile() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), Constants.MAPFILE);
        return file;
    }


   /*
   * Routes Helper
   */
    protected boolean onMapTap(LatLong tapLatLong, Point layerXY, Point tapXY )
    {
        if (hopper == null || prepareInProgress || shortestPathRunning)
            return false;

        Layers layers = mapView.getLayerManager().getLayers();
        if (start != null && end == null)
        {
            end = tapLatLong;
            shortestPathRunning = true;
            addMarker(R.drawable.image_test, tapLatLong);
            calcPath(start.latitude, start.longitude, end.latitude, end.longitude);
        }
        else{
            start = tapLatLong;
            end = null;
            // remove all layers but the first one, which is the map
            while (layers.size() > 1)
            {
                layers.remove(1);
            }
            addMarker(R.drawable.image_test, start);
        }
        return true;
    }

    public void loadGraphStorage()
    {
        new GHAsyncTask<Void, Void, Path>()
        {
            protected Path saveDoInBackground( Void... v ) throws Exception
            {
                GraphHopper tmpHopp = new GraphHopper().forMobile();
                tmpHopp.load(new File("").getAbsolutePath() + "-gh");
                // log("found graph " + tmpHopp.getGraphHopperStorage().toString() + ", nodes:" + tmpHopp.getGraphHopperStorage().getNodes());
                hopper = tmpHopp;
                return null;
            }

            protected void onPostExecute( Path o )
            {
                if (hasError())
                {
//                    logUser("An error happend while creating graph:"
//                            + getErrorMessage());
                }
                else
                {
//                    logUser("Finished loading graph. Press long to define where to start and end the route.");
                }

                prepareInProgress = false;
            }
        }.execute();
    }

    public void calcPath(final double fromLat, final double fromLon,
                         final double toLat, final double toLon){

        new AsyncTask<Void, Void, PathWrapper>()
        {
            float time;
            protected PathWrapper doInBackground( Void... v )
            {
                StopWatch sw = new StopWatch().start();
                GHRequest req = new GHRequest(fromLat, fromLon, toLat, toLon).setAlgorithm(AlgorithmOptions.DIJKSTRA_BI);
                req.getHints().put("instructions", "false");
                GHResponse resp = hopper.route(req);
                time = sw.stop().getSeconds();
                return resp.getBest();
            }

            protected void onPostExecute(PathWrapper resp)
            {
                if (!resp.hasErrors())
                {
                    mapView.getLayerManager().getLayers().add(createPolyline(resp));
                    //mapView.redraw();
                }
                else{
                    Log.e("MapPresenter", "Error: " + resp.getErrors().toString());
                }
                shortestPathRunning = false;
            }
        }.execute();
    }

    private Polyline createPolyline(PathWrapper response )
    {
        Paint paintStroke = AndroidGraphicFactory.INSTANCE.createPaint();
        paintStroke.setStyle(Style.STROKE);
        paintStroke.setColor(Color.argb(128, 0, 0xCC, 0x33));
        paintStroke.setDashPathEffect(new float[] { 25, 15 });
        paintStroke.setStrokeWidth(8);

        Polyline line = new Polyline(paintStroke, AndroidGraphicFactory.INSTANCE);
        List<LatLong> geoPoints = line.getLatLongs();
        PointList tmp = response.getPoints();
        for (int i = 0; i < response.getPoints().getSize(); i++)
        {
            geoPoints.add(new LatLong(tmp.getLatitude(i), tmp.getLongitude(i)));
        }
        return line;
    }

    abstract class GHAsyncTask<A, B, C> extends AsyncTask<A, B, C>
    {
        private Throwable error;

        protected abstract C saveDoInBackground( A... params ) throws Exception;

        protected C doInBackground( A... params )
        {
            try
            {
                return saveDoInBackground(params);
            } catch (Throwable t)
            {
                error = t;
                return null;
            }
        }
        public boolean hasError()
        {
            return error != null;
        }

        public Throwable getError()
        {
            return error;
        }

        public String getErrorMessage()
        {
            if (hasError())
            {
                return error.getMessage();
            }
            return "No Error";
        }
    }



}
