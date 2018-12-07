package com.example.tamara.comp3074_project;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.RatingBar;
import android.widget.TextView;

public class RestaurantViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_view);
        Bundle extras = getIntent().getExtras();
        Intent i = getIntent();

        if (extras != null) {
            TextView tvName = (TextView) findViewById(R.id.textViewName);
            TextView tvAddress = (TextView) findViewById(R.id.textViewAddress);
            TextView tvPhone = (TextView) findViewById(R.id.textViewPhone);
            TextView tvDescription = (TextView) findViewById(R.id.textViewDescription);
            TextView tvTags = (TextView) findViewById(R.id.textViewTags);
            TextView tvRating = (TextView) findViewById(R.id.textViewRatingValue);
            RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBarView);

            tvName.setText(i.getStringExtra("name"));
            tvAddress.setText(i.getStringExtra("address"));
            tvPhone.setText(i.getStringExtra("phone"));
            tvDescription.setText(i.getStringExtra("description"));
            tvTags.setText(i.getStringExtra("tags"));
            //tvRating.setText(i.getStringExtra("rating"));
            ratingBar.setRating(Float.parseFloat(i.getStringExtra("rating")));
        }
    }

}
