package com.proafsolutions.cubatrip.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
        ImageView navigate = (ImageView) findViewById(R.id.imgNavigate);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.SetLocation();
            }
        });

        ImageView review = (ImageView) findViewById(R.id.imgReview);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                // .setAction("Action", null).show();
                EditText reviewContact = (EditText) findViewById(R.id.reviewContact);
                reviewContact.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(reviewContact, InputMethodManager.SHOW_IMPLICIT);

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
        });

        RatingBar ratingBar = (RatingBar)findViewById(R.id.reviewRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                presenter.ChangeRate();
            }
        });

        Button buttonPrev = (Button) findViewById(R.id.prev);
        Button buttonNext = (Button) findViewById(R.id.next);

        buttonPrev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                presenter.showPrevious();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                presenter.showNext();
            }
        });


    }

    public void onClick(View v) {
        presenter.ShowReviewsList();
    }


}
