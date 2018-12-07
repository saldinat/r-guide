package com.example.tamara.comp3074_project;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.List;

import static android.content.ContentValues.TAG;


public class UpdateRestaurantActivity extends Activity {
    public static final String EXTRA_REPLY_NAME = "new_name";
    public static final String EXTRA_REPLY_ADDRESS = "new_address";
    public static final String EXTRA_REPLY_PHONE = "new_phone";
    public static final String EXTRA_REPLY_DESCRIPTION = "new_description";
    public static final String EXTRA_REPLY_TAGS = "new_tags";
    public static final String EXTRA_REPLY_RATING = "new_rating";

    public static final String EXTRA_REPLY_DELETE = "delete";
    public static final String EXTRA_REPLY_UPDATE = "update";
    private RestaurantViewModel presenterViewModel;
    private LocationManager locationManager;
    private static final int LOCATION_CODE = 1;
    public static String autoCompleteAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_restaurant);
        Bundle extras = getIntent().getExtras();
        Intent i = getIntent();

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        /*
         * The following code example shows setting an AutocompleteFilter on a PlaceAutocompleteFragment to
         * set a filter returning only results with a precise address.
         */
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);
        final EditText name = findViewById(R.id.editTextName);
        final EditText address = findViewById(R.id.editTextAddress);
        final EditText phone = findViewById(R.id.editTextPhone);
        final EditText description = findViewById(R.id.editTextDescription);
        final EditText tags = findViewById(R.id.editTextTags);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        final TextView txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });

        name.setText(i.getStringExtra("name"));
        address.setText(i.getStringExtra("address"));
        phone.setText(i.getStringExtra("phone"));
        description.setText(i.getStringExtra("description"));
        tags.setText(i.getStringExtra("tags"));
        ratingBar.setRating(Float.parseFloat(i.getStringExtra("rating")));


        Button b = findViewById(R.id.btnDeleteRestaurant);
        Button bSave = findViewById(R.id.btnSaveRestaurant);
        Button bLocation = findViewById(R.id.btnLocationRestaurant);
        Button bDirection = findViewById(R.id.btnDirectionRestaurant);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_CODE);
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final Intent intent_2 = new Intent();
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateRestaurantActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent i_start = new Intent(UpdateRestaurantActivity.this, MainActivity.class);
                        Intent intent_2 = new Intent();
                        //startActivityForResult(i_start, 2);
                        i_start.putExtra(EXTRA_REPLY_DELETE, "do-delete");
                        i_start.putExtra(EXTRA_REPLY_NAME, name.getText().toString());

                        setResult(RESULT_OK, i_start);
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();

            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (autoCompleteAddress.equals("")) {

                    /* Original code block found here:
                    Intent i_start = new Intent(UpdateRestaurantActivity.this, MainActivity.class);
                    //Intent intent_2 = new Intent();
                    //startActivityForResult(i_start, 2);
                    i_start.putExtra(EXTRA_REPLY_UPDATE, "do-update");
                    //i_start.putExtra(EXTRA_REPLY_NAME, name.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_NAME, name.getText().toString());
                    //i_start.putExtra(EXTRA_REPLY_ADDRESS, address.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_PHONE, phone.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_DESCRIPTION, description.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_TAGS, tags.getText().toString());

                    setResult(RESULT_OK, i_start);
                    finish();
                    */ // End of Original code block:

                    Intent i_start = new Intent(UpdateRestaurantActivity.this, MainActivity.class);
                    i_start.putExtra(EXTRA_REPLY_UPDATE, "do-update");
                    i_start.putExtra(EXTRA_REPLY_NAME, name.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_ADDRESS, address.getText().toString());
                    // i_start.putExtra(EXTRA_REPLY_ADDRESS, autoCompleteAddress);
                    i_start.putExtra(EXTRA_REPLY_PHONE, phone.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_DESCRIPTION, description.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_TAGS, tags.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_RATING, tags.getText().toString());

                    setResult(RESULT_OK, i_start);
                    finish();

                } else {

                    Intent i_start = new Intent(UpdateRestaurantActivity.this, MainActivity.class);
                    i_start.putExtra(EXTRA_REPLY_UPDATE, "do-update");
                    i_start.putExtra(EXTRA_REPLY_NAME, name.getText().toString());
                    // i_start.putExtra(EXTRA_REPLY_ADDRESS, address.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_ADDRESS, autoCompleteAddress);
                    i_start.putExtra(EXTRA_REPLY_PHONE, phone.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_DESCRIPTION, description.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_TAGS, tags.getText().toString());
                    i_start.putExtra(EXTRA_REPLY_RATING, txtRatingValue.getText().toString());

                    setResult(RESULT_OK, i_start);
                    finish();

                }
            }
        });

        bLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String destination = address.getText().toString();
                if (destination.equals("")) {
                    destination = "160+Kendal+Ave+Toronto+Ontario+Canada";
                    Toast.makeText(getApplicationContext(), "No Address Was Supplied.", Toast.LENGTH_LONG).show();
                }

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + destination));
                startActivity(i);
            }
        });

        bDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String destination = address.getText().toString();
                if (destination.equals("")) {
                    destination = "160+Kendal+Ave+Toronto+Ontario+Canada";
                    Toast.makeText(getApplicationContext(), "No Address Was Supplied.", Toast.LENGTH_LONG).show();
                }

                String lastKnownLocation;

                List<String> providers = locationManager.getProviders(true);

                if (providers.get(0).equals("") || providers.get(0).equals(null)) {
                    lastKnownLocation = "160+Kendal+Ave+Toronto+Ontario+Canada";
                } else {
                    lastKnownLocation = providers.get(0);
                }

                Uri navQuery = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=" + lastKnownLocation + "&destination=" + destination);

                Intent i = new Intent(Intent.ACTION_VIEW, navQuery);
                startActivity(i);

            }
        });

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                autoCompleteAddress = place.getAddress().toString();
                Log.i(TAG, "Place: " + place.getName()); //get place details here
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });


    }

}
