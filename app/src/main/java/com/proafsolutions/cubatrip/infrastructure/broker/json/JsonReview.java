package com.proafsolutions.cubatrip.infrastructure.broker.json;

import com.activeandroid.annotation.Column;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.domain.model.enums.RateEnum;

import java.util.Date;

/**
 * Created by alex on 5/1/2016.
 */
public class JsonReview {

    private int rate;

    private String comments;

    private String contact;

    private long date;

    private int productId;

    public JsonReview(){
    }

    public Review toReview(){
        return new Review(RateEnum.getRate(rate), comments, contact, new Date(date), new Product(productId));
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
