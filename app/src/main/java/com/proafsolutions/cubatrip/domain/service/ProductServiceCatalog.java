package com.proafsolutions.cubatrip.domain.service;

import com.proafsolutions.cubatrip.domain.model.Category;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;

import java.util.List;

/**
 * Created by alex on 4/17/2016.
 */
public class ProductServiceCatalog implements IProductServiceCatalog {

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
    public List<Product> getProductsByCategory(long categoryId) {
        return RepositoryProvider.getProductRepository()
                                 .retrieveProductsByCategory(categoryId);
    }

    @Override
    public List<Product> getProductsByCategory(long categoryId, long provinceId) {
        return RepositoryProvider.getProductRepository()
                                 .retrieveProductsByCategory(categoryId, provinceId);
    }

    @Override
    public List<Category> getCategoriesByProvince(long provinceId) {
        return null;
    }

    @Override
    public void rateProduct(long productId, int rate) {
      Product prod = RepositoryProvider.getProductRepository().load(productId);
      if(prod != null){

      }
    }

    @Override
    public void doCheckIn() {

    }
}
