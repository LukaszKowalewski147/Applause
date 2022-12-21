package com.example.applause;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class AccelerometerHandler extends AppCompatActivity implements SensorEventListener{

    private Displacement displacement;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private double zAxis;
    private long lastTime;
    private long time;
    private boolean firstEvent;
    private AtomicBoolean contactOccured;

    public AccelerometerHandler(Context context) {
        displacement = new Displacement();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        zAxis = 0.0d;
        lastTime = 0L;
        time = 0L;
        firstEvent = true;
        contactOccured = new AtomicBoolean(false);
    }

    public Queue<AccelerationVector> handleAccelerometerStop() {
        stopAccelerometer();
        return displacement.getEntries();
    }

    public void startAccelerometer() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void stopAccelerometer() {
        sensorManager.unregisterListener(this, accelerometer);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        zAxis = sensorEvent.values[2];

        if (firstEvent) {
            lastTime = sensorEvent.timestamp;
            firstEvent = false;
        }

        if (zAxis < -40.0d) {
            contactOccured.compareAndSet(false, true);
        }

        time = sensorEvent.timestamp - lastTime;
        lastTime = sensorEvent.timestamp;

        displacement.addEntry(zAxis, time);
        //showResults(zAxis, time);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
