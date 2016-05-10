package com.proafsolutions.cubatrip.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.proafsolutions.cubatrip.domain.model.specification.GeoLocation;

import java.util.Date;

/**
 * Created by alex on 4/24/2016.
 */
@Table(name = "check_in")
public class ProductCheckIn  extends Model {

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "date")
    private Date date;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "comments")
    private String comments;

    @Column( name = "productId", onDelete = Column.ForeignKeyAction.CASCADE)
    private Product product;

    public ProductCheckIn(){
        super();
    }

    public ProductCheckIn(String userEmail, GeoLocation location, Product product) {
        this();
        this.userEmail = userEmail;
        this.product = product;
        this.date = new Date();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public ProductCheckIn(String userEmail, GeoLocation location, String comments, Product product) {
        this(userEmail, location, product);
        this.comments = comments;
    }

    public GeoLocation getGeoLocation(){
        return new GeoLocation(latitude, longitude);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
