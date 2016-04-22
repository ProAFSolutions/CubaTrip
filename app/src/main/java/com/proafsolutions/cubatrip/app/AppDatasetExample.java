package com.proafsolutions.cubatrip.app;

import android.util.Log;

import com.proafsolutions.cubatrip.domain.model.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Province;
import com.proafsolutions.cubatrip.domain.specification.GeoLocation;
import com.proafsolutions.cubatrip.domain.specification.ProductDetails;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.ProductRepository;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.ProvinceRepository;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 4/21/2016.
 */
public class AppDatasetExample {


    public AppDatasetExample(boolean init){
        createProvince();
        createProducts(init);
    }

    public void createProvince() {
        ProvinceRepository repo = RepositoryProvider.getProvinceRepository();
        if(repo.loadAll().size() == 0){
            Province province = new Province("Habana");
            repo.save(province);
            Log.i("AppDatasetExample","Province inserted: " + province.getId());
        }
    }

    public void createProducts(boolean init) {
        ProductRepository prodRepo = RepositoryProvider.getProductRepository();
        if(init){
            for (Product P :prodRepo.loadAll()) {
                P.delete();
            }
        }

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

        prodRepo.save(product1);
        Log.i("AppDatasetExample","Product inserted: " + product1.getId());

        prodRepo.save(product2);
        Log.i("AppDatasetExample","Product inserted: " + product2.getId());
    }
}
