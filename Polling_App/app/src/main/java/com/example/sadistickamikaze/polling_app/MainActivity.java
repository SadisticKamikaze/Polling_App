package com.example.sadistickamikaze.polling_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    static Long password;
    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Button FilterButton = (Button)findViewById(R.id.FilterButton);
            Button NewPollButton = (Button)findViewById(R.id.NewPollButton);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FilterButton.setVisibility(View.VISIBLE);
                    NewPollButton.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_notifications:
                    FilterButton.setVisibility(View.GONE);
                    NewPollButton.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }

    };
    int test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password=Long.valueOf(0);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button FilterButton = (Button)findViewById(R.id.FilterButton);
        Button NewPollButton = (Button)findViewById(R.id.NewPollButton);

        FilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference pollReference= FirebaseDatabase.getInstance().getReference().child("Polls");
                pollReference.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }
                );
                Intent startIntent = new Intent(getApplicationContext(), FilterButton.class);
                startActivity(startIntent);
            }
        });
        NewPollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), CreatePoll.class);
                startActivity(startIntent);
            }
        });

    }
    private Long[] getPasswords(Map<String, Object> polls){ //puts passwords into a long array by iterating though all polls
        ArrayList<Long> passwords= new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            passwords.add((Long) poll.get("password"));
        }
        return (Long[])  passwords.toArray(new Long[0]);
    }
    private Long[] getDeletePasswords(Map<String, Object> polls){ //puts delete passwords into a long array by iterating though all polls
        ArrayList<Long> passwords= new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            passwords.add((Long) poll.get("delete"));
        }
        return (Long[])  passwords.toArray(new Long[0]);
    }
    private Long[] getNoPollCount(Map<String, Object> polls){ //puts nos into a long array by iterating though all polls
        ArrayList<Long> noCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noCount.add((Long) poll.get("no"));
        }
        return (Long[]) noCount.toArray(new Long[0]);
    }
    private Long[] getYesPollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("yes"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }

    private String[] getPollNames(Map<String, Object> polls){ //puts names into a long array by iterating though all polls
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add(entry.getKey());
        }
        return (String[])pollList.toArray(new String[0]);
    }

    public void newPoll(View v){
        Intent info = new Intent(this, CreatePoll.class);
        startActivity(info);
    }

    @Override
    public void onResume(){
        super.onResume();
        final DatabaseReference pollReference= FirebaseDatabase.getInstance().getReference().child("Polls");
        pollReference.addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) { //every time page is resumed it refreshes this
                        String[] names = getPollNames((Map<String, Object>) dataSnapshot.getValue()); //runs functions to put passwords, name, and votes into arrays
                        Long[] yes = getYesPollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] no = getNoPollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] passwords = getPasswords((Map<String, Object>) dataSnapshot.getValue());
                        Long[] delete = getDeletePasswords((Map<String,Object>) dataSnapshot.getValue());
                        LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
                        Context context = getApplicationContext();
                        for(int i=0;i<names.length;i++){
                            if(passwords[i]==password) { //if passwords match, make a button with the name of the poll
                                final Button pollButton = new Button(context);
                                pollButton.setId(i);
                                pollButton.setText(names[i]);
                                layout.addView(pollButton);
                                final long p = yes[i];
                                final long u = no[i];
                                final long del = delete[i];
                                pollButton.setOnClickListener(new View.OnClickListener() {
                                    int j=0;
                                    int k=1;
                                    int l=2;
                                    int m=3;
                                    @Override
                                    public void onClick(View v) {

                                        Intent startIntent = new Intent(getApplicationContext(), viewpoll.class);
                                        startIntent.putExtra(""+j+"", (String)pollButton.getText());
                                        startIntent.putExtra(""+k+"", "Yes: "+Integer.toString((int)p));
                                        startIntent.putExtra(""+l+"", "No: "+Integer.toString((int)u));
                                        startIntent.putExtra(""+m+"", Integer.toString((int)del));
                                        startActivity(startIntent);
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

    }

    @Override
    public void onPause(){ //stops duplicates being created when page refreshes
        super.onPause();
        LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
        layout.removeAllViewsInLayout();
    }



}
