package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.proafsolutions.cubatrip.domain.model.Category;
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
                System.out.println(c.getName());
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage() + " " + ex.getStackTrace().toString());
        }
    }

    public void openMap(){
        Bundle params = new Bundle();
        params.putString("param1", "Alex");
        params.putString("param1", "Jonito");
        openNewActivityPassingData(MainActivity.class, params);
    }



}
