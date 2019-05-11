package com.store.minecryps.Utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisplayPie {

    PieChart pieChart;

    public  void DisplayGraph(String totalCoin, String totalCoinMined){
        ArrayList<PieEntry> yValue=new ArrayList<>();
        yValue.add(new PieEntry(Float.parseFloat(totalCoinMined),"Total Coin Mined"));
        yValue.add(new PieEntry(Float.parseFloat(totalCoin),"Total Coin"));

        PieDataSet pieDataSet= new PieDataSet(yValue,"");
        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.rgb(249,147,81));
        colors.add(Color.rgb(109,33,79));

        pieDataSet.setColors(colors);
        PieData pieData= new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.setDrawHoleEnabled(false);
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.BLACK);
    }
}
