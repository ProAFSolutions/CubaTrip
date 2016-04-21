package com.proafsolutions.cubatrip.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.ui.adapter.ListAdapter;
import com.proafsolutions.cubatrip.ui.presenter.CategoriesPresenter;


public class CategoriesActivity extends AppCompatActivity {

    private CategoriesPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        presenter = new CategoriesPresenter(CategoriesActivity.this);

        // Create a progress bar to display while the list loads
      /*  ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);*/

        presenter.RefreshList(0);

    }

    public void onImageSelectClick(View view){
        presenter.ClickImage(view.getId());
    }

}
