package com.proafsolutions.cubatrip.infrastructure.dal.repository;

/**
 * Created by alex on 4/17/2016.
 */
public class RepositoryProvider {

    private static final ProductRepository _productRepository = new ProductRepository();

    private static final ReviewRepository _reviewRepository = new ReviewRepository();

    private static final UserSettingsRepository _userSettingsRepository = new UserSettingsRepository();


    public static ProductRepository getProductRepository() {
        return _productRepository;
    }

    public static ReviewRepository getReviewRepository() {
        return _reviewRepository;
    }

    public static UserSettingsRepository getUserSettingsRepository() {
        return _userSettingsRepository;
    }
}
