package com.example.sadistickamikaze.polling_app;

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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Button FilterButton = (Button)findViewById(R.id.FilterButton);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FilterButton.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_notifications:
                    FilterButton.setVisibility(View.GONE);
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
        test=0;
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
                                getPollResults((Map<String, Object>) dataSnapshot.getValue());
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
    private void getPollResults(Map<String, Object> polls) {
        ArrayList<Long> yesCount = new ArrayList<Long>();
        ArrayList<Long> noCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("yes"));
            noCount.add((Long) poll.get("no"));
            Log.d("Yes: ", yesCount.toString());
            Log.d("No: ", noCount.toString());
        }
    }

}
