package com.example.sadistickamikaze.polling_app;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class PiePoll extends AppCompatActivity {
    public String[] info;
    public String TAG;
    public int number_of_options;
    public ArrayList<Long> yData;
    public ArrayList<String> xData;
    public String pollName;
    public String centerName;
    public ArrayList<Integer> colors;
    public ArrayList<PieEntry> yEntrys;
    public ArrayList<String> xEntrys;
    public Description description;
    PieChart pieChart;

    public PiePoll() {
        yData = new ArrayList<>();
        xData = new ArrayList<>();
        yEntrys = new ArrayList<>();
        xEntrys = new ArrayList<>();
        colors = new ArrayList<>();
        description = new Description();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_poll);

        info = new String[10];

        TAG = "PiePoll";

        // xData.add("Yes");
        // xData.add("No");
        pieChart = (PieChart) findViewById(R.id.PiePoll);
        // pieChart.setDescription("Hello World");

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if ((long) b.get("a") > 0) {
            yData.add((long) b.get("a"));
        }
        if ((long) b.get("b") > 0) {
            yData.add((long) b.get("b"));
        }
        if ((long) b.get("c") > 0) {
            yData.add((long) b.get("c"));
        }
        if ((long) b.get("d") > 0) {
            yData.add((long) b.get("d"));
        }
        if ((long) b.get("e") > 0) {
            yData.add((long) b.get("e"));
        }
        if ((long) b.get("f") > 0) {
            yData.add((long) b.get("f"));
        }
        if ((long) b.get("g") > 0) {
            yData.add((long) b.get("g"));
        }
        if ((long) b.get("h") > 0) {
            yData.add((long) b.get("h"));
        }
        if ((long) b.get("i") > 0) {
            yData.add((long) b.get("i"));
        }
        if ((long) b.get("j") > 0) {
            yData.add((long) b.get("j"));
        }

        pollName = "TBD";
        description.setText("Question Name");
        description.setTextSize(16);
        description.setPosition(500,50);
        centerName = "Some Center name";
        pieChart.setRotationEnabled(true);

        pieChart.setNoDataText("No Votes have been recorded");
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setHoleRadius(15);
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelTextSize(20);
        pieChart.setDescription(description);
        info = (String[]) b.get("names");
        number_of_options = (int) b.get("count");

        for (int i = 0; i < info.length; i++) {
            xData.add(info[i]);
        }


        Log.d("something", yData.toString());
        addDataset();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Log.d(TAG, "onValueSelected, Value Select from Chart");
                Log.d(TAG, "onvalueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int index = Math.round(h.getX());
                String answer = xData.get(index);
                Toast.makeText(PiePoll.this, "Response: " + answer + "\n" + "Number of Votes: " + h.getY(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void addDataset() {

        for (int i = 0; i < yData.size(); i++) {
            Log.d("something", yData.get(i).toString());
            yEntrys.add(new PieEntry(yData.get(i)));
        }

        for (int i = 0; i < xData.size(); i++) {
            xEntrys.add(xData.get(i));
        }


        // Create data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Votes");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(20);


        // Add colors
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.BLACK);
        colors.add(Color.GRAY);
        colors.add(Color.MAGENTA);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.DKGRAY);
        colors.add(Color.LTGRAY);
        /*
        if ( ==){
            colors.add(Color.rgb());
        }

        if ( ==){
            colors.add(Color.rgb());
        }

        if ( ==){
            colors.add(Color.rgb());
        }

        if ( ==){
            colors.add(Color.rgb());
        }

        if ( ==){
            colors.add(Color.rgb());
        }

        if ( ==){
            colors.add(Color.rgb());
        }

        if ( ==){
            colors.add(Color.rgb());
        }

        if ( ==){
            colors.add(Color.rgb());
        }

        if ( ==){
            colors.add(Color.rgb());
        }

        if ( ==){
            colors.add(Color.rgb());
        }

        */
        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
        legend.setTextSize(16);

        // Create Pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}