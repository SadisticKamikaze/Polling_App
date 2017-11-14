package com.example.sadistickamikaze.polling_app;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class PiePoll extends AppCompatActivity {

    public String TAG;
    public ArrayList<Long> yData;
    public ArrayList<String> xData;
    public String pollName;
    public String description;
    public String centerName;
    public ArrayList<Integer> colors;
    public ArrayList<PieEntry> yEntrys;
    public ArrayList<String> xEntrys;
    PieChart pieChart;

    public PiePoll() {
        yData = new ArrayList<>();
        xData = new ArrayList<>();
        yEntrys = new ArrayList<>();
        xEntrys = new ArrayList<>();
        colors = new ArrayList<>();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_poll);



        TAG = "PiePoll";
        pollName = "TBD";
        description = "Some Description";
        centerName = "Some Center name";
        xData.add("Yes");
        xData.add("No");
        pieChart = (PieChart) findViewById(R.id.PiePoll);
        // pieChart.setDescription("Hello World");
        pieChart.setRotationEnabled(true);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setHoleRadius(25);
        pieChart.setDrawEntryLabels(true);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        yData.add((long)b.get("a"));
        yData.add((long)b.get("b"));
        Log.d("something",yData.toString() );
        addDataset();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Log.d(TAG, "onValueSelected, Value Select from Chart");
                Log.d(TAG, "onvalueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: "+ h.toString());

                int index = Math.round(h.getX());
                String answer = xData.get(index);
                Toast.makeText(PiePoll.this, "Response: " +answer+ "\n" + "Number of Votes: "  +h.getY() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }
    private void addDataset(){

        for (int i =0; i < yData.size(); i++){
            Log.d("something",yData.get(i).toString() );
            yEntrys.add(new PieEntry(yData.get(i)));
        }

        for (int i=0; i<xData.size(); i++){
            xEntrys.add(xData.get(i));
        }


        // Create data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Votes");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(16);


        // Add colors
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);


        // Create Pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

    public void addVotes (long l){
        yData.add(l);
    }


}