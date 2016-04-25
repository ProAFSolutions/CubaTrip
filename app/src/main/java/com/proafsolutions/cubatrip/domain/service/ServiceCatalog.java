package com.proafsolutions.cubatrip.domain.service;

import com.proafsolutions.cubatrip.domain.model.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.RateEnum;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;

import java.util.Date;
import java.util.List;

/**
 * Created by alex on 4/17/2016.
 */
public class ServiceCatalog implements IServiceCatalog {

    private static volatile IServiceCatalog INSTANCE = null;

    protected ServiceCatalog(){}

    public static IServiceCatalog getInstance() {
        if (INSTANCE == null) {
            synchronized (ServiceCatalog.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceCatalog();
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
    public List<Product> getProductsByCategory(CategoryEnum category) {
        return RepositoryProvider.getProductRepository()
                                 .retrieveProductsByCategory(category);
    }

    @Override
    public List<Product> getProductsByCategory(CategoryEnum category, long provinceId) {
        return RepositoryProvider.getProductRepository()
                                 .retrieveProductsByCategory(category, provinceId);
    }

    @Override
    public void doCheckIn() {

    }

    @Override
    public void doProductReview(Review review) {
        RepositoryProvider.getReviewRepository().save(review);
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
