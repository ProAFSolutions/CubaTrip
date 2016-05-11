package com.proafsolutions.cubatrip.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.RatingBar;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.ui.presenter.DetailsPresenter;

public class DetailsActivity extends AppCompatActivity {

    private DetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        presenter = new DetailsPresenter(DetailsActivity.this);
        presenter.LoadProduct();
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.buttonMap);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       // .setAction("Action", null).show();
                presenter.SetLocation();
            }
        });

        AppCompatButton post = (AppCompatButton) findViewById(R.id.buttonPost);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                // .setAction("Action", null).show();
                presenter.NewReview();
            }
        });*/

        RatingBar ratingBar = (RatingBar)findViewById(R.id.reviewRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                presenter.ChangeRate();
            }
        });
    }

    public void onClick(View v) {
        presenter.ShowReviewsList();
    }


}
