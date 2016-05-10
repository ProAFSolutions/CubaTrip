package com.proafsolutions.cubatrip.domain.service;

import com.proafsolutions.cubatrip.domain.model.ProductCheckIn;
import com.proafsolutions.cubatrip.domain.model.enums.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.enums.ProvinceEnum;
import com.proafsolutions.cubatrip.domain.model.enums.RateEnum;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;

import java.util.Date;
import java.util.List;

/**
 * Created by alex on 4/17/2016.
 */
public class BLServiceCatalog implements IBLService {

    private static volatile IBLService INSTANCE = null;

    protected BLServiceCatalog(){}

    public static IBLService getInstance() {
        if (INSTANCE == null) {
            synchronized (BLServiceCatalog.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BLServiceCatalog();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Product getProductById(long id) {
       return RepositoryProvider.getProductRepository()
                                .load(id);
    }

    @Override
    public Product getProductByRemoteId(long remoteId) {
        return RepositoryProvider.getProductRepository()
                                 .retrieveProductByRemoteId(remoteId);
    }

    @Override
    public List<Product> getProductsByCategory(CategoryEnum category, ProvinceEnum province) {
        return RepositoryProvider.getProductRepository().retrieveProductsByCategory(category, province);
    }

    @Override
    public void doCheckIn(ProductCheckIn checkIn) {
        RepositoryProvider.getCheckInRepository().save(checkIn);
    }

    @Override
    public void doProductReview(Review review) {
        RepositoryProvider.getReviewRepository().save(review);
    }

    @Override
    public void pushPendingReviews() {

    }

    @Override
    public void addNewReviews(List<Review> reviews) {
        RepositoryProvider.getReviewRepository().saveAll(reviews);
    }

    @Override
    public List<Review> getReviews(long productId) {
       return RepositoryProvider.getReviewRepository().retrieveReviews(productId);
    }

    @Override
    public List<Review> getReviewsByRate(long productId, RateEnum rate) {
        return RepositoryProvider.getReviewRepository().retrieveReviewsByRate(productId, rate);
    }

    @Override
    public List<Review> getReviewsFrom(long productId, Date from) {
        return null;
    }


}
