package com.proafsolutions.cubatrip.infrastructure.broker;

import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.infrastructure.broker.json.JsonReview;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Created by alex on 4/23/2016.
 */
public class ServiceBroker {

    private static IServerAPIClient client = ServiceGenerator.createService(IServerAPIClient.class);

    public static List<Review> requestReviews(){
        List<Review> result = null;
        Call<List<JsonReview>> call = client.getReviews();
        try {
            List<JsonReview> response = call.execute().body();
            for (JsonReview jsonReview: response) {
                result.add(jsonReview.toReview());
            }
        } catch (IOException e) {
            // handle errors
        }
        return result;
    }

}
