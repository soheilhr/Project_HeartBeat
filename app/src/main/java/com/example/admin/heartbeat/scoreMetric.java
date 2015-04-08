package com.example.admin.heartbeat;

import android.app.Application;

public class scoreMetric extends Application {

    private double[] metrics;

    public double[] getMetrics() {
        return metrics;
    }

    public void setMetrics(double[] metrics) {
        this.metrics = metrics;
    }
}
