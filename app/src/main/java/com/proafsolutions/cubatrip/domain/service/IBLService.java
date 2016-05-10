package com.proafsolutions.cubatrip.domain.service;

import com.proafsolutions.cubatrip.domain.model.ProductCheckIn;
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
public interface IBLService {

    Product getProductById(long id);

    Product getProductByRemoteId(long remoteId);

    List<Product> getProductsByCategory(CategoryEnum category, ProvinceEnum province);

    List<Review> getReviews(long productId);

    List<Review> getReviewsByRate(long productId, RateEnum rate);

    List<Review> getReviewsFrom(long productId, Date from);

    void doCheckIn(ProductCheckIn checkIn);

    void doProductReview(Review review);

    void pushPendingReviews();

    void addNewReviews(List<Review> reviews);
}
