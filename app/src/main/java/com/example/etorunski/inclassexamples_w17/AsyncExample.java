package com.example.etorunski.inclassexamples_w17;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class AsyncExample extends AppCompatActivity {
EditText outputText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_example);

        outputText = (EditText)findViewById(R.id.output);

        MyAsyncTask thread =     new MyAsyncTask();
        thread.execute(0, 6 ,5, 26, 267, 264, 24746);
    }


    private class MyAsyncTask extends AsyncTask<Integer, Double, String>
    {
        @Override
        protected String doInBackground(Integer ... args)
        {

            try{
                for(int i = 0; i < args.length; i++) {
                    Thread.sleep(1000);
                    publishProgress((double)i / args.length); //telling android to update the gui

                }
            }
            catch (Exception e)
            {

            }

            return "Done";
        }

        public void onProgressUpdate(Double ... progress)
        {
            outputText.setText("Progress:" + progress[0]);

            Log.i("ASYNCTASK", "" + progress[0]);
        }

        public void onPostExecute(String work)
        {
            outputText.setText("Work finished");
            Log.i("ASYNC TASK DONE:" , work);
        }
    }
}
