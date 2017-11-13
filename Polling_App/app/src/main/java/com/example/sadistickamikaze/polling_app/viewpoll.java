package com.example.sadistickamikaze.polling_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class viewpoll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpoll);

        int j = 0;
        int k = 1;
        int l = 2;
        TextView questionname = (TextView) findViewById(R.id.questiontitle);
        TextView option1buttonname = (TextView) findViewById(R.id.option1button);
        TextView option2buttonname = (TextView) findViewById(R.id.option2button);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        questionname.setText((String)b.get(""+j+""));
        option1buttonname.setText((String)b.get(""+k+""));
        option2buttonname.setText((String)b.get(""+l+""));
    }
}
