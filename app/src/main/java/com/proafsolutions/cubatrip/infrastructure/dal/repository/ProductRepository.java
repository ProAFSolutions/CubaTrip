package com.proafsolutions.cubatrip.infrastructure.dal.repository;

import com.activeandroid.query.Select;
import com.proafsolutions.cubatrip.domain.model.Category;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Province;

import java.util.List;

/**
 * Created by alex on 4/17/2016.
 */
public class ProductRepository extends AbstractRepository<Product>{

    @Override
    public Class<Product> getModelClass() {
        return Product.class;
    }

    public Product retrieveProductByRemoteId(long remoteId){
        return new Select()
                .from(Product.class)
                .where("remoteId = ?", remoteId)
                .executeSingle();
    }

    public List<Product> retrieveProductsByCategory(long categoryId){
        return new Select()
                .from(Product.class)
                .where("category = ?", categoryId)
                .execute();
    }

    public List<Product> retrieveProductsByCategory(long categoryId, long provinceId){
        return new Select()
                .from(Product.class)
                .where("category = ?", categoryId)
                .where("province = ?", provinceId)
                .execute();
    }

}
