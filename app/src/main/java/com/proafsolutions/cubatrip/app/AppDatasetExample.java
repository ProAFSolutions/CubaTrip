package com.proafsolutions.cubatrip.app;

import android.util.Log;

import com.proafsolutions.cubatrip.domain.model.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Province;
import com.proafsolutions.cubatrip.domain.model.RateEnum;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.domain.specification.GeoLocation;
import com.proafsolutions.cubatrip.domain.specification.ProductDetails;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.ProductRepository;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.ProvinceRepository;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 4/21/2016.
 */
public class AppDatasetExample {


    public AppDatasetExample(boolean init){
        if(init){
            createProvince();
            createProducts();
            createReviews();
        }
        printOutProducts();
    }

    public void createProvince() {
        ProvinceRepository repo = RepositoryProvider.getProvinceRepository();
        if(repo.loadAll().size() == 0){
            Province province = new Province("Habana");
            repo.save(province);
            Log.i("AppDatasetExample","Province inserted: " + province.getId());

            Province province2 = new Province("Camaguey");
            repo.save(province2);
            Log.i("AppDatasetExample","Province inserted: " + province2.getId());
        }
    }

    public void createProducts() {
        ProductRepository prodRepo = RepositoryProvider.getProductRepository();

        Province province = RepositoryProvider.getProvinceRepository().loadAll().get(0);

        Map<String, String> carretaServices = new HashMap<String, String>();
        carretaServices.put("Dishes", "SeaFood, Italian Food, Cuban Buffet");

        List<String> images = new ArrayList<String>();
        images.add("image1.jpg");
        images.add("image2.jpg");
        images.add("image3.jpg");

        Product product1 = new Product(
                111,//remoteId
                "La Carreta",
                "Delicous Cuban Cousine",
                "9:00 am to 10:00pm",
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
                ),
                images
        );

        Product product2 = new Product(
                222,//remoteId
                "Universidad de La Habana",
                "Universidad",
                "9:00 am to 5:00pm",
                CategoryEnum.EDUCATION,
                province,
                new ProductDetails(
                        "Alejandro Clavijo",
                        "El Cotorro",
                        "537867876",
                        "alex@gmail.com",
                        "www.cubatrip.com/uc",
                        carretaServices
                ),
                new GeoLocation(
                        "-83.34562334",
                        "92.839379990"
                ),
                images
        );

        Product product3 = new Product(
                333,//remoteId
                "Habana Libre",
                "Hotel",
                "24 hours",
                CategoryEnum.HOTEL,
                province,
                new ProductDetails(
                        "Bebilla",
                        "vedado",
                        "537867334",
                        "bebi@gmail.com",
                        "www.cubatrip.com/hotels",
                        carretaServices
                ),
                new GeoLocation(
                        "-83.345232334",
                        "92.8393445990"
                ),
                images
        );

        List<Product> prods = new ArrayList<Product>();
        prods.add(product1);
        prods.add(product2);
        prods.add(product3);

        prodRepo.saveAll(prods);

    }

    public void createReviews(){
        Product product = RepositoryProvider.getProductRepository().loadAll().get(0);

        List<Review> reviews = new ArrayList<Review>();
        reviews.add(new Review(RateEnum.GOOD, "Nice people and nice place", product));
        reviews.add(new Review(RateEnum.AVERAGE, "Very old place", product));
        reviews.add(new Review(RateEnum.VERY_BAD, "No internet connection around", product));

        RepositoryProvider.getReviewRepository().saveAll(reviews);
    }


    public void printOutProducts(){

        for (Province P :RepositoryProvider.getProvinceRepository().loadAll()) {
            Log.i("AppDatasetExample", (P.getName() != null ? P.getName() : "NULL"));
        }

        List<Product> prods = RepositoryProvider.getProductRepository().loadAll();
        for (Product P : prods) {
            Log.i("AppDatasetExample", String.format("Id: %s, Name: %s, Province: %s, Services Count: %s, Images: %s",
                P.getId(), P.getName(), P.getProvince().getName(), P.getServices().size(), P.getImages().size()
            ));
        }

        for (Review R : RepositoryProvider.getReviewRepository().retrieveReviews(prods.get(0).getId())) {
            Log.i("AppDatasetExample", String.format("Rate: %s, Rate Value: %s, Date: %s",
                    R.getRate().name(), R.getRate().getRate(), R.getDate().toString()
            ));
        }
    }
}
