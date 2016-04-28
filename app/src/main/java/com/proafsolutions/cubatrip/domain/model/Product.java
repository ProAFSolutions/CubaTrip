package com.proafsolutions.cubatrip.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.proafsolutions.cubatrip.domain.model.enums.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.enums.ProvinceEnum;
import com.proafsolutions.cubatrip.domain.specification.ProductDetails;
import com.proafsolutions.cubatrip.domain.specification.GeoLocation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 4/17/2016.
 */
@Table(name = "product")
public class Product extends Model{

    @Column(name = "remoteId", unique = true, onUniqueConflict = Column.ConflictAction.FAIL)
    private int remoteId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private Date created;

    @Column(name = "lastUpdated")
    private Date lastUpdated;

    @Column(name = "contact")
    private String contact;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "hours")
    private String hours;

    @Column(name = "webSiteUrl")
    private String webSiteUrl;

    @Column(name = "services")
    private Map<String, String> services = new HashMap<String, String>();

    @Column(name = "images")
    private List<String> images = new ArrayList<String>();

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column( name = "category")
    private CategoryEnum category;

    @Column( name = "province")
    private ProvinceEnum province;

    private ProductDetails details;
    private GeoLocation location;

    public Product() {
        super();
    }

    public Product(int remoteId, String name, String description, String hours, CategoryEnum category, ProvinceEnum province, ProductDetails details, GeoLocation location, List<String> images) {
        this();
        this.remoteId = remoteId;
        this.name = name;
        this.description = description;
        this.created = this.lastUpdated = new Date();
        this.hours = hours;

        this.category = category;
        this.province = province;

        this.contact = details.getContact();
        this.address = details.getAddress();
        this.phone = details.getPhone();
        this.email = details.getEmail();
        this.webSiteUrl = details.getWebSiteUrl();
        this.services = details.getServices();

        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();

        this.images = images;
    }

    public ProductDetails getDetails(){
        return  new ProductDetails(contact, address,phone, email, webSiteUrl, services);
    }

    public GeoLocation getGeoLocation(){
        return new GeoLocation(latitude, longitude);
    }

    //Getter and Setter
    public int getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Map<String, String> getServices() {
        return services;
    }

    public void setServices(Map<String, String> services) {
        this.services = services;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public ProvinceEnum getProvince() {
        return province;
    }

    public void setProvince(ProvinceEnum province) {
        this.province = province;
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

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}

