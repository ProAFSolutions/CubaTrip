package com.proafsolutions.cubatrip.infrastructure.broker;

import android.util.Log;

import com.proafsolutions.cubatrip.artifacts.Constants;
import com.proafsolutions.cubatrip.infrastructure.broker.json.JsonCategory;
import com.proafsolutions.cubatrip.infrastructure.broker.json.JsonProduct;
import com.proafsolutions.cubatrip.infrastructure.broker.json.JsonServerResponse;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.List;

/**
 * Created by alex on 4/20/2016.
 */
public class WebServiceBroker implements IWebServiceBroker {

    private static volatile IWebServiceBroker INSTANCE = null;

    protected WebServiceBroker(){}

    public static IWebServiceBroker getInstance() {
        if (INSTANCE == null) {
            synchronized (WebServiceBroker.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WebServiceBroker();
                }
            }
        }
        return INSTANCE;
    }

    private <T> T doServerRequest(String action, Class<T> responseType){
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            String url = String.format(Constants.WEB_SERVICE_URL, action);
            return restTemplate.getForObject(String.valueOf(new URL(url)), responseType);
        } catch (Exception e) {
            Log.e("WebServiceBroker", e.getMessage(), e);
        }
        return null;
    }


    @Override
    public JsonServerResponse requestFullData() {
        return null;
    }

    @Override
    public List<JsonProduct> requestProducts() {
        return null;
    }

    @Override
    public List<JsonCategory> requestCategories() {
        return null;
    }


}
