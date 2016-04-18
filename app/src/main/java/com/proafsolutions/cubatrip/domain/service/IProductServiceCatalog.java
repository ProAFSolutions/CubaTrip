package com.proafsolutions.cubatrip.domain.service;

import com.proafsolutions.cubatrip.domain.model.Category;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Province;

import java.util.List;

/**
 * Created by alex on 4/17/2016.
 */
public interface IProductServiceCatalog {

    Product getProductById(long id);

    Product getProductByRemoteId(long remoteId);

    List<Product> getProductsByCategory(long categoryId);

    List<Product> getProductsByCategory(long categoryId, long provinceId);

    List<Category> getCategoriesByProvince(long provinceId);

    void rateProduct(long productId, int rate);

    void doCheckIn();
}
