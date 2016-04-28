package com.proafsolutions.cubatrip.infrastructure.dal.repository;

import com.activeandroid.query.Select;
import com.proafsolutions.cubatrip.domain.model.enums.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.enums.ProvinceEnum;

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

    public List<Product> retrieveProductsByCategory(CategoryEnum category){
        return new Select()
                .from(Product.class)
                .where("category = ?", category)
                .execute();
    }

    public List<Product> retrieveProductsByCategory(CategoryEnum category, ProvinceEnum province){
        return new Select()
                .from(Product.class)
                .where("category = ?", category)
                .where("province = ?", province)
                .execute();
    }

}
