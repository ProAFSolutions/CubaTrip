package com.proafsolutions.cubatrip.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.Product;

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
        textView.setText(productList.get(position).getName());
        textDescription.setText(productList.get(position).getDescription());
        //String s = valuesTitle[position];
        imageView.setImageResource(R.mipmap.no_image);

        return rowView;
    }

}

