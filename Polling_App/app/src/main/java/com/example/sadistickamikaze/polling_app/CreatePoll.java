package com.example.sadistickamikaze.polling_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatePoll extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("Polls");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        Button ApplyButton = (Button)findViewById(R.id.CreatePollButton);
        ApplyButton.setOnClickListener(new View.OnClickListener() {

            EditText pollquestion = (EditText)findViewById(R.id.question);
            EditText option1answer = (EditText)findViewById(R.id.option1answer);
            EditText option2answer = (EditText)findViewById(R.id.option2answer);

            @Override
            public void onClick(View v) {

                String string = pollquestion.getText().toString();
                String string2 = option1answer.getText().toString();
                String string3 = option2answer.getText().toString();
                myref.child(string).child(string2).setValue("0");
                myref.child(string).child(string3).setValue("0");


                finish();
            }
        });
    }
}
