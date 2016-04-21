package com.proafsolutions.cubatrip.infrastructure.broker.json;

import com.proafsolutions.cubatrip.domain.model.Category;

import java.util.List;

/**
 * Created by alex on 4/20/2016.
 */
public class JsonProvince {

    private String name;

    private List<Category> categories;

    public JsonProvince(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
