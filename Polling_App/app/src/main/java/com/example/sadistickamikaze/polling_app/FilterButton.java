package com.example.sadistickamikaze.polling_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.sadistickamikaze.polling_app.MainActivity.password;

public class FilterButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_button);

        Button ApplyButton = (Button)findViewById(R.id.ApplyButton);
        ApplyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText password2 = (EditText)findViewById(R.id.passwordcontext);
                String string = password2.getText().toString();
                if(string != null) {
                    password = Long.valueOf(string);
                }

                finish();
            }
        });
    }
}
