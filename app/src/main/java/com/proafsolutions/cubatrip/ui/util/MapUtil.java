package com.proafsolutions.cubatrip.ui.util;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.proafsolutions.cubatrip.artifacts.Constants;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.datastore.MapDataStore;
import org.mapsforge.map.layer.Layer;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.model.MapViewPosition;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.InternalRenderTheme;

import java.io.File;

/**
 * Created by alex on 4/25/2016.
 */
public class MapUtil {

    private static final String MAP_CACHE = "mapcache";

    public static TileCache createTileCache(Activity activity, MapView mapView) {
        return AndroidUtil.createTileCache(activity, MAP_CACHE, mapView.getModel().displayModel.getTileSize(), 1f,
                mapView.getModel().frameBufferModel.getOverdrawFactor());
    }

    public static TileRendererLayer createTileRendererLayer(TileCache tileCache, MapView mapView) {
        MapDataStore mapDataStore = new MapFile(getMapFile());
        TileRendererLayer tileRendererLayer = new TileRendererLayer(tileCache, mapDataStore,  mapView.getModel().mapViewPosition, false, true, AndroidGraphicFactory.INSTANCE);
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.OSMARENDER);
        return tileRendererLayer;
    }

    public static void addMarker(MapView mapView, Drawable drawable, LatLong latLong) {
        Bitmap bitmap = AndroidGraphicFactory.convertToBitmap(drawable);
        Marker marker = new Marker(latLong, bitmap, 0, -bitmap.getHeight() / 2);
        mapView.getLayerManager().getLayers().add(marker);
        mapView.getLayerManager().redrawLayers();
    }

    public static File getMapFile() {
        // File file = new File(Environment.getExternalStorageDirectory(), MAPFILE);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), Constants.MAPFILE);
        return file;
    }
}
