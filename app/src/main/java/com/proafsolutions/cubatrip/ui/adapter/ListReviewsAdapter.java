package com.proafsolutions.cubatrip.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.util.Utils;

import java.util.List;

/**
 * Created by Fily on 4/20/2016.
 */

public class ListReviewsAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<Review> productList;


    public ListReviewsAdapter(Context context, String[] valuesTitles, List<Review> products) {
        super(context, -1,valuesTitles);
        this.context = context;
        productList = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_reviews, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
        TextView textValue = (TextView) rowView.findViewById(R.id.ratingValue);
        TextView ratingText = (TextView) rowView.findViewById(R.id.ratingText);
        TextView textDescription = (TextView) rowView.findViewById(R.id.secondLine);
       // ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(productList.get(position).getContact());
        textDescription.setText(productList.get(position).getComments());
        textValue.setText("("+ productList.get(position).getRate().getRate()+")");
        ratingText.setText(productList.get(position).getRate().toString());
        //String s = valuesTitle[position];
      //  imageView.setImageResource(R.mipmap.no_image);

        return rowView;
    }

}

