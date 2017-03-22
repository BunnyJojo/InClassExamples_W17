package com.example.etorunski.inclassexamples_w17;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class WelcomePage extends AppCompatActivity  {
    Vibrator v ;
    private Context ctx;

    private ListView theList;
    private String [] examples = {"Sensors","Layouts", "Activities", "SharedPreferences",
            "ListView" , "Databases", "AsyncTask", "Navigation bar"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        //Set the window to be full screen:
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ctx = this;

        setContentView(R.layout.activity_welcome_page);


        //get a reference to the vibration motor:
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        theList = (ListView)findViewById(R.id.theList);
        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(v != null)   v.vibrate(500); //Vibrate the phone for 500 milliseconds

                switch(position)
                {
                    case 0: //Sensors
                        break;
                    case 1: //Layouts
                        //Start the Screen_Two activity, with 10 as the result code
                        Intent nextActivity = new Intent(ctx, screen_two.class);
                        nextActivity.putExtra("LoginName", "Eric");
                        nextActivity.putExtra("API", 22);
                        startActivityForResult(nextActivity,10);
                        break;
                    case 2: //screen 3
                        startActivityForResult(new Intent(ctx,Screen_three.class ), 20);
                        break;
                    case 3:     //shared preferences
                        startActivity(new Intent(ctx, SharedPreferencesActivity.class));
                        break;
                    case 4: //ListView activity
                        startActivity(new Intent(ctx, ListViewActivity.class ));
                        break;
                    case 5:
                        startActivity(new Intent(ctx, SQLActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(ctx, AsyncExample.class));
                        break;
                    case 7: //navigation bar
                        startActivity(new Intent(ctx, NavigationBarExample.class));
                        break;
                }
            }
        });
        theList.setAdapter(new ArrayAdapter<>(this, R.layout.row_layout, examples ));



        //t.run();
        Log.d("Main", "OnCreate");

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
