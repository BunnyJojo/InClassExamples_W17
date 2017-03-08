package com.example.etorunski.inclassexamples_w17;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SharedPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);


        final EditText storedName = (EditText)findViewById(R.id.storedName);
        //Open a file for storing shared preferences:
        final SharedPreferences prefs = getSharedPreferences("myFileName", Context.MODE_PRIVATE);

        //Read the number of times run in the file:
        String previousName= prefs.getString("USER", "NONE");
        int myNum = prefs.getInt("name", 10);

        storedName.setText(previousName);
        Button b = (Button)findViewById(R.id.go_back_button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get an editor object for writing to the file:
                SharedPreferences.Editor writer = prefs.edit();

                writer.putString("USER", storedName.getText().toString());

                //Write the file:
                writer.commit();
                finish();
            }
        });


    }
}
