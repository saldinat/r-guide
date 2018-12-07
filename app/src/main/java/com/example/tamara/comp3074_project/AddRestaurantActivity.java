package com.example.tamara.comp3074_project;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import static android.content.ContentValues.TAG;

public class AddRestaurantActivity extends Activity {
    public static final String EXTRA_REPLY_NAME = "new_name";
    public static final String EXTRA_REPLY_ADDRESS = "new_address";
    public static final String EXTRA_REPLY_PHONE = "new_phone";
    public static final String EXTRA_REPLY_DESCRIPTION = "new_description";
    public static final String EXTRA_REPLY_TAGS = "new_tags";
    public static final String EXTRA_REPLY_RATING = "new_rating";

    public static String autoCompleteAddress = "";
    private RatingBar ratingBar;
    private TextView txtRatingValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);


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
        final String rating;
        // String autoCompleteAddress;

        Button b = findViewById(R.id.btnSaveRestaurant);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // autoCompleteAddress = place.getAddress().toString();

                if (autoCompleteAddress.equals("")) {
                    Intent i = new Intent();
                    i.putExtra(EXTRA_REPLY_NAME, name.getText().toString());
                    i.putExtra(EXTRA_REPLY_ADDRESS, address.getText().toString());
                    // i.putExtra(EXTRA_REPLY_ADDRESS, autoCompleteAddress);
                    i.putExtra(EXTRA_REPLY_PHONE, phone.getText().toString());
                    i.putExtra(EXTRA_REPLY_DESCRIPTION, description.getText().toString());
                    i.putExtra(EXTRA_REPLY_TAGS, tags.getText().toString());
                    i.putExtra(EXTRA_REPLY_RATING, txtRatingValue.getText().toString());
                    setResult(RESULT_OK, i);
                    finish();

                } else {
                    Intent i = new Intent();
                    i.putExtra(EXTRA_REPLY_NAME, name.getText().toString());
                    // i.putExtra(EXTRA_REPLY_ADDRESS, address.getText().toString());
                    i.putExtra(EXTRA_REPLY_ADDRESS, autoCompleteAddress);
                    i.putExtra(EXTRA_REPLY_PHONE, phone.getText().toString());
                    i.putExtra(EXTRA_REPLY_DESCRIPTION, description.getText().toString());
                    i.putExtra(EXTRA_REPLY_TAGS, tags.getText().toString());
                    i.putExtra(EXTRA_REPLY_RATING, txtRatingValue.getText().toString());
                    setResult(RESULT_OK, i);
                    finish();
//
                }
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

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                //rating = String.valueOf(rating);
                txtRatingValue.setText(String.valueOf(rating));

            }
        });

    }

}
