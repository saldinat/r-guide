package ccom.example.tamara.comp3074_project;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.example.tamara.comp3074_project.R;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button back = findViewById(R.id.btnAboutBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
