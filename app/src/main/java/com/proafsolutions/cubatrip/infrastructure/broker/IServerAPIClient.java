package com.proafsolutions.cubatrip.infrastructure.broker;

import com.proafsolutions.cubatrip.infrastructure.broker.json.JsonReview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by alex on 4/20/2016.
 */
public interface IServerAPIClient {

   @GET("api/CubaTrip/Review")
   Call<List<JsonReview>> getReviews();

}
