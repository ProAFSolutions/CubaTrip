package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.proafsolutions.cubatrip.artifacts.Constants;
import com.proafsolutions.cubatrip.domain.model.Category;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.infrastructure.dal.repository.RepositoryProvider;
import com.proafsolutions.cubatrip.ui.activity.DatabaseActivity;
import com.proafsolutions.cubatrip.ui.activity.MainActivity;

import java.util.List;

/**
 * Created by alex on 4/18/2016.
 */
public class DatabasePresenter extends AbstractPresenter{

    private DatabaseActivity activity;

    public DatabasePresenter(DatabaseActivity activity){
        this.activity = activity;
    }

    @Override
    protected Activity getCurrentActivity() {
        return this.activity;
    }

    public void createCategory(){
        try{
            RepositoryProvider.getCategoryRepository().save(new Category("Restaurants", "restaurant_logo.png"));
            RepositoryProvider.getCategoryRepository().save(new Category("Cafeteria", "cafeteria.png"));
            List<Category> categories = RepositoryProvider.getCategoryRepository().loadAll();
            for (Category c: categories) {
                Log.i("DatabasePresenter", c.getName());
            }
        }catch (Exception ex){
            Log.e("DatabasePresenter", ex.getMessage() + " " + ex.getStackTrace().toString());
        }
    }

    public void createProduct(){
        try{
            Product product = new Product();

           // RepositoryProvider.getProductRepository().save(product);
        }catch (Exception ex){
            Log.e("DatabasePresenter", ex.getMessage() + " " + ex.getStackTrace().toString());
        }
    }

    public void openMap(){
        Bundle params = new Bundle();
        params.putString(Constants.ACTIVITY_PARAM_LATITUDE, "-83.12121212");
        params.putString(Constants.ACTIVITY_PARAM_LONGITUDE, "92.45454556");
        params.putFloat(Constants.ACTIVITY_PARAM_TEST, 85.40F);
        openNewActivityPassingData(MainActivity.class, params);
    }

}
