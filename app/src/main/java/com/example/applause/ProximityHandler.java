package com.example.applause;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.appcompat.app.AppCompatActivity;

public class ProximityHandler extends AppCompatActivity implements SensorEventListener {
    private Context context;
    private SensorManager sensorManager;
    private Sensor proximity;

    private long lastTime;
    private long time;
    private boolean firstEvent;

    public ProximityHandler(Context context) {
        this.context = context;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        lastTime = 0L;
        time = 0L;
        firstEvent = true;
    }

    public void startProximity() {
        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void stopProximity() {
        sensorManager.unregisterListener(this, proximity);

        Intent intent = new Intent(context, ClapsSummary.class);
        intent.putExtra("sessionTypeKey", SessionType.REFLEX);
        intent.putExtra("reflexKey", time);
        context.startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (firstEvent) {
            lastTime = sensorEvent.timestamp;
            firstEvent = false;
        } else {
            if (sensorEvent.values[0] < 2.0f) {
                time = sensorEvent.timestamp - lastTime;
                stopProximity();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
