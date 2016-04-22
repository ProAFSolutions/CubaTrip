package com.proafsolutions.cubatrip.android;

import com.proafsolutions.cubatrip.domain.model.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Province;
import com.proafsolutions.cubatrip.domain.specification.GeoLocation;
import com.proafsolutions.cubatrip.domain.specification.ProductDetails;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.ProductRepository;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.ProvinceRepository;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by alex on 4/21/2016.
 */
public class TestRepository {



    @Before
    public void setUp(){

    }

    @After
    public void tearDown() {

    }

    @Test
    public void createProvince() throws Exception {
        for (Province P : RepositoryProvider.getProvinceRepository().loadAll()) {
            P.delete();
        }
        RepositoryProvider.getProvinceRepository().save(new Province("Habana"));
    }

    @Test
    public void createProduct() throws Exception {
        Province province = RepositoryProvider.getProvinceRepository().loadAll().get(0);

        Map<String, String> carretaServices = new HashMap<String, String>();
        carretaServices.put("Hours", "From 7:00 am to 10:00 pm");
        carretaServices.put("Dishes", "SeaFood, Italian Food, Cuban Buffet");

        Product product1 = new Product(
                0,//remoteId
                "La Carreta", "Delicous Cuban Cousine",
                CategoryEnum.RESTAURANTS,
                province,
                new ProductDetails(
                         "Filiberto Lopez",
                         "Calle M / C y K Vedado",
                         "537985743",
                         "filito@gmail.com",
                         "www.cubatrip.com/Carreta",
                         carretaServices
                ),
                new GeoLocation(
                        "-83.98765143",
                        "92.45788967"
        ));

        Product product2 = new Product(
                0,//remoteId
                "Universidad de La Habana",
                "Universidad",
                CategoryEnum.EDUCATION,
                province,
                new ProductDetails(
                        "Alejandro Clavijo",
                        "El Cotorro",
                        "537867876",
                        "alex@gmail.com",
                        "www.cubatrip.com/uc",
                        new HashMap<String, String>()
                ),
                new GeoLocation(
                        "-83.34562334",
                        "92.839379990"
        ));

        RepositoryProvider.getProductRepository().save(product1);
        RepositoryProvider.getProductRepository().save(product2);
     }

}
