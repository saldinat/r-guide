package com.example.tamara.comp3074_project;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ccom.example.tamara.comp3074_project.AboutActivity;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RestaurantViewModel presenterViewModel;
    final RestaurantListAdapter adapter = new RestaurantListAdapter(this);
    LiveData<List<Restaurant>> restaurants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.btnAddNewRestaurant);
        Button bAbout = findViewById(R.id.btnAboutGroup);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddRestaurantActivity.class);
                startActivityForResult(i, NEW_WORD);
            }
        });

        bAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenterViewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        restaurants = presenterViewModel.getAllRestaurants();
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
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_TAGS),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_RATING)
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
                if(data.getStringExtra(UpdateRestaurantActivity.EXTRA_REPLY_UPDATE).equals("do-update")){
                    presenterViewModel.insert(new Restaurant(
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_NAME),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_ADDRESS),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_PHONE),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_DESCRIPTION),
                    data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_TAGS),
                            data.getStringExtra(AddRestaurantActivity.EXTRA_REPLY_RATING)
                    ));
        }
    }}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        List<Restaurant> newList = new ArrayList<>();
        presenterViewModel.getAllRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(@Nullable List<Restaurant> restaurants) {
                adapter.setRestaurants(restaurants);
            }
        });
        for(Restaurant res: adapter.getRestaurants()) {
            if (res.getRestaurant().contains(userInput) || res.getTags().contains(userInput)) {
                newList.add(res);
            }
        }
        adapter.updateList(newList);
        return true;
    }
}
