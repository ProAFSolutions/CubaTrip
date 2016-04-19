package com.proafsolutions.cubatrip.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.ui.presenter.DatabasePresenter;


public class DatabaseActivity extends AppCompatActivity {

    private DatabasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        presenter = new DatabasePresenter(DatabaseActivity.this);
    }

    public void onCreateCategoryClick(View view) {
       presenter.createCategory();
    }

    public void onCreateProductClick(View view){

    }

    public void onOpenMapClick(View view){
        presenter.openMap();
    }
}
