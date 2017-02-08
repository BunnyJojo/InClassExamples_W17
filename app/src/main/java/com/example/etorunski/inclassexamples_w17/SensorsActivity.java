package com.example.etorunski.inclassexamples_w17;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class SensorsActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private EditText xText, yText, zText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        xText= (EditText)findViewById(R.id.xText);
        yText= (EditText)findViewById(R.id.yText);
        zText= (EditText)findViewById(R.id.zText);

        // get a reference to the Gyroscope sensor:
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(mSensorManager != null) {
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (mSensor != null)
                mSensorManager.registerListener(new MySensorListener(), mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }



    private class MySensorListener implements SensorEventListener {
        //This tells you if the accuracy of a sensor has changed, like the GPS accuracy
        public void onAccuracyChanged(Sensor sens, int accuracy) {
        }

        //This tells you what the new value read by the sensor is.
        public void onSensorChanged(SensorEvent evt) {
            if (evt.values.length == 3) {
                xText.setText(Double.toString(evt.values[0]));
                yText.setText(Double.toString(evt.values[1]));
                zText.setText(Double.toString(evt.values[2]));
            }
        }
    }
}
