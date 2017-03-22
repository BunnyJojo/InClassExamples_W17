package com.example.etorunski.inclassexamples_w17;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NavigationBarExample extends AppCompatActivity {
Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar_example);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarBlue);
        setSupportActionBar(myToolbar);
        ctx = this;

    }

    public boolean onCreateOptionsMenu(Menu m)  //put the menu in the toolbar
    {
        MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.blue_menu,  m); //makes m look like blue_menu

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {       //when an item was clicked
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_item1:
                //show a Toast
                Log.i("Navigation", "1");
                Toast.makeText(this, "Share", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_item2:
                Log.i("Navigation", "2");
                //launch another Activity
                break;
            case R.id.menu_item3://dog
                Log.i("Navigation", "3");


                LayoutInflater inflater = getLayoutInflater();
View v = inflater.inflate(R.layout.activity_screen_two, null); //v is a RelativeLayout
Button b = (Button)v.findViewById(R.id.button1);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("Button 1", "Click");
                    }
                });
                b = (Button)v.findViewById(R.id.button2);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("Button 2", "Click");
                    }
                });
                b = (Button)v.findViewById(R.id.button3);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("Button 3", "Click");
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

                builder.setMessage("This is a custom dialog")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Yes", "Yes");
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("No", "No");
                            }
                        }).setNeutralButton("Maybe", new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                             Log.i("Maybe", "Maybe");

                                 NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender(). setHintHideIcon(true);//.setBackground(mBitmap);

                                 NotificationCompat.Builder mBuilder =
                                         new NotificationCompat.Builder(ctx)
                                                 .setSmallIcon(R.drawable.dogicon)
                                                 .extend(wearableExtender)   // <---  this sends the notification to my watch
                                                 .setContentTitle("My notification")
                                                 .setContentText("Hello World!");


                                //where to go if clicked
                                 Intent resultIntent = new Intent(ctx, screen_two.class);
                                 PendingIntent resultPendingIntent = PendingIntent.getActivity( ctx, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                 mBuilder.setContentIntent(resultPendingIntent);

                                 //now show the notification:
                                 int mNotificationId = 0634;
                                 NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                 mNotifyMgr.notify(mNotificationId, mBuilder.build());


                             }
                });
                builder.setView(  v );
                builder.create().show();


                break;

        }
        return true;
    }
}
