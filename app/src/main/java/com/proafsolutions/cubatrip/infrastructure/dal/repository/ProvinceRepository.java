package com.proafsolutions.cubatrip.infrastructure.dal.repository;

import com.proafsolutions.cubatrip.domain.model.Province;

/**
 * Created by alex on 4/17/2016.
 */
public class ProvinceRepository extends  AbstractRepository<Province>{

    @Override
    public Class<Province> getModelClass() {
        return Province.class;
    };
}
