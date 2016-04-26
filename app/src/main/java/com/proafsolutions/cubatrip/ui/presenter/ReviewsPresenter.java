package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.domain.service.ServiceCatalog;
import com.proafsolutions.cubatrip.ui.activity.CategoriesActivity;
import com.proafsolutions.cubatrip.ui.activity.DetailsActivity;
import com.proafsolutions.cubatrip.ui.activity.ReviewsActivity;
import com.proafsolutions.cubatrip.ui.adapter.ListAdapter;
import com.proafsolutions.cubatrip.ui.adapter.ListReviewsAdapter;
import com.proafsolutions.cubatrip.util.Utils;

import java.util.ArrayList;
import java.util.List;


public class ReviewsPresenter extends AbstractPresenter {

    private ReviewsActivity activity;

    public ReviewsPresenter(ReviewsActivity activity) {
        this.activity = activity;
    }

    public List<Review> reviews;


    @Override
    protected Activity getCurrentActivity() {
        return activity;
    }



    public void RefreshList()
    {
        long IdProduct = getActivityParameters().getLong("idProduct");
        Product product = ServiceCatalog.getInstance().getProductById(IdProduct);

        TextView text = (TextView)activity.findViewById(R.id.productText);
        text.setText(product.getName());

        reviews = ServiceCatalog.getInstance().getReviews(IdProduct);

        String[] stringsNames = new String[reviews.size()];

        ListReviewsAdapter adapter = new ListReviewsAdapter(activity,stringsNames, reviews);

        ListView lv = (ListView)activity.findViewById(R.id.listReviews);
        lv.setAdapter(adapter);
    }

    }
