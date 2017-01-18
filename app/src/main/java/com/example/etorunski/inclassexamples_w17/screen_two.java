package com.example.etorunski.inclassexamples_w17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class screen_two extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try { setContentView(R.layout.activity_screen_two);

        Button b1 = (Button)findViewById(R.id.button1);
        Switch sw = null;//= (Switch)findViewById(  R.id.switch1);

            sw.setSelected(false);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Button1", "Button clicked");
            }
        });
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("Switch", "State changed");
            }
        });
        }catch(Exception e)
    {

    }
    }
}
