package com.proafsolutions.cubatrip.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by alex on 4/17/2016.
 */
@Table(name = "province")
public class Province extends Model{

    @Column(name = "name")
    private String name;

    public Province(){
        super();
    }

    public Province(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
