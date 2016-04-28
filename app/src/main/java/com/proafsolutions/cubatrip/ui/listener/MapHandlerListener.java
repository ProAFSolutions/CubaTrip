package com.proafsolutions.cubatrip.ui.listener;

import org.mapsforge.core.model.LatLong;


public interface MapHandlerListener {

    void onPressLocation(LatLong latLong);

    void pathCalculating(boolean shortestPathRunning);
}
