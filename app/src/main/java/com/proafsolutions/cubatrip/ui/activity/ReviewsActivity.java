package com.proafsolutions.cubatrip.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.ui.presenter.ReviewsPresenter;

public class ReviewsActivity extends AppCompatActivity {

    private ReviewsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        presenter = new ReviewsPresenter(ReviewsActivity.this);
        presenter.RefreshList();
    }
}
