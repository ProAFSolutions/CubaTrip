package com.proafsolutions.cubatrip.infrastructure.dal.repository;

import com.proafsolutions.cubatrip.domain.model.ProductCheckIn;

/**
 * Created by alex on 5/6/2016.
 */
public class CheckInRepository extends AbstractRepository<ProductCheckIn> {

    @Override
    public Class<ProductCheckIn> getModelClass() {
        return ProductCheckIn.class;
    }
}
