package com.store.minecryps.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.store.minecryps.R;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivity";
    private Context context=SettingsActivity.this;
    private Toolbar mtoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initToolbar();
        initListView();
    }

    private void initToolbar() {
        mtoolbar=findViewById(R.id.setting_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initListView() {

        ListView listView=findViewById(R.id.lv_account_setting);
        ArrayList<String> options= new ArrayList<>();
        options.add(getString(R.string.Edit_profile));
        options.add(getString(R.string.Sign_Out));

        ArrayAdapter arrayAdapter= new ArrayAdapter(context,android.R.layout.simple_list_item_1,options);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: navigating to gragment"+position);
                String txt= parent.getItemAtPosition(position).toString();
                //setViewPager(position);
                Toast.makeText(context, txt+" has been clicked", Toast.LENGTH_SHORT).show();

                if (position==0){
                    Intent intent= new Intent(SettingsActivity.this, SetupActivityActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
                else if (position==1){
                    Intent intent= new Intent(SettingsActivity.this, SignOutActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }

            }
        });

    }
}
