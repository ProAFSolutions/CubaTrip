package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.domain.service.BLServiceCatalog;
import com.proafsolutions.cubatrip.ui.activity.ReviewsActivity;
import com.proafsolutions.cubatrip.ui.adapter.ListReviewsAdapter;

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
        Product product = BLServiceCatalog.getInstance().getProductById(IdProduct);

        TextView text = (TextView)activity.findViewById(R.id.productText);
        text.setText(product.getName());

        reviews = BLServiceCatalog.getInstance().getReviews(IdProduct);

        String[] stringsNames = new String[reviews.size()];

        ListReviewsAdapter adapter = new ListReviewsAdapter(activity,stringsNames, reviews);

        ListView lv = (ListView)activity.findViewById(R.id.listReviews);
        lv.setAdapter(adapter);
    }

    }
