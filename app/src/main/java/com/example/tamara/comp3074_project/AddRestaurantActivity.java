package com.example.tamara.comp3074_project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static android.content.ContentValues.TAG;

public class AddRestaurantActivity extends Activity {
    public static final String EXTRA_REPLY_NAME = "new_name";
    public static final String EXTRA_REPLY_ADDRESS = "new_address";
    public static final String EXTRA_REPLY_PHONE = "new_phone";
    public static final String EXTRA_REPLY_DESCRIPTION = "new_description";
    public static final String EXTRA_REPLY_TAGS = "new_tags";
    public static String autoCompleteAddress = "";
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        /*
        final PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
                */

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

        /*
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
        */

        final EditText name = findViewById(R.id.editTextName);
        final EditText address = findViewById(R.id.editTextAddress);
        final EditText phone = findViewById(R.id.editTextPhone);
        final EditText description = findViewById(R.id.editTextDescription);
        final EditText tags = findViewById(R.id.editTextTags);

        // String autoCompleteAddress;

        /*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<PlaceLikelihoodBufferResponse> placeResult = mPlaceDetectionClient.getCurrentPlace(null);
        placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                            placeLikelihood.getPlace().getName(),
                            placeLikelihood.getLikelihood()));
                }
                likelyPlaces.release();
            }
        });
        */

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
                    setResult(RESULT_OK, i);
                    finish();

                }
            }
        });

        /*
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                autoCompleteAddress = place.getAddress().toString();
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
        */

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
