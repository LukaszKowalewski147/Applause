package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class StatsGraph extends AppCompatActivity {

    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_graph);

        graph = findViewById(R.id.graph);
        prepareGraph();
    }

    private void prepareGraph() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

        series.appendData(new DataPoint(1, 260), true, 500);
        series.appendData(new DataPoint(2, 220), true, 500);
        series.appendData(new DataPoint(3, 230), true, 500);
        series.appendData(new DataPoint(4, 255), true, 500);
        series.appendData(new DataPoint(5, 250), true, 500);
        series.appendData(new DataPoint(6, 225), true, 500);
        series.appendData(new DataPoint(7, 230), true, 500);
        series.appendData(new DataPoint(8, 245), true, 500);
        series.appendData(new DataPoint(9, 240), true, 500);
        series.appendData(new DataPoint(10, 215), true, 500);
        series.appendData(new DataPoint(11, 210), true, 500);
        series.appendData(new DataPoint(12, 250), true, 500);
        series.appendData(new DataPoint(13, 195), true, 500);
        series.appendData(new DataPoint(14, 240), true, 500);
        series.appendData(new DataPoint(15, 255), true, 500);

        setStandardGraphParameters();

        series.setTitle("Czas reakcji");
        series.setColor(Color.RED);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10.0f);

        graph.getViewport().setMaxY(series.getHighestValueY() + 20.0d);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    if (value % 1 == 0) {
                        return super.formatLabel(value, isValueX);
                    } else
                        return null;
                }
                return super.formatLabel(value, isValueX) + "ms";
            }
        });
        graph.addSeries(series);
    }

    private void setStandardGraphParameters() {
        int maxXLabels = 10;

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        graph.getGridLabelRenderer().setHorizontalAxisTitle("Numer sesji");
        graph.getGridLabelRenderer().setNumHorizontalLabels(maxXLabels);
        graph.getGridLabelRenderer().setNumVerticalLabels(12);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0.0d);
        graph.getViewport().setMaxX(12);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0.0d);
        graph.getViewport().setScalable(true);
    }
}