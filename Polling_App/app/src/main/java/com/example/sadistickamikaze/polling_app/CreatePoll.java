package com.example.sadistickamikaze.polling_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatePoll extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("Polls");
    String dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        Spinner numberofpolldropdown = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.numberofpolls));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberofpolldropdown.setAdapter(adapter);
        final EditText ddoption1 = (EditText)findViewById(R.id.ddoption1);
        final EditText ddoption2 = (EditText)findViewById(R.id.ddoption2);
        final EditText ddoption3 = (EditText)findViewById(R.id.ddoption3);
        final EditText ddoption4 = (EditText)findViewById(R.id.ddoption4);
        final EditText ddoption5 = (EditText)findViewById(R.id.ddoption5);
        final EditText ddoption6 = (EditText)findViewById(R.id.ddoption6);
        final EditText ddoption7 = (EditText)findViewById(R.id.ddoption7);
        final EditText ddoption8 = (EditText)findViewById(R.id.ddoption8);
        final EditText ddoption9 = (EditText)findViewById(R.id.ddoption9);
        final EditText ddoption10 = (EditText)findViewById(R.id.ddoption10);
        numberofpolldropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 dropdown = parent.getItemAtPosition(position).toString();
                 if(dropdown.equals("2")){
                     ddoption1.setVisibility(View.VISIBLE);
                     ddoption2.setVisibility(View.VISIBLE);
                     ddoption3.setVisibility(View.GONE);
                     ddoption4.setVisibility(View.GONE);
                     ddoption5.setVisibility(View.GONE);
                     ddoption6.setVisibility(View.GONE);
                     ddoption7.setVisibility(View.GONE);
                     ddoption8.setVisibility(View.GONE);
                     ddoption9.setVisibility(View.GONE);
                     ddoption10.setVisibility(View.GONE);
                 }
                if(dropdown.equals("3")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.GONE);
                    ddoption5.setVisibility(View.GONE);
                    ddoption6.setVisibility(View.GONE);
                    ddoption7.setVisibility(View.GONE);
                    ddoption8.setVisibility(View.GONE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("4")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.GONE);
                    ddoption6.setVisibility(View.GONE);
                    ddoption7.setVisibility(View.GONE);
                    ddoption8.setVisibility(View.GONE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("5")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.GONE);
                    ddoption7.setVisibility(View.GONE);
                    ddoption8.setVisibility(View.GONE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("6")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.VISIBLE);
                    ddoption7.setVisibility(View.GONE);
                    ddoption8.setVisibility(View.GONE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("7")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.VISIBLE);
                    ddoption7.setVisibility(View.VISIBLE);
                    ddoption8.setVisibility(View.GONE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("8")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.VISIBLE);
                    ddoption7.setVisibility(View.VISIBLE);
                    ddoption8.setVisibility(View.VISIBLE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("9")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.VISIBLE);
                    ddoption7.setVisibility(View.VISIBLE);
                    ddoption8.setVisibility(View.VISIBLE);
                    ddoption9.setVisibility(View.VISIBLE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("10")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.VISIBLE);
                    ddoption7.setVisibility(View.VISIBLE);
                    ddoption8.setVisibility(View.VISIBLE);
                    ddoption9.setVisibility(View.VISIBLE);
                    ddoption10.setVisibility(View.VISIBLE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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
                String stringoption1 = ddoption1.getText().toString();
                String stringoption2 = ddoption2.getText().toString();
                String stringoption3 = ddoption3.getText().toString();
                String stringoption4 = ddoption4.getText().toString();
                String stringoption5 = ddoption5.getText().toString();
                String stringoption6 = ddoption6.getText().toString();
                String stringoption7 = ddoption7.getText().toString();
                String stringoption8 = ddoption8.getText().toString();
                String stringoption9 = ddoption9.getText().toString();
                String stringoption10 = ddoption10.getText().toString();
                if(option1answer.getText().toString().matches("")==false){
                    password = Long.parseLong(option1answer.getText().toString());
                }
                if(option2answer.getText().toString().matches("")==false){
                    delete = Long.parseLong(option2answer.getText().toString());
                }
                myref.child(string).child("opt1").setValue(stringoption1);
                myref.child(string).child("opt2").setValue(stringoption2);
                myref.child(string).child("opt3").setValue(stringoption3);
                myref.child(string).child("opt4").setValue(stringoption4);
                myref.child(string).child("opt5").setValue(stringoption5);
                myref.child(string).child("opt6").setValue(stringoption6);
                myref.child(string).child("opt7").setValue(stringoption7);
                myref.child(string).child("opt8").setValue(stringoption8);
                myref.child(string).child("opt9").setValue(stringoption9);
                myref.child(string).child("opt10").setValue(stringoption10);
                for(int i =1;i<11;i++){
                    myref.child(string).child("opt"+i+"ans").setValue(0);
                }
                myref.child(string).child("password").setValue(0);
                if(password>0){
                    myref.child(string).child("password").setValue(password);
                }
                myref.child(string).child("delete").setValue(0);
                if(delete>0){
                    myref.child(string).child("delete").setValue(delete);
                }
                myref.child(string).child("opt1names").setValue("");
                myref.child(string).child("opt2names").setValue("");
                finish();
            }
        });
    }
}
