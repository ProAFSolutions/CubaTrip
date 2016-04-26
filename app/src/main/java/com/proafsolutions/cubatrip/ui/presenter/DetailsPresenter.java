package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.domain.service.ServiceCatalog;
import com.proafsolutions.cubatrip.ui.activity.DetailsActivity;
import com.proafsolutions.cubatrip.ui.activity.MapActivity;
import com.proafsolutions.cubatrip.ui.activity.ReviewsActivity;
import com.proafsolutions.cubatrip.util.Utils;

import java.util.Date;
import java.util.List;

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

        RefreshReviews();

        ((TextView)activity.findViewById(R.id.firstLine)).setText(product.getName());
        ((TextView)activity.findViewById(R.id.secondLine)).setText(product.getDescription());

        ((TextView)activity.findViewById(R.id.Address)).setText(product.getAddress());
        ((TextView)activity.findViewById(R.id.Phone)).setText(product.getPhone());
        ((TextView)activity.findViewById(R.id.Web)).setText(product.getWebSiteUrl());

        ((TextView)activity.findViewById(R.id.ServicesText)).setText(product.getServices().get("Dishes"));

    }

    public void SetLocation()
    {
        Bundle params = new Bundle();
        params.putString("longitude",product.getLongitude());
        params.putString("latitude",product.getLatitude());
        this.openNewActivityPassingData(MapActivity.class,params);
    }

    public void NewReview()
    {
        Review reviewNew = new Review();

        String reviewComments =  ((TextView)activity.findViewById(R.id.reviewComments)).getText().toString();
        float rating = ((RatingBar)activity.findViewById(R.id.reviewRating)).getRating();

        String reviewContact =  ((TextView)activity.findViewById(R.id.reviewContact)).getText().toString();

        if(rating==0) {
            Snackbar.make(activity.getCurrentFocus(), "You should select a rating.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }

        if(reviewContact.isEmpty()) {
            Snackbar.make(activity.getCurrentFocus(), "You should write your name.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }

        if(reviewComments.isEmpty()) {
            Snackbar.make(activity.getCurrentFocus(), "You should a write a comments.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }

        reviewNew.setComments(reviewComments);
        reviewNew.setDate(new Date());
        reviewNew.setSync(false);
        reviewNew.setProduct(product);
        reviewNew.setRate(Utils.GetRateEnum((int)rating));
        reviewNew.setContact(reviewContact);

        ServiceCatalog.getInstance().doProductReview(reviewNew);
        RefreshReviews();

    }


    public void RefreshReviews()
    {
        List<Review> reviews =   ServiceCatalog.getInstance().getReviews(product.getId());

        if(reviews.size()==0) {
            ((RatingBar) activity.findViewById(R.id.ratingBar)).setRating(0);
            ((TextView) activity.findViewById(R.id.lineReviews)).setText("Not reviews yet.");
        }
        else {
            ((TextView) activity.findViewById(R.id.lineReviews)).setText("(" + reviews.size() + ") Reviews");
            int totalReview = 0;
            for (Review rev:reviews) {
                totalReview+=rev.getRate().ordinal();
            }
            ((RatingBar)activity.findViewById(R.id.ratingBar)).setRating(totalReview/reviews.size());

        }

        ((TextView)activity.findViewById(R.id.reviewComments)).setText("");
        ((RatingBar)activity.findViewById(R.id.reviewRating)).setRating(0);
        ((TextView)activity.findViewById(R.id.reviewContact)).setText("");

    }

    public void ShowReviewsList()
    {
        Bundle params = new Bundle();
        params.putLong("idProduct",product.getId());
        this.openNewActivityPassingData(ReviewsActivity.class,params);
    }
}
