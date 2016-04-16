package com.proafsolutions.cubatrip.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by alex on 4/15/2016.
 */
@Table(name = "address")
public class Address extends Model {

    @Column(name = "street")
    public String street;

    @Column(name = "city")
    public String city;

    @Column(name = "state")
    public String state;

    public Address(){
        super();
    }

}
