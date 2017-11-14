package com.example.sadistickamikaze.polling_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class viewpoll extends AppCompatActivity {
    final DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("Polls");
    String name;
    Long opt1;
    Long opt2;
    Long del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpoll);

        int j = 0;
        int k = 1;
        int l = 2;
        int m = 3;
        TextView questionname = (TextView) findViewById(R.id.questiontitle);
        TextView option1buttonname = (TextView) findViewById(R.id.option1button);
        TextView option2buttonname = (TextView) findViewById(R.id.option2button);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        questionname.setText((String)b.get(""+j+""));
        name=(String)b.get(""+j+"");
        option1buttonname.setText((String)b.get(""+k+""));
        opt1= Long.parseLong(((String)b.get(""+k+"")).replaceAll("[^0-9]","")); //strips the option name and leaves numbers, then convert into Long
        option2buttonname.setText((String)b.get(""+l+""));
        opt2= Long.parseLong(((String)b.get(""+l+"")).replaceAll("[^0-9]",""));
        del = Long.parseLong(((String)b.get(""+m+"")).replaceAll("[^0-9]",""));
        option1buttonname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                myref.child(name).child("yes").setValue(opt1+Long.valueOf(1));  //increments poll value here
                finish();
            }
        });
        option2buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myref.child(name).child("no").setValue(opt2+Long.valueOf(1));
                finish();
            }
        });
        Button ApplyButton = (Button)findViewById(R.id.donebutton);
        ApplyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void displayPoll(View view){
        PiePoll piePoll = new PiePoll();

        Log.d("opt 1", opt1.toString());
        Log.d("opt 1", opt2.toString());
        Intent intent = new Intent(getApplicationContext(), PiePoll.class);
        intent.putExtra("a",opt1);
        intent.putExtra("b",opt2);
       // piePoll.addVotes(opt1);
       //  piePoll.addVotes(opt2);
        startActivity(intent);
    }


}
