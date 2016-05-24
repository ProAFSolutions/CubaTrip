package com.proafsolutions.cubatrip.ui.presenter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.domain.model.enums.CategoryEnum;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.enums.ProvinceEnum;
import com.proafsolutions.cubatrip.domain.service.BLServiceCatalog;
import com.proafsolutions.cubatrip.ui.activity.CategoriesActivity;
import com.proafsolutions.cubatrip.ui.activity.DetailsActivity;
import com.proafsolutions.cubatrip.ui.activity.MapActivity;
import com.proafsolutions.cubatrip.ui.adapter.ListAdapter;
import com.proafsolutions.cubatrip.ui.util.Utils;


import java.util.ArrayList;
import java.util.List;


public class CategoriesPresenter extends AbstractPresenter {

    private CategoriesActivity activity;

    public CategoriesPresenter(CategoriesActivity activity) {
        this.activity = activity;
    }

    List<Product> products;


    @Override
    protected Activity getCurrentActivity() {
        return activity;
    }

    public void ClickImage(int id){
        RefreshList(Utils.GetCategoryFromResourceId(id));
        LinearLayout menu = (LinearLayout)activity.findViewById(R.id.LayoutMenu);
        boolean NextSelect = false;

        //Set Color for TextView
        ArrayList<View> allViewsWithinMyTopView = getAllChildren(menu);
        for (View child : allViewsWithinMyTopView) {
            if(child.getId()==id)
                NextSelect = true;

            if (child instanceof TextView) {
                TextView childTextView = (TextView) child;
                if(!NextSelect)
                    childTextView.setTextColor(Color.parseColor("#FFFFFF"));
                else {
                    childTextView.setTextColor(Color.parseColor("#1E88E5"));
                    NextSelect = false;
                }

            }
        }

    }

    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

    public void RefreshList(CategoryEnum category)
    {
        products = BLServiceCatalog.getInstance().getProductsByCategory(category, ProvinceEnum.LA_HABANA);

        String[] stringsNames = new String[products.size()];

        ListAdapter adapter = new ListAdapter(activity,stringsNames, products);

        ListView lv = (ListView)activity.findViewById(R.id.listCategories);
        lv.setAdapter(adapter);
    }

    public void ClickProduct(int position){
        Bundle params = new Bundle();
        params.putLong("idProduct",products.get(position).getId());
        this.openNewActivityPassingData(DetailsActivity.class,params);
    }



}
