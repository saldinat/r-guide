package com.example.tamara.comp3074_project;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Update;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_DELETE = "RESULT";

    private RestaurantViewModel presenterViewModel;
    private RestaurantListAdapter adapter;
    private  RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.btnAddNewRestaurant);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddRestaurantActivity.class);
                startActivityForResult(i, NEW_WORD);

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        final RestaurantListAdapter adapter = new RestaurantListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenterViewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        presenterViewModel.getAllRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(@Nullable List<Restaurant> restaurants) {
                adapter.setRestaurants(restaurants);
            }
        });
    }

    public final static int NEW_WORD = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_WORD && resultCode == RESULT_OK && data.getExtras() != null){
            presenterViewModel.insert(new Restaurant(
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_NAME),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_ADDRESS),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_PHONE),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_DESCRIPTION),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_TAGS)
                    ));
        }

        else if((resultCode == RESULT_OK) &&
                data.hasExtra("delete")){
            if(data.getStringExtra(UpdateRestaurantActivity.EXTRA_REPLY_DELETE).equals("do-delete")){
               presenterViewModel.deleteRestaurantWithName(data.getStringExtra(UpdateRestaurantActivity.EXTRA_REPLY_NAME));
                Toast.makeText(this, "Restaurant has been deleted!",
                        Toast.LENGTH_LONG).show();
        }}
        else if(resultCode == RESULT_OK &&
                    data.hasExtra("update")){
            Log.d("LOG-UPDD", "before here!!!");
                if(data.getStringExtra(UpdateRestaurantActivity.EXTRA_REPLY_UPDATE).equals("do-update")){
                    presenterViewModel.insert(new Restaurant(
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_NAME),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_ADDRESS),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_PHONE),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_DESCRIPTION),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_TAGS)
            ));
            //Toast.makeText(this, "Restaurant has been deleted!",
                    //Toast.LENGTH_LONG).show();
            Log.d("LOG-UPD", "here!!!");
        }
    }}

}
