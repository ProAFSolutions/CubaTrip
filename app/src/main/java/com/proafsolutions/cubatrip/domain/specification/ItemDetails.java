package com.proafsolutions.cubatrip.domain.specification;


import java.util.Map;

/**
 * Created by alex on 4/17/2016.
 */
public class ItemDetails {

    private String contact;

    private String address;

    private String phone;

    private String email;

    private String webSiteUrl;

    private Map<String, String> services;

    public ItemDetails(String contact, String address, String phone, String email, String webSiteUrl, Map<String, String> services) {
        this.contact = contact;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.webSiteUrl = webSiteUrl;
        this.services = services;
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

    public Map<String, String> getServices() {
        return services;
    }

    public void setServices(Map<String, String> services) {
        this.services = services;
    }
}
