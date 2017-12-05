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
    Long opt3;
    Long opt4;
    Long opt5;
    Long opt6;
    Long opt7;
    Long opt8;
    Long opt9;
    Long opt10;
    String[] info;
    String del;
    String opt1names;
    String opt2names;
    String opt3names;
    String opt4names;
    String opt5names;
    String opt6names;
    String opt7names;
    String opt8names;
    String opt9names;
    String opt10names;
    int optCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        optCount=2;
        info= new String[10];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpoll);
        TextView questionname = (TextView) findViewById(R.id.questiontitle);
        TextView option1buttonname = (TextView) findViewById(R.id.option1button);
        TextView option2buttonname = (TextView) findViewById(R.id.option2button);
        TextView option3buttonname = (TextView) findViewById(R.id.option3button);
        TextView option4buttonname = (TextView) findViewById(R.id.option4button);
        TextView option5buttonname = (TextView) findViewById(R.id.option5button);
        TextView option6buttonname = (TextView) findViewById(R.id.option6button);
        TextView option7buttonname = (TextView) findViewById(R.id.option7button);
        TextView option8buttonname = (TextView) findViewById(R.id.option8button);
        TextView option9buttonname = (TextView) findViewById(R.id.option9button);
        TextView option10buttonname = (TextView) findViewById(R.id.option10button);
        final EditText deletepassword = (EditText) findViewById(R.id.DeletePassword);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        for(int i=1;i<11;i++){
            info[i-1]=((String)b.get("opt"+i+"info")).substring(0,((String)b.get("opt"+i+"info")).indexOf(":"));
        }

        questionname.setText((String)b.get("name"));
        name=(String)b.get("name");
        option1buttonname.setText((String)b.get("opt1info"));
        opt1= Long.parseLong(((String)b.get("opt1info")).substring(((String)b.get("opt1info")).indexOf(":")+2)); //strips the option name and leaves numbers, then convert into Long
        option2buttonname.setText((String)b.get("opt2info"));
        opt2= Long.parseLong(((String)b.get("opt2info")).substring(((String)b.get("opt2info")).indexOf(":")+2));
        option3buttonname.setText((String)b.get("opt3info"));
        opt3= Long.parseLong(((String)b.get("opt3info")).substring(((String)b.get("opt3info")).indexOf(":")+2));
        if(option3buttonname.getText().equals(": 0") == false){
            option3buttonname.setVisibility(View.VISIBLE);
            optCount++;
        }
        option4buttonname.setText((String)b.get("opt4info"));
        opt4= Long.parseLong(((String)b.get("opt4info")).substring(((String)b.get("opt4info")).indexOf(":")+2));
        if(option4buttonname.getText().equals(": 0") == false){
            option4buttonname.setVisibility(View.VISIBLE);
            optCount++;
        }
        option5buttonname.setText((String)b.get("opt5info"));
        opt5= Long.parseLong(((String)b.get("opt5info")).substring(((String)b.get("opt5info")).indexOf(":")+2));
        if(option5buttonname.getText().equals(": 0") == false){
            option5buttonname.setVisibility(View.VISIBLE);
            optCount++;
        }
        option6buttonname.setText((String)b.get("opt6info"));
        opt6= Long.parseLong(((String)b.get("opt6info")).substring(((String)b.get("opt6info")).indexOf(":")+2));
        if(option6buttonname.getText().equals(": 0") == false){
            option6buttonname.setVisibility(View.VISIBLE);
            optCount++;
        }
        option7buttonname.setText((String)b.get("opt7info"));
        opt7= Long.parseLong(((String)b.get("opt7info")).substring(((String)b.get("opt7info")).indexOf(":")+2));
        if(option7buttonname.getText().equals(": 0") == false){
            option7buttonname.setVisibility(View.VISIBLE);
            optCount++;
        }
        option8buttonname.setText((String)b.get("opt8info"));
        opt8= Long.parseLong(((String)b.get("opt8info")).substring(((String)b.get("opt8info")).indexOf(":")+2));
        if(option8buttonname.getText().equals(": 0") == false){
            option8buttonname.setVisibility(View.VISIBLE);
            optCount++;
        }
        option9buttonname.setText((String)b.get("opt9info"));
        opt9= Long.parseLong(((String)b.get("opt9info")).substring(((String)b.get("opt9info")).indexOf(":")+2));
        if(option9buttonname.getText().equals(": 0") == false){
            option9buttonname.setVisibility(View.VISIBLE);
            optCount++;
        }
        option10buttonname.setText((String)b.get("opt10info"));
        opt10= Long.parseLong(((String)b.get("opt10info")).substring(((String)b.get("opt10info")).indexOf(":")+2));
        if(option10buttonname.getText().equals(": 0") == false){
            option10buttonname.setVisibility(View.VISIBLE);
            optCount++;
        }
        del = ((String)b.get("delPass")).replaceAll("[^0-9]","");



       /* opt1names = (String)b.get(""+n+"");
        opt2names = (String)b.get(""+o+"");
        if(opt1names!=null && opt1names.matches("")==false)
            yesVotes.setText("Yes: "+ opt1names);
        if(opt2names!=null && opt2names.matches("")==false)
            noVotes.setText("No: "+ opt2names);*/
        option1buttonname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText voterIDField = (EditText) findViewById(R.id.VoterID);
                String voterID=voterIDField.getText().toString();
                myref.child(name).child("opt1ans").setValue(opt1+Long.valueOf(1));  //increments poll value here
                if(voterID!=null && voterID.matches("")==false&&voterID.matches("Name")==false){
                    opt1names = opt1names + " "+voterID;
                    myref.child(name).child("opt1names").setValue(opt1names);
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
                    opt2names = opt2names + " "+voterID;
                    myref.child(name).child("opt2names").setValue(opt2names);
                }
                finish();
            }
        });
        option3buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText voterIDField = (EditText) findViewById(R.id.VoterID);
                String voterID=voterIDField.getText().toString();
                myref.child(name).child("opt3ans").setValue(opt3+Long.valueOf(1));
                if(voterID!=null && voterID.matches("")==false && voterID.matches("Name")==false){
                    opt3names = opt3names + " "+voterID;
                    myref.child(name).child("nonames").setValue(opt3names);
                }
                finish();
            }
        });
        option4buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText voterIDField = (EditText) findViewById(R.id.VoterID);
                String voterID=voterIDField.getText().toString();
                myref.child(name).child("opt4ans").setValue(opt4+Long.valueOf(1));
                if(voterID!=null && voterID.matches("")==false && voterID.matches("Name")==false){
                    opt4names = opt4names + " "+voterID;
                    myref.child(name).child("nonames").setValue(opt4names);
                }
                finish();
            }
        });
        option5buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText voterIDField = (EditText) findViewById(R.id.VoterID);
                String voterID=voterIDField.getText().toString();
                myref.child(name).child("opt5ans").setValue(opt5+Long.valueOf(1));
                if(voterID!=null && voterID.matches("")==false && voterID.matches("Name")==false){
                    opt5names = opt5names + " "+voterID;
                    myref.child(name).child("nonames").setValue(opt5names);
                }
                finish();
            }
        });
        option6buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText voterIDField = (EditText) findViewById(R.id.VoterID);
                String voterID=voterIDField.getText().toString();
                myref.child(name).child("opt6ans").setValue(opt6+Long.valueOf(1));
                if(voterID!=null && voterID.matches("")==false && voterID.matches("Name")==false){
                    opt6names = opt6names + " "+voterID;
                    myref.child(name).child("nonames").setValue(opt6names);
                }
                finish();
            }
        });
        option7buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText voterIDField = (EditText) findViewById(R.id.VoterID);
                String voterID=voterIDField.getText().toString();
                myref.child(name).child("opt7ans").setValue(opt7+Long.valueOf(1));
                if(voterID!=null && voterID.matches("")==false && voterID.matches("Name")==false){
                    opt7names = opt7names + " "+voterID;
                    myref.child(name).child("nonames").setValue(opt7names);
                }
                finish();
            }
        });
        option8buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText voterIDField = (EditText) findViewById(R.id.VoterID);
                String voterID=voterIDField.getText().toString();
                myref.child(name).child("opt8ans").setValue(opt8+Long.valueOf(1));
                if(voterID!=null && voterID.matches("")==false && voterID.matches("Name")==false){
                    opt8names = opt8names + " "+voterID;
                    myref.child(name).child("nonames").setValue(opt8names);
                }
                finish();
            }
        });
        option9buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText voterIDField = (EditText) findViewById(R.id.VoterID);
                String voterID=voterIDField.getText().toString();
                myref.child(name).child("opt9ans").setValue(opt9+Long.valueOf(1));
                if(voterID!=null && voterID.matches("")==false && voterID.matches("Name")==false){
                    opt9names = opt9names + " "+voterID;
                    myref.child(name).child("nonames").setValue(opt9names);
                }
                finish();
            }
        });
        option10buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText voterIDField = (EditText) findViewById(R.id.VoterID);
                String voterID=voterIDField.getText().toString();
                myref.child(name).child("opt10ans").setValue(opt10+Long.valueOf(1));
                if(voterID!=null && voterID.matches("")==false && voterID.matches("Name")==false){
                    opt10names = opt10names + " "+voterID;
                    myref.child(name).child("nonames").setValue(opt10names);
                }
                finish();
            }
        });
        Button ApplyButton = (Button)findViewById(R.id.donebutton);
        ApplyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String delpass=deletepassword.getText().toString();
                    if(delpass.equals(del)) {
                        myref.child(name).removeValue();
                        finish();
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
        intent.putExtra("c",opt3);
        intent.putExtra("d",opt4);
        intent.putExtra("e",opt5);
        intent.putExtra("f",opt6);
        intent.putExtra("g",opt7);
        intent.putExtra("h",opt8);
        intent.putExtra("i",opt9);
        intent.putExtra("j",opt10);
        intent.putExtra("question", name);
        intent.putExtra("names", info);
        intent.putExtra("count",optCount);
        startActivity(intent);
    }



}
