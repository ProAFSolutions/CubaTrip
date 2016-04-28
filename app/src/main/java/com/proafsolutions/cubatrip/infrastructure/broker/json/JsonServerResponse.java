package com.proafsolutions.cubatrip.infrastructure.broker.json;

import java.util.List;

/**
 * Created by alex on 4/20/2016.
 */
public class JsonServerResponse {

    private List<Province> provinces;

    private String date;

    public JsonServerResponse(){}

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
