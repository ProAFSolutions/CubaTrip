package com.proafsolutions.cubatrip.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.ui.presenter.DetailsPresenter;
import com.proafsolutions.cubatrip.ui.presenter.MainPresenter;

public class DetailsActivity extends AppCompatActivity {

    private DetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        presenter = new DetailsPresenter(DetailsActivity.this);
        presenter.LoadProduct();
    }
}
