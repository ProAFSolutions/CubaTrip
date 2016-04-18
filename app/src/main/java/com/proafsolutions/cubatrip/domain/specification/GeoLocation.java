package com.proafsolutions.cubatrip.domain.specification;

/**
 * Created by alex on 4/17/2016.
 */
public class GeoLocation {

    private String latitude;

    private String longitude;

    public GeoLocation(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
