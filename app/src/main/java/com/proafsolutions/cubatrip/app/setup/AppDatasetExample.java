package com.proafsolutions.cubatrip.app.setup;

import android.util.Log;

import com.proafsolutions.cubatrip.domain.model.enums.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.enums.ProvinceEnum;
import com.proafsolutions.cubatrip.domain.model.enums.RateEnum;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.domain.model.specification.GeoLocation;
import com.proafsolutions.cubatrip.domain.model.specification.ProductDetails;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.ProductRepository;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 4/21/2016.
 */
public class AppDatasetExample {


    public AppDatasetExample(){
        if(RepositoryProvider.getProductRepository().loadAll().size() == 0){
            createProducts();
            createReviews();
        }
        printOutProducts();
    }

    public void createProducts() {
        ProductRepository prodRepo = RepositoryProvider.getProductRepository();

        ProvinceEnum province = ProvinceEnum.LA_HABANA;

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

        List<Product> prods = RepositoryProvider.getProductRepository().loadAll();
        for (Product P : prods) {
            Log.i("AppSetup", String.format("Id: %s, Name: %s, Province: %s, Services Count: %s, Images: %s",
                P.getId(), P.getName(), P.getProvince().getDescriptor(), P.getServices().size(), P.getImages().size()
            ));
        }

        for (Review R : RepositoryProvider.getReviewRepository().retrieveReviews(prods.get(0).getId())) {
            Log.i("AppSetup", String.format("Rate: %s, Rate Value: %s, Date: %s",
                    R.getRate().name(), R.getRate().getRate(), R.getDate().toString()
            ));
        }
    }
}
