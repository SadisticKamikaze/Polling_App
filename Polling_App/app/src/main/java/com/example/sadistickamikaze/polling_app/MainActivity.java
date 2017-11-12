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
    private int i;
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
        String[] pollList;
        Long[] yesList;
        Long[] noList;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Key:0000", "Failed to read value.", error.toException());
            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button FilterButton = (Button)findViewById(R.id.FilterButton);

        FilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference pollReference= FirebaseDatabase.getInstance().getReference().child("Polls");
                pollReference.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String[] names = getPollNames((Map<String, Object>) dataSnapshot.getValue());
                                Long[] yes = getYesPollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] no = getNoPollCount((Map<String, Object>) dataSnapshot.getValue());
                                String name;
                                Long yesCount;
                                Long noCount;
                                for(int i=0; i<names.length; i++){
                                    name = names[i];
                                    yesCount=yes[i];
                                    noCount=no[i];
                                    Log.d("Poll Info", name+": Yes="+yesCount
                                    +", No="+noCount);
                                }
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

    }
    private Long[] getNoPollCount(Map<String, Object> polls){
        ArrayList<Long> noCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noCount.add((Long) poll.get("no"));
        }
        return (Long[]) noCount.toArray(new Long[0]);
    }
    private Long[] getYesPollCount(Map<String, Object> polls){
        ArrayList<Long> yesCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("yes"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }

    private String[] getPollNames(Map<String, Object> polls){
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
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String[] names = getPollNames((Map<String, Object>) dataSnapshot.getValue());
                        Long[] yes = getYesPollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] no = getNoPollCount((Map<String, Object>) dataSnapshot.getValue());
                        LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
                        Context context = getApplicationContext();
                        for(int i=0;i<names.length;i++){
                            Button pollButton = new Button(context);
                            pollButton.setId(i);
                            pollButton.setText(names[i]);
                            layout.addView(pollButton);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

    }

    @Override
    public void onPause(){
        super.onPause();
        LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
        layout.removeAllViewsInLayout();
    }

    // Creates new button
    // LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
    // i++;
    // Button pollButton = new Button(this);
    // pollButton.setId(i);
    // pollButton.setText("Hello World");
    //  layout.addView(pollButton);

}
