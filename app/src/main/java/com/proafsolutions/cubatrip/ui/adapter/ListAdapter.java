package com.proafsolutions.cubatrip.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.domain.service.BLServiceCatalog;
import com.proafsolutions.cubatrip.infrastructure.io.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fily on 4/20/2016.
 */

public class ListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<Product> productList;


    public ListAdapter(Context context, String[] valuesTitles,List<Product> products) {
        super(context, -1,valuesTitles);
        this.context = context;
        productList = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_categories, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
        TextView textDescription = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        RatingBar ratingBar = (RatingBar) rowView.findViewById(R.id.ratingBar);
        ratingBar.setRating(RefreshRating(productList.get(position)));
        textView.setText(productList.get(position).getName());
        textDescription.setText(productList.get(position).getDescription());
        //String s = valuesTitle[position];

        boolean hasImage = false;
        if(productList.get(position).getImages().size()>0)
        {
            File imgFile = new File(FileManager.getResourcesFolder()+"/"+productList.get(position).getProvince().toString()+"/"+productList.get(position).getId()+"/"+productList.get(position).getImages().get(0).toString());

            if(imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
               // imageView.getLayoutParams().height=250;
               // imageView.getLayoutParams().width=250;
                hasImage = true;
            }
        }

        if(!hasImage) {
            imageView.setImageResource(R.mipmap.no_image);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return rowView;
    }

    public float RefreshRating(Product product)
    {
        List<Review> reviews =   BLServiceCatalog.getInstance().getReviews(product.getId());
        if(reviews.size()==0) {
          return 0;
        }
        else {
            int totalReview = 0;
            for (Review rev:reviews) {
                totalReview+=rev.getRate().ordinal();
            }
              return (totalReview/reviews.size());
        }

    }



}

