package com.proafsolutions.cubatrip.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.enums.CategoryEnum;
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

        ListView lv = (ListView)findViewById(R.id.listCategories);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                presenter.ClickProduct(position);

            }
        });

        presenter.RefreshList(CategoryEnum.RESTAURANTS);

    }

    public void onImageSelectClick(View view){
        presenter.ClickImage(view.getId());
    }

}
