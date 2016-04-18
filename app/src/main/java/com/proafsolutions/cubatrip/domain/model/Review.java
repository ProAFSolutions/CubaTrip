package com.proafsolutions.cubatrip.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by alex on 4/17/2016.
 */
@Table(name = "Review")
public class Review extends Model {

    @Column(name = "rate")
    private int rate;

    @Column(name = "comments")
    private String comments;

    @Column(name = "contact")
    private String contact;

    @Column(name = "date")
    private Date date;

    @Column(name = "sync")
    private boolean sync;

    @Column(name = "product", onDelete = Column.ForeignKeyAction.CASCADE)
    private Product product;

    public Review(){
        super();
    }

    public Review(int rate, String comments, Date date, boolean sync, Product product) {
        this();
        this.rate = rate;
        this.comments = comments;
        this.date = date;
        this.sync = sync;
        this.product = product;
    }

    //Getter and Setter
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
