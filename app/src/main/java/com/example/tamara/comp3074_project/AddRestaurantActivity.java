package com.example.tamara.comp3074_project;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRestaurantActivity extends Activity {
    public static final String EXTRA_REPLY_NAME = "new_name";
    public static final String EXTRA_REPLY_ADDRESS = "new_address";
    public static final String EXTRA_REPLY_PHONE = "new_phone";
    public static final String EXTRA_REPLY_DESCRIPTION = "new_description";
    public static final String EXTRA_REPLY_TAGS = "new_tags";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        final EditText name = findViewById(R.id.editTextName);
        final EditText address = findViewById(R.id.editTextAddress);
        final EditText phone = findViewById(R.id.editTextPhone);
        final EditText description = findViewById(R.id.editTextDescription);
        final EditText tags = findViewById(R.id.editTextTags);


        Button b = findViewById(R.id.btnSaveRestaurant);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra(EXTRA_REPLY_NAME, name.getText().toString());
                i.putExtra(EXTRA_REPLY_ADDRESS, address.getText().toString());
                i.putExtra(EXTRA_REPLY_PHONE, phone.getText().toString());
                i.putExtra(EXTRA_REPLY_DESCRIPTION, description.getText().toString());
                i.putExtra(EXTRA_REPLY_TAGS, tags.getText().toString());
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

}
