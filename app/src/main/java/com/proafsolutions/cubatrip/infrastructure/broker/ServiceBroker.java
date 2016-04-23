package com.proafsolutions.cubatrip.infrastructure.broker;

import com.proafsolutions.cubatrip.infrastructure.broker.json.JsonProduct;
import com.proafsolutions.cubatrip.infrastructure.broker.json.JsonServerResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Created by alex on 4/23/2016.
 */
public class ServiceBroker {

    private static IServerAPIClient client = ServiceGenerator.createService(IServerAPIClient.class);

    public static JsonServerResponse requestFullData(){
        JsonServerResponse response = null;
        Call<JsonServerResponse> call = client.getFullData();
        try {
            response = call.execute().body();
        } catch (IOException e) {
            // handle errors
        }
        return response;
    }

    public static List<JsonProduct> requestProducts(){
        List<JsonProduct> response = null;
        Call<List<JsonProduct>> call = client.getProducts();
        try {
           response = call.execute().body();
        } catch (IOException e) {
            // handle errors
        }
        return response;
    }

}
