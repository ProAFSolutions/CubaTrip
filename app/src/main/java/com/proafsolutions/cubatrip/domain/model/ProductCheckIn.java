package com.proafsolutions.cubatrip.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

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

    @Column(name = "comments")
    private String comments;

    @Column( name = "productId", onDelete = Column.ForeignKeyAction.CASCADE)
    private Product product;

    public ProductCheckIn(){
        super();
    }

    public ProductCheckIn(String userEmail, Product product) {
        this();
        this.userEmail = userEmail;
        this.product = product;
        this.date = new Date();
    }

    public ProductCheckIn(String userEmail, String comments, Product product) {
        this(userEmail, product);
        this.comments = comments;
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
}
