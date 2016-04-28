package com.proafsolutions.cubatrip.ui.listener;

import com.jjoe64.graphview.series.DataPoint;

public interface TrackingListener {

    void updateDistance(Double distance);


    void updateAvgSpeed(Double avgSpeed);


    void updateMaxSpeed(Double maxSpeed);


    void updateDistanceGraphSeries(DataPoint[][] dataPoints);


    void addDistanceGraphSeriesPoint(DataPoint speed, DataPoint distance);
}
