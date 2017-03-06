package com.example.android.weatherapp.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by Hugo on 05/03/2017.
 */

public class JsonUtils
{
    private static final String OWM_LIST = "list";
    private static final String OWM_MAIN = "main";
    private static final String OWM_TEMPERATURE = "temp";
    private static final String OWM_MAX = "temp_max";
    private static final String OWM_MIN = "temp_min";
    private static final String OWM_WEATHER = "weather";
    private static final String OWM_WEATHER_ID = "id";
    private static final String OWM_MESSAGE_CODE = "cod";

    public static String[] getSimpleWeatherStringsFromJson(Context context, String forecastJsonStr)
            throws JSONException
    {
        String[] parsedWeatherData;
        JSONObject forecastJson = new JSONObject(forecastJsonStr);

        if (forecastJson.has(OWM_MESSAGE_CODE))
        {
            int errorCode = forecastJson.getInt(OWM_MESSAGE_CODE);

            switch (errorCode)
            {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    return null;
                default:
                    return null;
            }
        }

        JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);
        parsedWeatherData = new String[weatherArray.length()];
        long startDay = WeatherAppDateUtils.getNormalizedUtcDateForToday();

        for (int i = 0; i < weatherArray.length(); i++)
        {
            String date;
            String highLow;
            long dateTimeMillis;
            double high;
            double low;
            int weatherId;
            String description;

            JSONObject dayForecast = weatherArray.getJSONObject(i);
            dateTimeMillis = startDay + WeatherAppDateUtils.DAY_IN_MILLIS * i;
            date = WeatherAppDateUtils.getFriendlyDateString(context, dateTimeMillis, false);

            JSONObject mainObject = dayForecast.getJSONObject(OWM_MAIN);
            high = mainObject.getDouble(OWM_MAX);
            low = mainObject.getDouble(OWM_MIN);
            highLow = WeatherFormatUtils.formatHighLows(context, high, low);

            JSONObject weatherObject =
                    dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);

            weatherId = weatherObject.getInt(OWM_WEATHER_ID);
            description = WeatherFormatUtils.getStringForWeatherCondition(context, weatherId);

            parsedWeatherData[i] = date + " - " + description + "\n" + highLow;
        }

        return parsedWeatherData;
    }
}