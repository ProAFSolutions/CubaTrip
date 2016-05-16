package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.domain.model.enums.RateEnum;
import com.proafsolutions.cubatrip.domain.service.BLServiceCatalog;
import com.proafsolutions.cubatrip.infrastructure.io.FileManager;
import com.proafsolutions.cubatrip.ui.activity.DetailsActivity;
import com.proafsolutions.cubatrip.ui.activity.MapActivity;
import com.proafsolutions.cubatrip.ui.activity.ReviewsActivity;
import com.proafsolutions.cubatrip.ui.util.Utils;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by fily on 4/18/2016.
 */



public class DetailsPresenter extends AbstractPresenter {

    private DetailsActivity activity;

    public DetailsPresenter(DetailsActivity activity) {
        this.activity = activity;
    }

    public Product product;

    public List<Bitmap>  images;

    public int current=0;

    @Override
    protected Activity getCurrentActivity() {
        return activity;
    }

    public void LoadProduct()
    {
      long IdProduct = getActivityParameters().getLong("idProduct");
      product =   BLServiceCatalog.getInstance().getProductById(IdProduct);

        RefreshReviews();

        ((TextView)activity.findViewById(R.id.firstLine)).setText(product.getName());
        ((TextView)activity.findViewById(R.id.secondLine)).setText(product.getDescription());

        ((TextView)activity.findViewById(R.id.Address)).setText(product.getAddress());
        ((TextView)activity.findViewById(R.id.Phone)).setText(product.getPhone());
        ((TextView)activity.findViewById(R.id.Web)).setText(product.getWebSiteUrl());

        ((TextView)activity.findViewById(R.id.ServicesText)).setText(product.getServices().get("Dishes"));

        LoadImages();

        if(images.size()>1) {
            // Get the ViewFlipper
            ViewFlipper mViewFlipper = (ViewFlipper) activity.findViewById(R.id.ImageProduct);
            mViewFlipper.setInAnimation(activity, android.R.anim.fade_in);
            mViewFlipper.setOutAnimation(activity, android.R.anim.fade_out);

            // Add all the images to the ViewFlipper
           for (int i = 0; i < images.size(); i++) {
                ImageView imageView = new ImageView(activity);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
               // imageView.setImageResource(R.drawable.image_test);
               imageView.setImageBitmap(images.get(i));
                mViewFlipper.addView(imageView);
            }

        }
        else
        {
            LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.optionImages);
            linearLayout.setVisibility(View.INVISIBLE);
        }


    }

    public void showNext()
    {
        ViewFlipper mViewFlipper = (ViewFlipper) activity.findViewById(R.id.ImageProduct);
        mViewFlipper.showNext();
    }

    public void showPrevious()
    {
        ViewFlipper mViewFlipper = (ViewFlipper) activity.findViewById(R.id.ImageProduct);
        mViewFlipper.showPrevious();
    }
    public void LoadImages()
    {
        images = new ArrayList<Bitmap>();

        for(int i=0; i<product.getImages().size();i++)
        {
            File imgFile = new File(FileManager.getResourcesFolder()+"/"+product.getProvince().toString()+"/"+product.getId()+"/"+product.getImages().get(i).toString());

            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                images.add(myBitmap);

            }
            else
            {
                //Not image
                Bitmap drawable = BitmapFactory.decodeResource(activity.getResources(),
                        R.drawable.no_image);
                images.add(drawable);
            }
        }


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
        reviewNew.setRate(RateEnum.getRate((int)rating));
        reviewNew.setContact(reviewContact);

        BLServiceCatalog.getInstance().doProductReview(reviewNew);
        RefreshReviews();

    }


    public void RefreshReviews()
    {
        List<Review> reviews =   BLServiceCatalog.getInstance().getReviews(product.getId());

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

    public void ChangeRate()
    {
        RatingBar r = (RatingBar)activity.findViewById(R.id.reviewRating);
        RateEnum rate = RateEnum.getRate((int)r.getRating());
        ((TextView)activity.findViewById(R.id.WriteReviewText)).setText("Write Review ("+rate.toString()+")");
    }
}
