package com.example.etorunski.inclassexamples_w17;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SQLActivity extends AppCompatActivity {

    protected MyOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        dbHelper = new MyOpenHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ListView theList = (ListView) findViewById(R.id.theList);
  /*      ContentValues newValues = new ContentValues();
        newValues.put(MyOpenHelper.NAME_COLUMN, "banana");
        newValues.put(MyOpenHelper.DESCRIPTION_COLUMN, "FRUIT");
        newValues.put(MyOpenHelper.PRICE_COLUMN, "79");

        db.insert(MyOpenHelper.databaseName, "", newValues);

*/

        Cursor results = db.query(true, MyOpenHelper.databaseName,
                new String[] { MyOpenHelper.ID_COLUMN, MyOpenHelper.DESCRIPTION_COLUMN , MyOpenHelper.NAME_COLUMN, MyOpenHelper.PRICE_COLUMN},
                "PRICE > ? AND NAME > ?", new String[] {"50", "baa"}, null, null, null, null);

        int rows = results.getCount() ; //number of rows returned
        results.moveToFirst(); //move to first result

        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.sqlrow, results,
                new String[]{   MyOpenHelper.PRICE_COLUMN,    MyOpenHelper.NAME_COLUMN}, //from column
                new int[] {     R.id.price_text,              R.id.name_text}, 0  //to layout ID
                );
        theList.setAdapter( listAdapter );

        int numColumns = results.getColumnCount();//how many columns are returned
        for(int i = 0; i < numColumns; i++)
        {
            String columnName = results.getColumnName(i);
            Log.i("SQL", "Column name:" + i + " = " +columnName);
        }
        /*

        int columnName = results.getColumnIndex(MyOpenHelper.NAME_COLUMN); //find the index of the name column
        int priceIndex = results.getColumnIndex(MyOpenHelper.PRICE_COLUMN);

        while(!results.isAfterLast()) {
            int price = results.getInt(priceIndex); //get int from column 0
            String name = results.getString(columnName);
            Log.d("DATABASE " , "Price:"+ Integer.toString(price)+ " name:" + name);
            results.moveToNext();
        }
        */
    }
}
