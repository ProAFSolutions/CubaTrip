package com.proafsolutions.cubatrip.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by alex on 4/17/2016.
 */
@Table(name = "Category")
public class Category extends Model{

    @Column(name = "name")
    private String name;

    @Column(name = "logoPath")
    private String logoPath;

    public Category(){
        super();
    }

    public Category(String name, String logoPath) {
        this();
        this.name = name;
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
