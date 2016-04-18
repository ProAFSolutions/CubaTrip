package com.proafsolutions.cubatrip.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.artifacts.Constants;
import com.proafsolutions.cubatrip.domain.model.Category;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;

import java.util.List;


public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
    }

    public void onCreateCategoryClick(View view)
    {
        try{

            RepositoryProvider.getCategoryRepository().save(new Category("Restaurants", "restaurant_logo.png"));
            RepositoryProvider.getCategoryRepository().save(new Category("Cafeteria", "cafeteria.png"));

            List<Category> categories = RepositoryProvider.getCategoryRepository().loadAll();

            for (Category c: categories) {
                System.out.println(c.getName());
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage() + " " + ex.getStackTrace().toString());
        }

    }

    public void onCreateProductClick(View view)
    {

    }
}
