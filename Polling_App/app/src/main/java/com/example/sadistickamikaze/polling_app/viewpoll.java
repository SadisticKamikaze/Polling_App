package com.example.sadistickamikaze.polling_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class viewpoll extends AppCompatActivity {
    final DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("Polls");
    String name;
    Long opt1;
    Long opt2;
    Long del;
    String yesnames;
    String nonames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpoll);
        int j = 0;
        int k = 1;
        int l = 2;
        int m = 3;
        int n = 4;
        int o = 5;
        TextView yesVotes = (TextView) findViewById(R.id.YesVotes);
        TextView noVotes = (TextView) findViewById(R.id.NoVotes);
        TextView questionname = (TextView) findViewById(R.id.questiontitle);
        TextView option1buttonname = (TextView) findViewById(R.id.option1button);
        TextView option2buttonname = (TextView) findViewById(R.id.option2button);
        final EditText deletepassword = (EditText) findViewById(R.id.DeletePassword);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        questionname.setText((String)b.get(""+j+""));
        name=(String)b.get(""+j+"");
        option1buttonname.setText((String)b.get(""+k+""));
        opt1= Long.parseLong(((String)b.get(""+k+"")).substring(((String)b.get(""+k+"")).indexOf(":")+2)); //strips the option name and leaves numbers, then convert into Long
        option2buttonname.setText((String)b.get(""+l+""));
        opt2= Long.parseLong(((String)b.get(""+l+"")).substring(((String)b.get(""+l+"")).indexOf(":")+2));
        del = Long.parseLong(((String)b.get(""+m+"")).replaceAll("[^0-9]",""));
       /* yesnames = (String)b.get(""+n+"");
        nonames = (String)b.get(""+o+"");
        if(yesnames!=null && yesnames.matches("")==false)
            yesVotes.setText("Yes: "+ yesnames);
        if(nonames!=null && nonames.matches("")==false)
            noVotes.setText("No: "+ nonames);*/
        option1buttonname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText voterIDField = (EditText) findViewById(R.id.VoterID);
                String voterID=voterIDField.getText().toString();
                myref.child(name).child("opt1ans").setValue(opt1+Long.valueOf(1));  //increments poll value here
                if(voterID!=null && voterID.matches("")==false&&voterID.matches("Name")==false){
                    yesnames = yesnames + " "+voterID;
                    myref.child(name).child("yesnames").setValue(yesnames);
                }
                finish();
            }
        });
        option2buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText voterIDField = (EditText) findViewById(R.id.VoterID);
                String voterID=voterIDField.getText().toString();
                myref.child(name).child("opt2ans").setValue(opt2+Long.valueOf(1));
                if(voterID!=null && voterID.matches("")==false && voterID.matches("Name")==false){
                    nonames = nonames + " "+voterID;
                    myref.child(name).child("nonames").setValue(nonames);
                }
                finish();
            }
        });
        Button ApplyButton = (Button)findViewById(R.id.donebutton);
        ApplyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String delpass=deletepassword.getText().toString();
                if(delpass.matches("")==false){
                    if(Long.parseLong(delpass)==del) {
                        myref.child(name).removeValue();
                        finish();
                    }
                }
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
