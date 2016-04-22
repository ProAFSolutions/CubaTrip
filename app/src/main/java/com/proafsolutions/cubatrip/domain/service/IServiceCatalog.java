package com.proafsolutions.cubatrip.domain.service;

import com.proafsolutions.cubatrip.domain.model.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Review;

import java.util.Date;
import java.util.List;

/**
 * Created by alex on 4/17/2016.
 */
public interface IServiceCatalog {

    Product getProductById(long id);

    Product getProductByRemoteId(long remoteId);

    List<Product> getProductsByCategory(CategoryEnum category);

    List<Product> getProductsByCategory(CategoryEnum category, long provinceId);

    void doCheckIn();

    void doProductReview(Review review);

    List<Review> getReviewsFrom(Date from);
}
