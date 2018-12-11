package com.example.tamara.comp3074_project;

import android.Manifest;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.widget.Button;

import android.widget.RatingBar;

import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RestaurantViewActivity extends Activity {

    String rName, rAddress, rPhone, rDescription, rTags = "";

    private static final int LOCATION_CODE = 1;
    private LocationManager locationManager;

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
//HEAD
            //tvRating.setText(i.getStringExtra("rating"));
//=======


            rName = tvName.getText().toString();
            rAddress = tvAddress.getText().toString();
            rPhone = tvPhone.getText().toString();
            rDescription = tvDescription.getText().toString();
            rTags = tvTags.getText().toString();

            tvRating.setText(i.getStringExtra("rating"));
//>>>>>>> 1e002570d36f02ed485ff209f77cb505d0a8ca97
            ratingBar.setRating(Float.parseFloat(i.getStringExtra("rating")));

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_CODE);
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Button btnLocation = findViewById(R.id.btnRVALocation);

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String destination = address.getText().toString();
                String destination = rAddress;
                if (destination.equals("")) {
                    destination = "160+Kendal+Ave+Toronto+Ontario+Canada";
                    Toast.makeText(getApplicationContext(), "No Address Was Supplied.", Toast.LENGTH_LONG).show();
                }

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + destination));
                startActivity(i);
            }
        });

        Button btnDirection = findViewById(R.id.btnRVADirections);

        btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String destination = address.getText().toString();
                String destination = rAddress;
                if (destination.equals("")) {
                    destination = "160+Kendal+Ave+Toronto+Ontario+Canada";
                    Toast.makeText(getApplicationContext(), "No Address Was Supplied.", Toast.LENGTH_LONG).show();
                }

                String lastKnownLocation;

                List<String> providers = locationManager.getProviders(true);

                if (providers.get(0).equals("") || providers.get(0).equals(null) || providers.get(0).equals("passive")) {
                    lastKnownLocation = "160+Kendal+Ave+Toronto+Ontario+Canada";
                } else {
                    lastKnownLocation = providers.get(0);
                }

                Uri navQuery = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=" + lastKnownLocation + "&destination=" + destination);

                Intent i = new Intent(Intent.ACTION_VIEW, navQuery);
                startActivity(i);
            }
        });

        Button btnShareEmail = findViewById(R.id.btnShareEmail);

        btnShareEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String eBody = "\n" + "\n" + "\n" + rName + ":" + "\n" + rAddress + "\n" + "Phone: " + rPhone+ "\n" +
                        rDescription + "\n" + "Tags:" + rTags;

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","abc@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Great new restaurant");
                emailIntent.putExtra(Intent.EXTRA_TEXT, eBody);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));

            }
        });

        Button btnShareTwitter = findViewById(R.id.btnShareTwitter);

        btnShareTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweetMsg = "I just ate at a great place! Check out " + rName + " at " + rAddress + ".";
                
                Intent tweet = new Intent(Intent.ACTION_VIEW);
                tweet.setData(Uri.parse("http://twitter.com/?status=" + Uri.encode(tweetMsg)));
                startActivity(tweet);

                /*
                String tweetUrl = "https://twitter.com/intent/tweet?text=" + tweetMsg;
                Uri uri = Uri.parse(tweetUrl);
                Intent tweet = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(tweet);
                   */

                /*
                String tweetUrl = "https://twitter.com/intent/tweet?text=" + tweetMsg + "&url="
                        + "https://www.google.com";
                Uri uri = Uri.parse(tweetUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                */
            }
        });

        Button btnShareFacebook = findViewById(R.id.btnShareFacebook);

        btnShareFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Future Integration.", Toast.LENGTH_LONG).show();
            }
        });

    }

}
