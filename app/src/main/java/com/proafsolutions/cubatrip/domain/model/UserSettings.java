package com.proafsolutions.cubatrip.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by alejandro.clavijo on 4/28/2016.
 */
@Table(name = "user_settings")
public class UserSettings extends Model{

    /*
    * Map Settings
    * */

    //foot, bike, car
    @Column(name = "travel_mode")
    private String travelMode;

    //fastest, shortest (route)
    @Column(name = "weighting")
    private String weighting;


    @Column(name = "routing_algorithm")
    private String routingAlgorithm;

    //instructions on or off; default true (on)
    @Column(name = "directions_on")
    private String directionsON;

    @Column(name = "zoom_level_max")
    private int zoomLevelMax;


    @Column(name = "zoom_level_min")
    private int zoomLevelMin;


    @Column(name = "last_zoom_level")
    private int lastZoomLevel;

    // latitude,longitude
    @Column(name = "last_zoom_level")
    private String  lastLocation;


    public UserSettings(){
        super();
    }



    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public String getWeighting() {
        return weighting;
    }

    public void setWeighting(String weighting) {
        this.weighting = weighting;
    }

    public String getRoutingAlgorithm() {
        return routingAlgorithm;
    }

    public void setRoutingAlgorithm(String routingAlgorithm) {
        this.routingAlgorithm = routingAlgorithm;
    }

    public String getDirectionsON() {
        return directionsON;
    }

    public void setDirectionsON(String directionsON) {
        this.directionsON = directionsON;
    }

    public int getZoomLevelMax() {
        return zoomLevelMax;
    }

    public void setZoomLevelMax(int zoomLevelMax) {
        this.zoomLevelMax = zoomLevelMax;
    }

    public int getZoomLevelMin() {
        return zoomLevelMin;
    }

    public void setZoomLevelMin(int zoomLevelMin) {
        this.zoomLevelMin = zoomLevelMin;
    }

    public int getLastZoomLevel() {
        return lastZoomLevel;
    }

    public void setLastZoomLevel(int lastZoomLevel) {
        this.lastZoomLevel = lastZoomLevel;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }
}
