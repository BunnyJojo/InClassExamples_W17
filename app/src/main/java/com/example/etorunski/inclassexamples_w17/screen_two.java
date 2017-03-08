package com.example.etorunski.inclassexamples_w17;

import android.content.Intent;
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
        setContentView(R.layout.activity_screen_two);

try{
    Thread.sleep(6000);
}catch(Exception e)
{

}
        Intent i = getIntent();
   String name = i.getStringExtra("LoginName");
        int api = i.getIntExtra("API", 0);
    //    String name = getIntent().getExtras().getString("LoginName");

        //Get an object for the XML Button: button1
        Button b1 = (Button)findViewById(R.id.button1);



        //Get an object for the XML Switch: switch1
        Switch sw = (Switch)findViewById(  R.id.switch1);
        sw.setSelected(true);


        //add an onClick event for Button1:
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Button1", "Button clicked");
                Intent dataBack = new Intent();
                dataBack.putExtra("Payment", "Declined");
                setResult(5, dataBack);
                finish();
            }
        });

        //add a checkedchangeListener for the switch
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("Switch", "State changed:" + b);
            }
        });
    }
}
