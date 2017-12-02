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
    static String password;
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
        password="";
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button FilterButton = (Button)findViewById(R.id.FilterButton);
        Button RefreshButton = (Button)findViewById(R.id.refreshButton);

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
        RefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
                layout.removeAllViewsInLayout();
                DatabaseReference pollReference= FirebaseDatabase.getInstance().getReference().child("Polls");
                pollReference.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) { //every time page is resumed it refreshes this
                                String[] names = getPollNames((Map<String, Object>) dataSnapshot.getValue()); //runs functions to put passwords, name, and votes into arrays
                                String[] opt1names = getOpt1Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt2names = getOpt2Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt3names = getOpt3Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt4names = getOpt4Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt5names = getOpt5Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt6names = getOpt6Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt7names = getOpt7Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt8names = getOpt8Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt9names = getOpt9Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt10names = getOpt10Names((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt1PollCount = getOpt1PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt2PollCount = getOpt2PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt3PollCount = getOpt3PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt4PollCount = getOpt4PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt5PollCount = getOpt5PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt6PollCount = getOpt6PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt7PollCount = getOpt7PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt8PollCount = getOpt8PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt9PollCount = getOpt9PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt10PollCount = getOpt10PollCount((Map<String, Object>) dataSnapshot.getValue());
                                String[] passwords = getPasswords((Map<String, Object>) dataSnapshot.getValue());
                                String[] delete = getDeletePasswords((Map<String,Object>) dataSnapshot.getValue());
                                //String[] yesnames = getOpt1Names((Map<String, Object>) dataSnapshot.getValue());
                                //String[] nonames = getNoNames((Map<String, Object>) dataSnapshot.getValue());
                                LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
                                Context context = getApplicationContext();
                                for(int i=0;i<names.length;i++){
                                    if(passwords[i].equals(password)) { //if passwords match, make a button with the name of the poll
                                        final Button pollButton = new Button(context);
                                        pollButton.setId(i);
                                        pollButton.setText(names[i]);
                                        layout.addView(pollButton);
                                        final String opt1name = opt1names[i];
                                        final String opt2name = opt2names[i];
                                        final String opt3name = opt3names[i];
                                        final String opt4name = opt4names[i];
                                        final String opt5name = opt5names[i];
                                        final String opt6name = opt6names[i];
                                        final String opt7name = opt7names[i];
                                        final String opt8name = opt8names[i];
                                        final String opt9name = opt9names[i];
                                        final String opt10name = opt10names[i];
                                        final long opt1count = opt1PollCount[i];
                                        final long opt2count = opt2PollCount[i];
                                        final long opt3count = opt3PollCount[i];
                                        final long opt4count = opt4PollCount[i];
                                        final long opt5count = opt5PollCount[i];
                                        final long opt6count = opt6PollCount[i];
                                        final long opt7count = opt7PollCount[i];
                                        final long opt8count = opt8PollCount[i];
                                        final long opt9count = opt9PollCount[i];
                                        final long opt10count = opt10PollCount[i];
                                        final String del = delete[i];
                                        pollButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent startIntent = new Intent(getApplicationContext(), viewpoll.class);
                                                startIntent.putExtra("name", (String)pollButton.getText());
                                                startIntent.putExtra("opt1info", opt1name+": "+Integer.toString((int)opt1count));
                                                startIntent.putExtra("opt2info", opt2name+": "+Integer.toString((int)opt2count));
                                                startIntent.putExtra("opt3info", opt3name+": "+Integer.toString((int)opt3count));
                                                startIntent.putExtra("opt4info", opt4name+": "+Integer.toString((int)opt4count));
                                                startIntent.putExtra("opt5info", opt5name+": "+Integer.toString((int)opt5count));
                                                startIntent.putExtra("opt6info", opt6name+": "+Integer.toString((int)opt6count));
                                                startIntent.putExtra("opt7info", opt7name+": "+Integer.toString((int)opt7count));
                                                startIntent.putExtra("opt8info", opt8name+": "+Integer.toString((int)opt8count));
                                                startIntent.putExtra("opt9info", opt9name+": "+Integer.toString((int)opt9count));
                                                startIntent.putExtra("opt10info", opt10name+": "+Integer.toString((int)opt10count));
                                                startIntent.putExtra("delPass", del);
                                                //startIntent.putExtra(""+n+"", yesname);
                                                //startIntent.putExtra(""+o+"", noname);
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
        });

    }
    private String[] getOpt1Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt1"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt2Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt2"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt3Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt3"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt4Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt4"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt5Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt5"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt6Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt6"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt7Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt7"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt8Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt8"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt9Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt9"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt10Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt10"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getPasswords(Map<String, Object> polls){ //puts passwords into a long array by iterating though all polls
        ArrayList<String> passwords= new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            passwords.add((String)poll.get("password"));
        }
        return (String[])  passwords.toArray(new String[0]);
    }
    private String[] getDeletePasswords(Map<String, Object> polls){ //puts delete passwords into a long array by iterating though all polls
        ArrayList<String> deletepasswords= new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            deletepasswords.add((String) poll.get("delete"));
        }
        return (String[])  deletepasswords.toArray(new String[0]);
    }
    private Long[] getOpt2PollCount(Map<String, Object> polls){ //puts nos into a long array by iterating though all polls
        ArrayList<Long> noCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noCount.add((Long) poll.get("opt2ans"));
        }
        return (Long[]) noCount.toArray(new Long[0]);
    }
    private Long[] getOpt1PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt1ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt3PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt3ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt4PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt4ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt5PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt5ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt6PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt6ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt7PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt7ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt8PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt8ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt9PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt9ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt10PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<Long>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt10ans"));
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
    private String[] getYesNames(Map<String, Object> polls){ //puts names into a long array by iterating though all polls
        ArrayList<String> yesNames = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesNames.add((String)poll.get("yesnames"));
        }
        return (String[])yesNames.toArray(new String[0]);
    }

    private String[] getNoNames(Map<String, Object> polls){ //puts names into a long array by iterating though all polls
        ArrayList<String> noNames = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noNames.add((String)poll.get("nonames"));
        }
        return (String[])noNames.toArray(new String[0]);
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
                        String[] opt1names = getOpt1Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt2names = getOpt2Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt3names = getOpt3Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt4names = getOpt4Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt5names = getOpt5Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt6names = getOpt6Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt7names = getOpt7Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt8names = getOpt8Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt9names = getOpt9Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt10names = getOpt10Names((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt1PollCount = getOpt1PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt2PollCount = getOpt2PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt3PollCount = getOpt3PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt4PollCount = getOpt4PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt5PollCount = getOpt5PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt6PollCount = getOpt6PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt7PollCount = getOpt7PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt8PollCount = getOpt8PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt9PollCount = getOpt9PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt10PollCount = getOpt10PollCount((Map<String, Object>) dataSnapshot.getValue());
                        String[] passwords = getPasswords((Map<String, Object>) dataSnapshot.getValue());
                        String[] delete = getDeletePasswords((Map<String,Object>) dataSnapshot.getValue());
                        //String[] yesnames = getOpt1Names((Map<String, Object>) dataSnapshot.getValue());
                        //String[] nonames = getNoNames((Map<String, Object>) dataSnapshot.getValue());
                        LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
                        Context context = getApplicationContext();
                        for(int i=0;i<names.length;i++){
                            if(passwords[i].equals(password)) { //if passwords match, make a button with the name of the poll
                                final Button pollButton = new Button(context);
                                pollButton.setId(i);
                                pollButton.setText(names[i]);
                                layout.addView(pollButton);
                                final String opt1name = opt1names[i];
                                final String opt2name = opt2names[i];
                                final String opt3name = opt3names[i];
                                final String opt4name = opt4names[i];
                                final String opt5name = opt5names[i];
                                final String opt6name = opt6names[i];
                                final String opt7name = opt7names[i];
                                final String opt8name = opt8names[i];
                                final String opt9name = opt9names[i];
                                final String opt10name = opt10names[i];
                                final long opt1count = opt1PollCount[i];
                                final long opt2count = opt2PollCount[i];
                                final long opt3count = opt3PollCount[i];
                                final long opt4count = opt4PollCount[i];
                                final long opt5count = opt5PollCount[i];
                                final long opt6count = opt6PollCount[i];
                                final long opt7count = opt7PollCount[i];
                                final long opt8count = opt8PollCount[i];
                                final long opt9count = opt9PollCount[i];
                                final long opt10count = opt10PollCount[i];
                                final String del = delete[i];
                                pollButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent startIntent = new Intent(getApplicationContext(), viewpoll.class);
                                        startIntent.putExtra("name", (String)pollButton.getText());
                                        startIntent.putExtra("opt1info", opt1name+": "+Integer.toString((int)opt1count));
                                        startIntent.putExtra("opt2info", opt2name+": "+Integer.toString((int)opt2count));
                                        startIntent.putExtra("opt3info", opt3name+": "+Integer.toString((int)opt3count));
                                        startIntent.putExtra("opt4info", opt4name+": "+Integer.toString((int)opt4count));
                                        startIntent.putExtra("opt5info", opt5name+": "+Integer.toString((int)opt5count));
                                        startIntent.putExtra("opt6info", opt6name+": "+Integer.toString((int)opt6count));
                                        startIntent.putExtra("opt7info", opt7name+": "+Integer.toString((int)opt7count));
                                        startIntent.putExtra("opt8info", opt8name+": "+Integer.toString((int)opt8count));
                                        startIntent.putExtra("opt9info", opt9name+": "+Integer.toString((int)opt9count));
                                        startIntent.putExtra("opt10info", opt10name+": "+Integer.toString((int)opt10count));
                                        startIntent.putExtra("delPass", del);
                                        //startIntent.putExtra(""+n+"", yesname);
                                        //startIntent.putExtra(""+o+"", noname);
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
