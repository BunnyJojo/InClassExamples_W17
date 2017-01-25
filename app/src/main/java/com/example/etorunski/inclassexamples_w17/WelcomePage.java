package com.example.etorunski.inclassexamples_w17;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


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

        Button screenThree = (Button)findViewById(R.id.screen_three_button);
        screenThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Create an intent to view a website:
                String url = "http://www.google.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                //set 100 as the request code. This will be returned in onActivityResult:
                startActivityForResult(i, 100);
            }
        });


        //get a reference to the vibration motor:
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        //Open a file for storing shared preferences:
        SharedPreferences prefs = getSharedPreferences("myFileName", Context.MODE_PRIVATE);

        //Read the number of times run in the file:
        int numTimesRun = prefs.getInt("TIMES_RUN", 0);

        //Get an editor object for writing to the file:
        SharedPreferences.Editor writer = prefs.edit();
        writer.putInt("TIMES_RUN", numTimesRun+1);
        writer.putString("USER", "ERIC");

        //Write the file:
        writer.commit();


        Log.d("Main", "OnCreate");

        // get a reference to the Gyroscope sensor:
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(mSensorManager != null) {
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            if (mSensor != null)
                mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    //When you click the button, vibrate the phone for 500 milliseconds
    public void buttonClick( View whatWasClicked   )
    {
        if(v != null)
            v.vibrate(500);


        //Start the Screen_Two activity, with 10 as the result code
        Intent nextActivity = new Intent(this, screen_two.class);
        nextActivity.putExtra("LoginName", "Eric");
        nextActivity.putExtra("API", 22);
        startActivityForResult(nextActivity,10);
    }


    //This tells you if the accuracy of a sensor has changed, like the GPS accuracy
    public void onAccuracyChanged(Sensor sens, int accuracy) {}

    //This tells you what the new value read by the sensor is.
    public void onSensorChanged(SensorEvent evt)
    {
        if(evt.values.length == 3)
        {
            float light = evt.values[0];
            Log.d("x is:" , ""+light + "," + evt.values[1] + " , " + evt.values[2]);
        }
    }

    protected void onResume()
    {
        super.onResume();
       Log.d("Main", "OnResume");
    }

    protected void onStart()
    {
        super.onStart();
        Log.d("Main", "OnStart");
    }

    protected void onPause()
    {
        super.onPause();
        Log.e("Main", "onPause");
    }

    protected void onStop()
    {
        super.onStop();
        Log.e("Main", "onStop");
    }


    //This function gets called when another activity has finished and this activity is resuming:
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(data != null)
        {
            String fromOtherActivity = data.getStringExtra("Payment");
            Log.d("Main", "Back from  other activity: " + fromOtherActivity);
        }
    }
}
