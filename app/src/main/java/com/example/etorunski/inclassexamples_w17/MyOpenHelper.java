package com.example.etorunski.inclassexamples_w17;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by etorunski on 2017-02-08.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION_NUM = 1;
    public static final String databaseName = "MyDatabase";
    public static final String NAME_COLUMN = "NAME";
    public static final String DESCRIPTION_COLUMN = "DESCRIPTION";
    public static final String PRICE_COLUMN = "PRICE";
    public static final String ID_COLUMN = "_id";

    public MyOpenHelper(Context ctx)
    {
        super(ctx, "DatabaseFileName", null, VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase db) //only called if not yet created
    {
        db.execSQL("CREATE TABLE " + databaseName + "(+" + ID_COLUMN + " INTEGER PRIMARY KEY, "+
                NAME_COLUMN+ " VARCHAR(256), "+
                DESCRIPTION_COLUMN+" text, "+
                PRICE_COLUMN+ " INTEGER);" );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + databaseName);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + databaseName);
        onCreate(db);
    }
}
