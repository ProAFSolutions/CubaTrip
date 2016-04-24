package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.widget.TextView;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.service.ServiceCatalog;
import com.proafsolutions.cubatrip.ui.activity.DetailsActivity;
import com.proafsolutions.cubatrip.ui.activity.MainActivity;

import java.util.Map;

/**
 * Created by fily on 4/18/2016.
 */
public class DetailsPresenter extends AbstractPresenter {

    private DetailsActivity activity;

    public DetailsPresenter(DetailsActivity activity) {
        this.activity = activity;
    }

    public Product product;

    @Override
    protected Activity getCurrentActivity() {
        return activity;
    }

    public void LoadProduct()
    {
      long IdProduct = getActivityParameters().getLong("idProduct");
      product =   ServiceCatalog.getInstance().getProductById(IdProduct);

        ((TextView)activity.findViewById(R.id.firstLine)).setText(product.getName());
        ((TextView)activity.findViewById(R.id.secondLine)).setText(product.getDescription());

        ((TextView)activity.findViewById(R.id.Address)).setText(product.getAddress());
        ((TextView)activity.findViewById(R.id.Phone)).setText(product.getPhone());
        ((TextView)activity.findViewById(R.id.Web)).setText(product.getWebSiteUrl());

        ((TextView)activity.findViewById(R.id.ServicesText)).setText(product.getServices().get("Dishes"));

    }
}
