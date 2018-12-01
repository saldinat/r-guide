package com.example.tamara.comp3074_project;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class UpdateRestaurantActivity extends Activity {
    public static final String EXTRA_REPLY_NAME = "new_name";
    public static final String EXTRA_REPLY_ADDRESS = "new_address";
    public static final String EXTRA_REPLY_PHONE = "new_phone";
    public static final String EXTRA_REPLY_DESCRIPTION = "new_description";
    public static final String EXTRA_REPLY_TAGS = "new_tags";
    public static final String EXTRA_REPLY_DELETE = "delete";
    public static final String EXTRA_REPLY_UPDATE = "update";
    private RestaurantViewModel presenterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_restaurant);
        Bundle extras = getIntent().getExtras();
        Intent i = getIntent();

        final EditText name = findViewById(R.id.editTextName);
        final EditText address = findViewById(R.id.editTextAddress);
        final EditText phone = findViewById(R.id.editTextPhone);
        final EditText description = findViewById(R.id.editTextDescription);
        final EditText tags = findViewById(R.id.editTextTags);

        name.setText(i.getStringExtra("name"));
        address.setText(i.getStringExtra("address"));
        phone.setText(i.getStringExtra("phone"));
        description.setText(i.getStringExtra("description"));
        tags.setText(i.getStringExtra("tags"));

        Button b = findViewById(R.id.btnDeleteRestaurant);
        Button bSave = findViewById(R.id.btnSaveRestaurant);

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
                Intent i_start = new Intent(UpdateRestaurantActivity.this, MainActivity.class);
                //Intent intent_2 = new Intent();
                //startActivityForResult(i_start, 2);
                i_start.putExtra(EXTRA_REPLY_UPDATE, "do-update");
                //i_start.putExtra(EXTRA_REPLY_NAME, name.getText().toString());
                i_start.putExtra(EXTRA_REPLY_NAME, name.getText().toString());
                i_start.putExtra(EXTRA_REPLY_ADDRESS, address.getText().toString());
                i_start.putExtra(EXTRA_REPLY_PHONE, phone.getText().toString());
                i_start.putExtra(EXTRA_REPLY_DESCRIPTION, description.getText().toString());
                i_start.putExtra(EXTRA_REPLY_TAGS, tags.getText().toString());
                setResult(RESULT_OK, i_start);
                finish();
            }
        });
    }

}
