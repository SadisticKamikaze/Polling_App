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
                Long password = Long.valueOf(0);
                Long delete = Long.valueOf(0);
                String string = pollquestion.getText().toString();
                if(option1answer.getText().toString().matches("")==false){
                    password = Long.parseLong(option1answer.getText().toString());
                }
                if(option2answer.getText().toString().matches("")==false){
                    delete = Long.parseLong(option1answer.getText().toString());
                }
                myref.child(string).child("yes").setValue(0);
                myref.child(string).child("no").setValue(0);
                myref.child(string).child("password").setValue(0);
                if(password>0){
                    myref.child(string).child("password").setValue(password);
                }
                myref.child(string).child("delete").setValue(0);
                if(delete>0){
                    myref.child(string).child("delete").setValue(delete);
                }
                finish();
            }
        });
    }
}
