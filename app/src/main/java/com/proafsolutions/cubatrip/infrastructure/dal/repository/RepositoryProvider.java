package com.proafsolutions.cubatrip.infrastructure.dal.repository;

/**
 * Created by alex on 4/17/2016.
 */
public class RepositoryProvider {

    private static final ProductRepository _productRepository = new ProductRepository();

    private static final CategoryRepository _categoryRepository = new CategoryRepository();

    private static final ProvinceRepository _provinceRepository = new ProvinceRepository();

    private static final ReviewRepository _reviewRepository = new ReviewRepository();


    public static ProductRepository getProductRepository() {
        return _productRepository;
    }

    public static CategoryRepository getCategoryRepository() {
        return _categoryRepository;
    }

    public static ProvinceRepository getProvinceRepository() {
        return _provinceRepository;
    }

    public static ReviewRepository getReviewRepository() {
        return _reviewRepository;
    }
}
