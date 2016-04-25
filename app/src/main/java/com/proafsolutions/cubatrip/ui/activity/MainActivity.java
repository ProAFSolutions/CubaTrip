package com.proafsolutions.cubatrip.ui.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.proafsolutions.cubatrip.artifacts.Constants;
import com.proafsolutions.cubatrip.ui.presenter.MainPresenter;

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

public class MainActivity extends AppCompatActivity {


    private MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidGraphicFactory.createInstance(this.getApplication());
        presenter = new MainPresenter(MainActivity.this);
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



}