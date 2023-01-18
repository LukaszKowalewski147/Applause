package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class StatsGraph extends AppCompatActivity {

    private GraphView graph;
    private ArrayList<ClapsSession> clapsSessions;
    private SessionType sessionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_graph);

        Bundle extras = getIntent().getExtras();
        sessionType = (SessionType) extras.get("sessionTypeKey");

        graph = findViewById(R.id.graph);

        clapsSessions = new ArrayList<>();

        prepareData();
        prepareGraph();
    }

    private void prepareData() {
        JSONCommunicator communicator = new JSONCommunicator(this);
        ArrayList<ClapsSession> allClapsSessions = communicator.getAllClapsSessions(Session.login);

        for (ClapsSession session : allClapsSessions) {
            if (session.getSessionType() == sessionType)
                clapsSessions.add(session);
        }
    }

    private void prepareGraph() {
        LineGraphSeries<DataPoint> avgSeries = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> maxSeries = new LineGraphSeries<>();
        int maxDataPoints = 500;
        int x = 0;

        switch (sessionType) {
            case SPEED:
                avgSeries.setTitle("Średnia szybkość");
                maxSeries.setTitle("Maksymalna szybkość");
                for (ClapsSession session : clapsSessions) {
                    avgSeries.appendData(new DataPoint(++x, session.getAvgSpeed()), true, maxDataPoints);
                    maxSeries.appendData(new DataPoint(x, session.getMaxSpeed()), true, maxDataPoints);
                }
                maxSeries.setColor(Color.RED);
                maxSeries.setDrawDataPoints(true);
                maxSeries.setDataPointsRadius(10.0f);
                break;

            case FORCE:
                avgSeries.setTitle("Średnia siła");
                maxSeries.setTitle("Maksymalna siła");
                for (ClapsSession session : clapsSessions) {
                    avgSeries.appendData(new DataPoint(++x, session.getAvgForce()), true, maxDataPoints);
                    maxSeries.appendData(new DataPoint(x, session.getMaxForce()), true, maxDataPoints);
                }
                maxSeries.setColor(Color.RED);
                maxSeries.setDrawDataPoints(true);
                maxSeries.setDataPointsRadius(10.0f);
                break;

            case QUALITY:
                avgSeries.setTitle("Jakość");
                for (ClapsSession session : clapsSessions) {
                    avgSeries.appendData(new DataPoint(++x, session.getQuality()), true, maxDataPoints);
                }
                break;

            case QUANTITY:
                avgSeries.setTitle("Ilość");
                for (ClapsSession session : clapsSessions) {
                    avgSeries.appendData(new DataPoint(++x, session.getQuantity()), true, maxDataPoints);
                }
                break;

            case REFLEX:
                avgSeries.setTitle("Czas reakcji");
                for (ClapsSession session : clapsSessions) {
                    avgSeries.appendData(new DataPoint(++x, session.getReflex()), true, maxDataPoints);
                }
                break;
        }

        setStandardGraphParameters();

        avgSeries.setColor(Color.BLUE);
        avgSeries.setDrawDataPoints(true);
        avgSeries.setDataPointsRadius(10.0f);

        switch (sessionType) {
            case SPEED:
                graph.getViewport().setMaxY(maxSeries.getHighestValueY() + 40.0d);
                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            if (value % 1 == 0) {
                                return super.formatLabel(value, isValueX);
                            } else
                                return null;
                        }
                        return super.formatLabel(value, isValueX) + "/min";
                    }
                });
                graph.addSeries(maxSeries);
                break;

            case FORCE:
                graph.getViewport().setMaxY(maxSeries.getHighestValueY() + 20.0d);
                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            if (value % 1 == 0) {
                                return super.formatLabel(value, isValueX);
                            } else
                                return null;
                        }
                        return super.formatLabel(value, isValueX) + "N";
                    }
                });
                graph.addSeries(maxSeries);
                break;

            case QUALITY:
                graph.getViewport().setMaxY(avgSeries.getHighestValueY() + 20.0d);
                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            if (value % 1 == 0) {
                                return super.formatLabel(value, isValueX);
                            } else
                                return null;
                        }
                        return super.formatLabel(value, isValueX) + "%";
                    }
                });
                break;

            case QUANTITY:
                graph.getViewport().setMaxY(avgSeries.getHighestValueY() + 20.0d);
                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            if (value % 1 == 0) {
                                return super.formatLabel(value, isValueX);
                            } else
                                return null;
                        }
                        return super.formatLabel(value, isValueX);
                    }
                });
                break;

            case REFLEX:
                graph.getViewport().setMaxY(avgSeries.getHighestValueY() + 20.0d);
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

        }

        graph.addSeries(avgSeries);
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