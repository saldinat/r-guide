package com.example.tamara.comp3074_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantViewActivity extends Activity {

    String rName, rAddress, rPhone, rDescription, rTags = "";

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

            tvName.setText(i.getStringExtra("name"));
            tvAddress.setText(i.getStringExtra("address"));
            tvPhone.setText(i.getStringExtra("phone"));
            tvDescription.setText(i.getStringExtra("description"));
            tvTags.setText(i.getStringExtra("tags"));

            rName = tvName.getText().toString();
            rAddress = tvAddress.getText().toString();
            rPhone = tvPhone.getText().toString();
            rDescription = tvDescription.getText().toString();
            rTags = tvTags.getText().toString();
        }

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

    }

}
