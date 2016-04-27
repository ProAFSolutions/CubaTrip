package com.proafsolutions.cubatrip.infrastructure.broker;

import com.proafsolutions.cubatrip.infrastructure.config.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alex on 4/22/2016.
 */
public class ServiceGenerator {

    private static OkHttpClient.Builder _httpClient =  new OkHttpClient.Builder();

    private static Retrofit.Builder _builder  = new Retrofit.Builder()
                                                .baseUrl(Constants.WEB_API_BASE_URL)
                                                .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = _builder.client(_httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

}
