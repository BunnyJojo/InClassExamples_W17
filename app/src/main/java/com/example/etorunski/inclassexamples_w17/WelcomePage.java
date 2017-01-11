package com.example.etorunski.inclassexamples_w17;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class WelcomePage extends AppCompatActivity implements SensorEventListener {
    Vibrator v ;
    private SensorManager mSensorManager;
    private Sensor mSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the window to be full screen:
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_welcome_page);


        //get a reference to the vibration motor:
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);



        // get a reference to the Gyroscope sensor:
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        
    }

    //When you click the button, vibrate the phone for 500 milliseconds
    public void buttonClick( View whatWasClicked   )
    {
        v.vibrate(500);
    }

    //This tells you if the accuracy of a sensor has changed, like the GPS accuracy
    public void onAccuracyChanged(Sensor sens, int accuracy)
    {

    }

    //This tells you what the new value read by the sensor is.
    public void onSensorChanged(SensorEvent evt)
    {
        float light = evt.values[0];
        Log.d("x is:" , ""+light + "," + evt.values[1] + " , " + evt.values[2]);
    }
}
