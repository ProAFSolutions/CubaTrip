package com.proafsolutions.cubatrip.infrastructure.broker;

import com.proafsolutions.cubatrip.infrastructure.broker.json.JsonCategory;
import com.proafsolutions.cubatrip.infrastructure.broker.json.JsonProduct;
import com.proafsolutions.cubatrip.infrastructure.broker.json.JsonServerResponse;

import java.util.List;

/**
 * Created by alex on 4/20/2016.
 */
public interface IWebServiceBroker {

   JsonServerResponse requestFullData();

   List<JsonProduct> requestProducts();

   List<JsonCategory> requestCategories();


}
