package com.example.my_magnaticsensore;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


//bransh master
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor magneticFieldSensor;//Check the magnetic field around the device
    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.values);
        textView2 = findViewById(R.id.locationValues);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            Toast.makeText(getApplicationContext(), "Sensor is find", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "Sensor is not find", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            int x = (int) Math.abs(event.values[0]);
            int y = (int) Math.abs(event.values[1]);
            int z = (int) Math.abs(event.values[2]);

            textView.setText(" val X: " + x + " \n " + " val Y: " + y + " \n " + " val Z: " + z
                    + " \n " + "MaxVal: " + " \n " + event.sensor.getMaximumRange());

            if (x > y && x > z) {
                textView2.setText(" X ");
            } else if (y > x && y > z) {
                textView2.setText(" Y ");

            } else if (z > x && z > y) {
                textView2.setText(" Z ");

            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, magneticFieldSensor, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}