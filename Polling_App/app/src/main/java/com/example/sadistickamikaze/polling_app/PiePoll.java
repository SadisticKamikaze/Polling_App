package com.example.sadistickamikaze.polling_app;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    public float[] yData={2.0f, 1.0f};;
    public String[] xData={"Yes" , "No"};
    public String pollName;
    public String description ;
    public String centerName;
    ArrayList<Integer> colors;
    ArrayList<PieEntry> yEntrys;
    ArrayList<String> xEntrys;
    PieChart pieChart;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_poll);
        TAG = "PiePoll";
        pollName = "TBD";
        description = "Some Description";
        centerName = "Some Center name";
        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        pieChart = (PieChart) findViewById(R.id.PiePoll);
        // pieChart.setDescription("Hello World");
        pieChart.setRotationEnabled(true);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setHoleRadius(25);
        pieChart.setDrawEntryLabels(true);
        addDataset();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Log.d(TAG, "onValueSelected, Value Select from Chart");
                Log.d(TAG, "onvalueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: "+ h.toString());

/*                int vote = e.toString().indexOf("Entry: ");
                String numberVotes= e.toString().substring(vote);

                for (int i=0;  i<yData.length; i++){
                    if(yData[i] == Float.parseFloat(numberVotes)){
                        vote=i;
                        break;
                    }
                }

                String response = xData[vote];
                Toast.makeText(PiePoll.this, "Response: " + response + "\n" + "Votes: " +numberVotes, Toast.LENGTH_SHORT).show();
*/
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }
    private void addDataset(){

        for (int i =0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i]));
        }

        for (int i=0; i<xData.length; i++){
            xEntrys.add(xData[i]);
        }


        // Create data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Votes");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(16);


        // Add colors
        colors.add(Color.RED);
        colors.add(Color.GREEN);
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
}