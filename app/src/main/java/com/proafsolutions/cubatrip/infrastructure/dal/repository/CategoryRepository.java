package com.proafsolutions.cubatrip.infrastructure.dal.repository;

import com.proafsolutions.cubatrip.domain.model.Category;

/**
 * Created by alex on 4/17/2016.
 */
public class CategoryRepository extends  AbstractRepository<Category>{

    @Override
    public Class<Category> getModelClass() {
        return Category.class;
    }
}
