package com.example.android.weatherapp.data;

import android.provider.BaseColumns;

/**
 * Created by Hugo on 06/03/2017.
 */

public class WeatherInfoContract
{
    public static final class WeatherInfoEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "weatherinfo";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_WEATHER_ID = "weather_id";
        public static final String COLUMN_LOW_TEMP = "low";
        public static final String COLUMN_HIGH_TEMP = "high";
        public static final String COLUMN_HUMIDITY = "humidity";
        public static final String COLUMN_PRESSURE = "pressure";
        public static final String COLUMN_WIND_SPEED = "wind";
        public static final String COLUMN_DEGREES = "degrees";
    }
}
