package com.proafsolutions.cubatrip.domain.service;

import com.proafsolutions.cubatrip.domain.model.enums.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.enums.ProvinceEnum;
import com.proafsolutions.cubatrip.domain.model.enums.RateEnum;
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

    List<Product> getProductsByCategory(CategoryEnum category, ProvinceEnum province);

    void doCheckIn();

    void doProductReview(Review review);

    List<Review> getReviews(long productId);

    List<Review> getReviewsByRate(long productId, RateEnum rate);

    List<Review> getReviewsFrom(long productId, Date from);
}
