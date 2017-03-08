package com.example.etorunski.inclassexamples_w17;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
            byte buffer[] = new byte[512];
            String in = "";
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric"); //("http://www.google.com/");

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inStream = urlConnection.getInputStream();
             //   inStream.read(buffer);
               //  in = new String(buffer);

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);

                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput( inStream  , "UTF-8");

                Log.i("XML parsing:" , "" + xpp.getEventType());
                int type;
                while((type = xpp.getEventType()) != XmlPullParser.END_DOCUMENT)
                {
                    if(xpp.getEventType() == XmlPullParser.START_TAG)
                    {
                        if(xpp.getName().equals("AMessage") )
                        {
                            String value =xpp.getAttributeValue(null, "message");
                            Log.i("XML Message:" , value );
                        }
                        else if(xpp.getName().equals("Weather") )
                        {
                            FileOutputStream of = openFileOutput("XMLData", Context.MODE_PRIVATE);
                            String value =xpp.getAttributeValue(null, "outlook");
                            Log.i("XML outlook:" , value );

                            value = xpp.getAttributeValue(null, "windy");
                            Log.i("XML windy:" , value );
                        }
                    }
                    xpp.next();
                }

            }catch( Exception me)
            {
                Log.e("AsyncTask", "Malformed URL:" + me.getMessage());
            }

     /*       try{
             /   for(int i = 0; i < args.length; i++) {
                    Thread.sleep(1000);
                    publishProgress((double)i / args.length); //telling android to update the gui

                }
            }
            catch (Exception e)
            {

            }
*/
            return in;
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
