package com.example.android.weatherapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.weatherapp.data.WeatherInfoContract.WeatherInfoEntry;

/**
 * Created by Hugo on 06/03/2017.
 */

public class WeatherDbHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "weather.db";
    private static final int DB_VERSION = 1;

    public WeatherDbHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + WeatherInfoEntry.TABLE_NAME + " (" +
                WeatherInfoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", " +
                WeatherInfoEntry.COLUMN_DATE + " TEXT NOT NULL" + ", " +
                WeatherInfoEntry.COLUMN_WEATHER_ID + " INTEGER NOT NULL" + ", " +
                WeatherInfoEntry.COLUMN_LOW_TEMP + " INTEGER NOT NULL" + ", " +
                WeatherInfoEntry.COLUMN_HIGH_TEMP + " INTEGER NOT NULL" + ", " +
                WeatherInfoEntry.COLUMN_HUMIDITY + " INTEGER NOT NULL" + ", " +
                WeatherInfoEntry.COLUMN_PRESSURE + " INTEGER NOT NULL" + ", " +
                WeatherInfoEntry.COLUMN_WIND_SPEED + " INTEGER NOT NULL" + ", " +
                WeatherInfoEntry.COLUMN_DEGREES + " INTEGER NOT NULL" + ", " +

                " UNIQUE (" + WeatherInfoEntry.COLUMN_DATE + ") ON CONFLICT REPLACE);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + WeatherInfoEntry.TABLE_NAME + ";");
        onCreate(db);
    }
}
