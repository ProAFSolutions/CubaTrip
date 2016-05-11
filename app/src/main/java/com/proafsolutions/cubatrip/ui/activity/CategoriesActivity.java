package com.proafsolutions.cubatrip.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.proafsolutions.cubatrip.android.R;
import com.proafsolutions.cubatrip.domain.model.CategoryEnum;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent launchNewIntent = null;

        switch (item.getItemId())
        {
           case R.id.menuCategory:
                launchNewIntent = new Intent(CategoriesActivity.this,CategoriesActivity.class);
               startActivityForResult(launchNewIntent, 0);
                return true;

            case R.id.menuMap:
                 launchNewIntent = new Intent(CategoriesActivity.this,MapActivity.class);
                startActivityForResult(launchNewIntent, 0);
                return true;

            //default:
              //  Toast.makeText(CategoriesActivity.this, "Option is Selected", Toast.LENGTH_SHORT).show();
                //return super.onOptionsItemSelected(item);
        }

        return true;
    }

}
