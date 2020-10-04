package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

/*
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


 */
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.DarkSkyClient;
import tk.plogitech.darksky.forecast.ForecastException;
import tk.plogitech.darksky.forecast.ForecastRequest;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

/*
MainActivity.this.runOnUiThread(new Runnable() {
@Override
public void run() {
mTextViewResult.setText(myResponse);
{
});
 */


public class MainActivity extends AppCompatActivity {

    Double Temperature;
    Double humidity;
    Double windSpeed;
    Double PrecipitationProb;
    Double AverageTemp;
    String latitude;
    String longitude;
    String locationData;
    String longandlatpart;
    String website;
    String currentDateString;

    Button currentCond;
    TextView showCond;
    Button HourlyTemp;
    TextView listOfTemp;
    Button avgTemp;
    TextView averageTemp;
    Button UpcomingWeek;
    TextView showUpcomingWeek;
    TextView pastText;
    TextView mypastTemp;



    //private FusedLocationProviderClient fusedLocationProviderClient; //Need to provide the location client

    String myResponse;
    String urlPast;
    String pastForecast;
    String PastData;
    String PastTemp;
    Double tempPast = 54.43;    //Temperature during this date
    long unixTime = 1547169465; //This is specifying the date of 1st January 2019
    String tempPastURL = "https://api.darksky.net/forecast/08ad4e9735bb99cff1923c3615e7fde6/30.369280,-97.745041,1547169465";  //The URL for determining the forecast on January 1st, 2019







//y\":0.009,\"precipIntensityError\":0.005,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754160,\"precipIntensity\":0.009,\"precipIntensityError\":0.006,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754220,\"precipIntensity\":0.009,\"precipIntensityError\":0.006,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754280,\"precipIntensity\":0.009,\"precipIntensityError\":0.006,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754340,\"precipIntensity\":0.009,\"precipIntensityError\":0.006,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754400,\"precipIntensity\":0.01,\"precipIntensityError\":0.006,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754460,\"precipIntensity\":0.01,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754520,\"precipIntensity\":0.01,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754580,\"precipIntensity\":0.01,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754640,\"precipIntensity\":0.01,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754700,\"precipIntensity\":0.011,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754760,\"precipIntensity\":0.011,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754820,\"precipIntensity\":0.011,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754880,\"precipIntensity\":0.011,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573754940,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755000,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755060,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755120,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755180,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755240,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755300,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755360,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755420,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755480,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755540,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755600,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755660,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755720,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755780,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755840,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755900,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573755960,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573756020,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573756080,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":1,\"precipType\":\"rain\"},{\"time\":1573756140,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":0.99,\"precipType\":\"rain\"},{\"time\":1573756200,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":0.99,\"precipType\":\"rain\"},{\"time\":1573756260,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":0.99,\"precipType\":\"rain\"},{\"time\":1573756320,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":0.99,\"precipType\":\"rain\"},{\"time\":1573756380,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":0.99,\"precipType\":\"rain\"},{\"time\":1573756440,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":0.99,\"precipType\":\"rain\"},{\"time\":1573756500,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":0.99,\"precipType\":\"rain\"},{\"time\":1573756560,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":0.99,\"precipType\":\"rain\"},{\"time\":1573756620,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.98,\"precipType\":\"rain\"},{\"time\":1573756680,\"precipIntensity\":0.013,\"precipIntensityError\":0.007,\"precipProbability\":0.99,\"precipType\":\"rain\"},{\"time\":1573756740,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.98,\"precipType\":\"rain\"},{\"time\":1573756800,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.98,\"precipType\":\"rain\"},{\"time\":1573756860,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.98,\"precipType\":\"rain\"},{\"time\":1573756920,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.98,\"precipType\":\"rain\"},{\"time\":1573756980,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.98,\"precipType\":\"rain\"},{\"time\":1573757040,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.97,\"precipType\":\"rain\"},{\"time\":1573757100,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.97,\"precipType\":\"rain\"},{\"time\":1573757160,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.97,\"precipType\":\"rain\"},{\"time\":1573757220,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.97,\"precipType\":\"rain\"},{\"time\":1573757280,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.96,\"precipType\":\"rain\"},{\"time\":1573757340,\"precipIntensity\":0.012,\"precipIntensityError\":0.007,\"precipProbability\":0.96,\"precipType\":\"rain\"}]},\"hourly\":{\"summary\":\"Light rain this morning and afternoon.\",\"icon\":\"rain\",\"data\":[{\"time\":1573750800,\"summary\":\"Possible Drizzle\",\"icon\":\"rain\",\"precipIntensity\":0.0087,\"precipProbability\":0.43,\"precipType\":\"rain\",\"temperature\":41.59,\"apparentTemperature\":39.66,\"dewPoint\":40.7,\"humidity\":0.97,\"pressure\":1026.5,\"windSpeed\":3.47,\"windGust\":5.65,\"windBearing\":3,\"cloudCover\":1,\"uvIndex\":3,\"visibility\":3.432,\"ozone\":279.6},{\"time\":1573754400,\"summary\":\"Drizzle\",\"icon\":\"rain\",\"precipIntensity\":0.0124,\"precipProbability\":1,\"precipType\":\"rain\",\"temperature\":44.44,\"apparentTemperature\":42.07,\"dewPoint\":42,\"humidity\":0.91,\"pressure\":1026.3,\"windSpeed\":4.47,\"windGust\":6.93,\"windBearing\":359,\"cloudCover\":1,\"uvIndex\":3,\"visibility\":2.473,\"ozone\":279.8},{\"time\":1573758000,\"summary\":\"Light Rain\",\"icon\":\"rain\",\"precipIntensity\":0.0164,\"precipProbability\":0.99,\"precipType\":\"rain\",\"temperature\":46.28,\"apparentTemperature\":43.1,\"dewPoint\":42.69,\"humidity\":0.87,\"pressure\":1026.1,\"windSpeed\":6.24,\"windGust\":11.74,\"windBearing\":352,\"cloudCover\":1,\"uvIndex\":3,\"visibility\":2.446,\"ozone\":279.9},{\"time\":1573761600,\"summary\":\"Possible Light Rain\",\"icon\":\"rain\",\"precipIntensity\":0.0236,\"precipProbability\":0.52,\"precipType\":\"rain\",\"temperature\":47.61,\"apparentTemperature\":43.59,\"dewPoint\":41.66,\"humidity\":0.8,\"pressure\":1025.9,\"windSpeed\":8.62,\"windGust\":17.34,\"windBearing\":5,\"cloudCover\":0.94,\"uvIndex\":3,\"visibility\":6.465,\"ozone\":280.3},{\"time\":1573765200,\"summary\":\"Possible Light Rain\",\"icon\":\"rain\",\"precipIntensity\":0.0198,\"precipProbability\":0.52,\"precipType\":\"rain\",\"temperature\":47.56,\"apparentTemperature\":43.14,\"dewPoint\":40.85,\"humidity\":0.77,\"pressure\":1025.9,\"windSpeed\":9.62,\"windGust\":20.52,\"windBearing\":9,\"cloudCover\":0.91,\"uvIndex\":2,\"visibility\":9.441,\"ozone\":280.5},{\"time\":1573768800,\"summary\":\"Possible Drizzle\",\"icon\":\"rain\",\"precipIntensity\":0.0128,\"precipProbability\":0.39,\"precipType\":\"rain\",\"temperature\":47.61,\"apparentTemperature\":43.47,\"dewPoint\":40.09,\"humidity\":0.75,\"pressure\":1026.3,\"windSpeed\":8.93,\"windGust\":20.5,\"windBearing\":14,\"cloudCover\":0.73,\"uvIndex\":1,\"visibility\":10,\"ozone\":280.8},{\"time\":1573772400,\"summary\":\"Mostly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0.0046,\"precipProbability\":0.13,\"precipType\":\"rain\",\"temperature\":46.56,\"apparentTemperature\":42.75,\"dewPoint\":38.88,\"humidity\":0.74,\"pressure\":1026.8,\"windSpeed\":7.62,\"windGust\":20.2,\"windBearing\":17,\"cloudCover\":0.65,\"uvIndex\":0,\"visibility\":10,\"ozone\":281.1},{\"time\":1573776000,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-night\",\"precipIntensity\":0.0014,\"precipProbability\":0.05,\"precipType\":\"rain\",\"temperature\":45.23,\"apparentTemperature\":42.21,\"dewPoint\":38.2,\"humidity\":0.76,\"pressure\":1027.3,\"windSpeed\":5.62,\"windGust\":15.68,\"windBearing\":17,\"cloudCover\":0.54,\"uvIndex\":0,\"visibility\":10,\"ozone\":281.4},{\"time\":1573779600,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-night\",\"precipIntensity\":0.0015,\"precipProbability\":0.04,\"precipType\":\"rain\",\"temperature\":43.9,\"apparentTemperature\":41.54,\"dewPoint\":38.1,\"humidity\":0.8,\"pressure\":1028.1,\"windSpeed\":4.34,\"windGust\":10.8,\"windBearing\":18,\"cloudCover\":0.48,\"uvIndex\":0,\"visibility\":10,\"ozone\":281.6},{\"time\":1573783200,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":43.04,\"apparentTemperature\":40.76,\"dewPoint\":38.45,\"humidity\":0.84,\"pressure\":1028.7,\"windSpeed\":4.08,\"windGust\":9.02,\"windBearing\":351,\"cloudCover\":0.25,\"uvIndex\":0,\"visibility\":10,\"ozone\":281.7},{\"time\":1573786800,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":42.08,\"apparentTemperature\":39.5,\"dewPoint\":38.32,\"humidity\":0.86,\"pressure\":1029.5,\"windSpeed\":4.26,\"windGust\":7.93,\"windBearing\":339,\"cloudCover\":0.21,\"uvIndex\":0,\"visibility\":10,\"ozone\":282},{\"time\":1573790400,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":41.4,\"apparentTemperature\":38.5,\"dewPoint\":37.95,\"humidity\":0.87,\"pressure\":1029.5,\"windSpeed\":4.51,\"windGust\":9.27,\"windBearing\":333,\"cloudCover\":0.17,\"uvIndex\":0,\"visibility\":10,\"ozone\":283},{\"time\":1573794000,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0.0012,\"precipProbability\":0.01,\"precipType\":\"rain\",\"temperature\":40.73,\"apparentTemperature\":37.3,\"dewPoint\":37.28,\"humidity\":0.87,\"pressure\":1029.8,\"windSpeed\":5.05,\"windGust\":9.64,\"windBearing\":330,\"cloudCover\":0.14,\"uvIndex\":0,\"visibility\":10,\"ozone\":284.2},{\"time\":1573797600,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0.0006,\"precipProbability\":0.01,\"precipType\":\"rain\",\"temperature\":40.41,\"apparentTemperature\":36.45,\"dewPoint\":36.82,\"humidity\":0.87,\"pressure\":1029.7,\"windSpeed\":5.7,\"windGust\":10.49,\"windBearing\":334,\"cloudCover\":0.1,\"uvIndex\":0,\"visibility\":10,\"ozone\":285},{\"time\":1573801200,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":40.21,\"apparentTemperature\":35.88,\"dewPoint\":36.43,\"humidity\":0.86,\"pressure\":1029.9,\"windSpeed\":6.2,\"windGust\":12.93,\"windBearing\":337,\"cloudCover\":0.06,\"uvIndex\":0,\"visibility\":10,\"ozone\":284.6},{\"time\":1573804800,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":40.01,\"apparentTemperature\":34.99,\"dewPoint\":35.29,\"humidity\":0.83,\"pressure\":1030.4,\"windSpeed\":7.26,\"windGust\":18.85,\"windBearing\":350,\"cloudCover\":0.04,\"uvIndex\":0,\"visibility\":10,\"ozone\":283.9},{\"time\":1573808400,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":39.72,\"apparentTemperature\":34.45,\"dewPoint\":35.04,\"humidity\":0.83,\"pressure\":1030.6,\"windSpeed\":7.61,\"windGust\":20.7,\"windBearing\":353,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":283.9},{\"time\":1573812000,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":38.4,\"apparentTemperature\":32.98,\"dewPoint\":34.14,\"humidity\":0.85,\"pressure\":1030.9,\"windSpeed\":7.37,\"windGust\":20.29,\"windBearing\":351,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":285.8},{\"time\":1573815600,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":36.95,\"apparentTemperature\":31.6,\"dewPoint\":32.97,\"humidity\":0.85,\"pressure\":1031.2,\"windSpeed\":6.79,\"windGust\":18.58,\"windBearing\":348,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":288.5},{\"time\":1573819200,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":36.31,\"apparentTemperature\":31.13,\"dewPoint\":32.63,\"humidity\":0.86,\"pressure\":1031.5,\"windSpeed\":6.34,\"windGust\":16.98,\"windBearing\":344,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":290.1},{\"time\":1573822800,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":36.48,\"apparentTemperature\":31.4,\"dewPoint\":33.58,\"humidity\":0.89,\"pressure\":1031.9,\"windSpeed\":6.23,\"windGust\":16.12,\"windBearing\":346,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":289.4},{\"time\":1573826400,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":38.3,\"apparentTemperature\":33.54,\"dewPoint\":35.37,\"humidity\":0.89,\"pressure\":1032.2,\"windSpeed\":6.29,\"windGust\":15.34,\"windBearing\":346,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":287.7},{\"time\":1573830000,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":42.05,\"apparentTemperature\":37.77,\"dewPoint\":36.68,\"humidity\":0.81,\"pressure\":1032.3,\"windSpeed\":6.72,\"windGust\":14.03,\"windBearing\":353,\"cloudCover\":0,\"uvIndex\":1,\"visibility\":10,\"ozone\":285.9},{\"time\":1573833600,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":45.88,\"apparentTemperature\":42.23,\"dewPoint\":37.1,\"humidity\":0.71,\"pressure\":1032.2,\"windSpeed\":7,\"windGust\":11.73,\"windBearing\":2,\"cloudCover\":0,\"uvIndex\":3,\"visibility\":10,\"ozone\":284.1},{\"time\":1573837200,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":50.13,\"apparentTemperature\":50.13,\"dewPoint\":37.71,\"humidity\":0.62,\"pressure\":1031.5,\"windSpeed\":6.64,\"windGust\":9.02,\"windBearing\":8,\"cloudCover\":0,\"uvIndex\":4,\"visibility\":10,\"ozone\":282.3},{\"time\":1573840800,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":53.53,\"apparentTemperature\":53.53,\"dewPoint\":37.67,\"humidity\":0.55,\"pressure\":1030.8,\"windSpeed\":6.25,\"windGust\":6.96,\"windBearing\":13,\"cloudCover\":0,\"uvIndex\":5,\"visibility\":10,\"ozone\":280.7},{\"time\":1573844400,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":56.97,\"apparentTemperature\":56.97,\"dewPoint\":37.43,\"humidity\":0.48,\"pressure\":1029.8,\"windSpeed\":5.8,\"windGust\":5.98,\"windBearing\":15,\"cloudCover\":0,\"uvIndex\":5,\"visibility\":10,\"ozone\":279.6},{\"time\":1573848000,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":59.49,\"apparentTemperature\":59.49,\"dewPoint\":36.59,\"humidity\":0.42,\"pressure\":1028.6,\"windSpeed\":5.56,\"windGust\":5.61,\"windBearing\":18,\"cloudCover\":0,\"uvIndex\":4,\"visibility\":10,\"ozone\":278.9},{\"time\":1573851600,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":60.66,\"apparentTemperature\":60.66,\"dewPoint\":35.88,\"humidity\":0.4,\"pressure\":1027.5,\"windSpeed\":5.24,\"windGust\":5.44,\"windBearing\":22,\"cloudCover\":0,\"uvIndex\":2,\"visibility\":10,\"ozone\":278.2},{\"time\":1573855200,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":59.96,\"apparentTemperature\":59.96,\"dewPoint\":35.72,\"humidity\":0.4,\"pressure\":1027.2,\"windSpeed\":4.87,\"windGust\":5.57,\"windBearing\":30,\"cloudCover\":0,\"uvIndex\":1,\"visibility\":10,\"ozone\":278},{\"time\":1573858800,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":56.88,\"apparentTemperature\":56.88,\"dewPoint\":36.08,\"humidity\":0.46,\"pressure\":1027.2,\"windSpeed\":4.19,\"windGust\":5.85,\"windBearing\":42,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.8},{\"time\":1573862400,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":52.6,\"apparentTemperature\":52.6,\"dewPoint\":36.43,\"humidity\":0.54,\"pressure\":1027.5,\"windSpeed\":3.51,\"windGust\":5.91,\"windBearing\":52,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.7},{\"time\":1573866000,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":49.91,\"apparentTemperature\":49.4,\"dewPoint\":37.21,\"humidity\":0.62,\"pressure\":1027.7,\"windSpeed\":3.19,\"windGust\":5.69,\"windBearing\":53,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.5},{\"time\":1573869600,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":47.47,\"apparentTemperature\":47.47,\"dewPoint\":37.57,\"humidity\":0.68,\"pressure\":1028.2,\"windSpeed\":2.78,\"windGust\":5.35,\"windBearing\":49,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.4},{\"time\":1573873200,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":45.69,\"apparentTemperature\":45.69,\"dewPoint\":37.87,\"humidity\":0.74,\"pressure\":1028.4,\"windSpeed\":2.28,\"windGust\":4.9,\"windBearing\":45,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.3},{\"time\":1573876800,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":44.44,\"apparentTemperature\":44.44,\"dewPoint\":37.9,\"humidity\":0.78,\"pressure\":1028.4,\"windSpeed\":2.09,\"windGust\":4.15,\"windBearing\":10,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.3},{\"time\":1573880400,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":43.49,\"apparentTemperature\":43.49,\"dewPoint\":37.72,\"humidity\":0.8,\"pressure\":1028.3,\"windSpeed\":1.88,\"windGust\":3.3,\"windBearing\":350,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.5},{\"time\":1573884000,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":42.63,\"apparentTemperature\":42.63,\"dewPoint\":37.43,\"humidity\":0.82,\"pressure\":1028.2,\"windSpeed\":1.73,\"windGust\":2.68,\"windBearing\":307,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.6},{\"time\":1573887600,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":41.08,\"apparentTemperature\":41.08,\"dewPoint\":36.98,\"humidity\":0.85,\"pressure\":1027.9,\"windSpeed\":2.11,\"windGust\":2.6,\"windBearing\":304,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.7},{\"time\":1573891200,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":39.92,\"apparentTemperature\":39.92,\"dewPoint\":36.54,\"humidity\":0.88,\"pressure\":1027.4,\"windSpeed\":2.32,\"windGust\":2.82,\"windBearing\":295,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":278},{\"time\":1573894800,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0.0002,\"precipProbability\":0.01,\"precipType\":\"rain\",\"temperature\":38.8,\"apparentTemperature\":38.8,\"dewPoint\":35.93,\"humidity\":0.89,\"pressure\":1027.2,\"windSpeed\":2.48,\"windGust\":3.04,\"windBearing\":290,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":278},{\"time\":1573898400,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0.0002,\"precipProbability\":0.01,\"precipType\":\"rain\",\"temperature\":37.2,\"apparentTemperature\":37.2,\"dewPoint\":34.74,\"humidity\":0.91,\"pressure\":1027.2,\"windSpeed\":2.57,\"windGust\":3.18,\"windBearing\":287,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.7},{\"time\":1573902000,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0.0002,\"precipProbability\":0.01,\"precipType\":\"rain\",\"temperature\":35.77,\"apparentTemperature\":35.77,\"dewPoint\":33.65,\"humidity\":0.92,\"pressure\":1027.4,\"windSpeed\":2.59,\"windGust\":3.32,\"windBearing\":296,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.4},{\"time\":1573905600,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":35.28,\"apparentTemperature\":35.28,\"dewPoint\":33.2,\"humidity\":0.92,\"pressure\":1027.6,\"windSpeed\":2.55,\"windGust\":3.32,\"windBearing\":300,\"cloudCover\":0,\"uvIndex\":0,\"visibility\":10,\"ozone\":277.1},{\"time\":1573909200,\"summary\":\"Clear\",\"icon\":\"clear-night\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":35.62,\"apparentTemperature\":35.62,\"dewPoint\":34.11,\"humidity\":0.94,\"pressure\":1027.8,\"windSpeed\":2.31,\"windGust\":2.89,\"windBearing\":304,\"cloudCover\":0.1,\"uvIndex\":0,\"visibility\":10,\"ozone\":276.8},{\"time\":1573912800,\"summary\":\"Clear\",\"icon\":\"clear-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":37.69,\"apparentTemperature\":37.69,\"dewPoint\":35.65,\"humidity\":0.92,\"pressure\":1027.8,\"windSpeed\":2.01,\"windGust\":2.38,\"windBearing\":304,\"cloudCover\":0.25,\"uvIndex\":0,\"visibility\":10,\"ozone\":276.3},{\"time\":1573916400,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":41.11,\"apparentTemperature\":41.11,\"dewPoint\":36.62,\"humidity\":0.84,\"pressure\":1027.7,\"windSpeed\":2.06,\"windGust\":2.5,\"windBearing\":335,\"cloudCover\":0.34,\"uvIndex\":1,\"visibility\":10,\"ozone\":275.9},{\"time\":1573920000,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":45.81,\"apparentTemperature\":45.81,\"dewPoint\":37.62,\"humidity\":0.73,\"pressure\":1027.2,\"windSpeed\":2.77,\"windGust\":3.79,\"windBearing\":54,\"cloudCover\":0.35,\"uvIndex\":3,\"visibility\":10,\"ozone\":275.6},{\"time\":1573923600,\"summary\":\"Partly Cloudy\",\"icon\":\"partly-cloudy-day\",\"precipIntensity\":0.0002,\"precipProbability\":0.01,\"precipType\":\"rain\",\"temperature\":51.5,\"apparentTemperature\":51.5,\"dewPoint\":38.69,\"humidity\":0.61,\"pressure\":1026.5,\"windSpeed\":3.83,\"windGust\":5.65,\"windBearing\":118,\"cloudCover\":0.32,\"uvIndex\":4,\"visibility\":10,\"ozone\":275.2}]},\"daily\":{\"summary\":\"Light rain today and next Thursday.\",\"icon\":\"rain\",\"data\":[{\"time\":1573711200,\"summary\":\"Light rain in the morning and afternoon.\",\"icon\":\"rain\",\"sunriseTime\":1573736220,\"sunsetTime\":1573774560,\"moonPhase\":0.58,\"precipIntensity\":0.0054,\"precipIntensityMax\":0.0236,\"precipIntensityMaxTime\":1573761780,\"precipProbability\":0.97,\"precipType\":\"rain\",\"temperatureHigh\":48.19,\"temperatureHighTime\":1573762620,\"temperatureLow\":35.75,\"temperatureLowTime\":1573820820,\"apparentTemperatureHigh\":43.59,\"apparentTemperatureHighTime\":1573761600,\"apparentTemperatureLow\":31.1,\"apparentTemperatureLowTime\":1573820580,\"dewPoint\":37.87,\"humidity\":0.84,\"pressure\":1025.7,\"windSpeed\":4.31,\"windGust\":20.76,\"windGustTime\":1573766400,\"windBearing\":12,\"cloudCover\":0.77,\"uvIndex\":3,\"uvIndexTime\":1573755240,\"visibility\":7.319,\"ozone\":280.5,\"temperatureMin\":38.64,\"temperatureMinTime\":1573736040,\"temperatureMax\":48.19,\"temperatureMaxTime\":1573762620,\"apparentTemperatureMin\":36.45,\"apparentTemperatureMinTime\":1573797600,\"apparentTemperatureMax\":43.59,\"apparentTemperatureMaxTime\":1573761600},{\"time\":1573797600,\"summary\":\"Clear throughout the day.\",\"icon\":\"clear-day\",\"sunriseTime\":1573822680,\"sunsetTime\":1573860960,\"moonPhase\":0.62,\"precipIntensity\":0.0001,\"precipIntensityMax\":0.0006,\"precipIntensityMaxTime\":1573797600,\"precipProbability\":0.05,\"precipType\":\"rain\",\"temperatureHigh\":61.18,\"temperatureHighTime\":1573852200,\"temperatureLow\":34.78,\"temperatureLowTime\":1573906560,\"apparentTemperatureHigh\":60.68,\"apparentTemperatureHighTime\":1573852200,\"apparentTemperatureLow\":35.27,\"apparentTemperatureLowTime\":1573906560,\"dewPoint\":36.17,\"humidity\":0.7,\"pressure\":1029.7,\"windSpeed\":5.24,\"windGust\":20.79,\"windGustTime\":1573809300,\"windBearing\":3,\"cloudCover\":0.01,\"uvIndex\":5,\"uvIndexTime\":1573841700,\"visibility\":10,\"ozone\":281.9,\"temperatureMin\":35.75,\"temperatureMinTime\":1573820820,\"temperatureMax\":61.18,\"temperatureMaxTime\":1573852200,\"apparentTemperatureMin\":31.1,\"apparentTemperatureMinTime\":1573820580,\"apparentTemperatureMax\":60.68,\"apparentTemperatureMaxTime\":1573852200},{\"time\":1573884000,\"summary\":\"Partly cloudy throughout the day.\",\"icon\":\"partly-cloudy-day\",\"sunriseTime\":1573909140,\"sunsetTime\":1573947360,\"moonPhase\":0.65,\"precipIntensity\":0.0001,\"precipIntensityMax\":0.0003,\"precipIntensityMaxTime\":1573926720,\"precipProbability\":0.05,\"precipType\":\"rain\",\"temperatureHigh\":64.16,\"temperatureHighTime\":1573938480,\"temperatureLow\":41.28,\"temperatureLowTime\":1573989300,\"apparentTemperatureHigh\":63.66,\"apparentTemperatureHighTime\":1573938480,\"apparentTemperatureLow\":40.24,\"apparentTemperatureLowTime\":1573988760,\"dewPoint\":37.64,\"humidity\":0.7,\"pressure\":1024.6,\"windSpeed\":3.85,\"windGust\":16.83,\"windGustTime\":1573969560,\"windBearing\":173,\"cloudCover\":0.31,\"uvIndex\":4,\"uvIndexTime\":1573928100,\"visibility\":10,\"ozone\":276.1,\"temperatureMin\":34.78,\"temperatureMinTime\":1573906560,\"temperatureMax\":64.16,\"temperatureMaxTime\":1573938480,\"apparentTemperatureMin\":35.27,\"apparentTemperatureMinTime\":1573906560,\"apparentTemperatureMax\":63.66,\"apparentTemperatureMaxTime\":1573938480},{\"time\":1573970400,\"summary\":\"Partly cloudy throughout the day.\",\"icon\":\"partly-cloudy-day\",\"sunriseTime\":1573995600,\"sunsetTime\":1574033700,\"moonPhase\":0.69,\"precipIntensity\":0.0001,\"precipIntensityMax\":0.0003,\"precipIntensityMaxTime\":1573970400,\"precipProbability\":0.03,\"precipType\":\"rain\",\"temperatureHigh\":66.99,\"temperatureHighTime\":1574025780,\"temperatureLow\":41.45,\"temperatureLowTime\":1574076480,\"apparentTemperatureHigh\":66.49,\"apparentTemperatureHighTime\":1574025780,\"apparentTemperatureLow\":39.35,\"apparentTemperatureLowTime\":1574076720,\"dewPoint\":44.49,\"humidity\":0.74,\"pressure\":1018.7,\"windSpeed\":4.36,\"windGust\":16.75,\"windGustTime\":1573970400,\"windBearing\":292,\"cloudCover\":0.38,\"uvIndex\":4,\"uvIndexTime\":1574014320,\"visibility\":10,\"ozone\":283.2,\"temperatureMin\":41.28,\"temperatureMinTime\":1573989300,\"temperatureMax\":66.99,\"temperatureMaxTime\":1574025780,\"apparentTemperatureMin\":40.24,\"apparentTemperatureMinTime\":1573988760,\"apparentTemperatureMax\":66.49,\"apparentTemperatureMaxTime\":1574025780},{\"time\":1574056800,\"summary\":\"Clear throughout the day.\",\"icon\":\"clear-day\",\"sunriseTime\":1574082060,\"sunsetTime\":1574120100,\"moonPhase\":0.72,\"precipIntensity\":0.0001,\"precipIntensityMax\":0.0003,\"precipIntensityMaxTime\":1574132520,\"precipProbability\":0.02,\"precipType\":\"rain\",\"temperatureHigh\":75.08,\"temperatureHighTime\":1574110380,\"temperatureLow\":50.86,\"temperatureLowTime\":1574162160,\"apparentTemperatureHigh\":74.58,\"apparentTemperatureHighTime\":1574110380,\"apparentTemperatureLow\":51.35,\"apparentTemperatureLowTime\":1574162160,\"dewPoint\":43.95,\"humidity\":0.67,\"pressure\":1014.7,\"windSpeed\":5.2,\"windGust\":11.25,\"windGustTime\":1574056920,\"windBearing\":240,\"cloudCover\":0,\"uvIndex\":5,\"uvIndexTime\":1574100900,\"visibility\":10,\"ozone\":279.9,\"temperatureMin\":41.45,\
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        References for all of my buttons and text views
         */

        currentCond = findViewById(R.id.CurrentConditions);
        showCond = findViewById(R.id.ShowTemperature);
        HourlyTemp = findViewById(R.id.Hour);
        avgTemp = findViewById(R.id.AvgTemp);
        averageTemp = findViewById(R.id.averagetemp);
        UpcomingWeek = findViewById(R.id.PredictTemp);
        showUpcomingWeek = findViewById(R.id.FutureTemp);
        pastText = findViewById(R.id.TextforPast);
        mypastTemp = findViewById(R.id.Temp);
        listOfTemp = findViewById(R.id.tempperHour);


        pastText.setText("January 10th, 2019 at 1:17 AM");             //Displaying the temperature during this date
        mypastTemp.setText("Temp:" + tempPast + " F");


        OkHttpClient client = new OkHttpClient();
        //String longandlatpart = latitude + "," + longitude;



        //String URL = "https://api.darksky.net/forecast/08ad4e9735bb99cff1923c3615e7fde6/" + longandlatpart;

        String url = "https://api.darksky.net/forecast/08ad4e9735bb99cff1923c3615e7fde6/30.369280,-97.745041";
        //url for determining the forecast


        /*
        Making the request to obtain the forecast at a certain location
         */
        Request request = new Request.Builder()
                .url(url)
                .build();

            client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful())
                    myResponse = response.body().string();

            }});



            /*

            pastTemperature.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment datePicker = new DatePickerFragment();
                    datePicker.show(getSupportFragmentManager(), "date picker");
                    long unixTime = convertDatetoUnix();
                    pastForecast = makeRequest(unixTime);
                    getPastTemperature(pastForecast);


                }
            });

            */

        //pastForecast = makeRequest(tempPastURL);
        //getPastTemperature(pastForecast);


    }





    private void getPastTemperature(String pastForecast)
    {
        Double Past;
        String gettheData;
        try{
            JSONObject getData = new JSONObject(pastForecast);
            gettheData = getData.getString("currently");
            JSONObject gettheTemp = new JSONObject(gettheData);
            Past = gettheTemp.getDouble("temperature");
            tempPast = Past;
            mypastTemp.setText("Temp: " + tempPast);



        } catch(Exception e)
        {
            e.printStackTrace();
        }


    }





    private String makeRequest(String URL) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful())
                    PastData = response.body().string();

            }});


        return PastData;

    }


    private long convertDatetoUnix() {
        long dateTime;

        try{
            SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
            Date date = format.parse(currentDateString);
            long timestamp = date.getTime() * 1000;
            dateTime = timestamp;
            return dateTime;



        }catch(Exception e)
        {
            e.printStackTrace();
            dateTime = 0;
        }

        /*
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("your string goes here");
        long timestamp = date.getTime();
        *.

         */

        return dateTime;

    }



    /*
    public String getLongandLat()
    {
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null)
                    locationData = location.toString();
                    latitude = String.valueOf(location.getLatitude());
                    longitude = String.valueOf(location.getLongitude());
                    longandlatpart = latitude + "," + longitude;
                    website = "https://api.darksky.net/forecast/08ad4e9735bb99cff1923c3615e7fde6/" + longandlatpart;

            }});

        return website;

    }

     */



    //Showing the current weather conditions such as the humidity, temperature, wind speed, and the precipitation probability at the current location

    public void showcurrentWeatherConditions(View view)
    {

        String currentTemperature;
        String currenthumidity;


        try
        {
            JSONObject jsonObject= new JSONObject(myResponse);
            String weatherData = jsonObject.getString("currently");
            JSONObject jsonObject2 = new JSONObject(weatherData);

            //Showing the current conditions
            Temperature = jsonObject2.getDouble("temperature");

            humidity = jsonObject2.getDouble("humidity") * 100;

            windSpeed = jsonObject2.getDouble("windSpeed");

            PrecipitationProb = jsonObject2.getDouble("precipProbability");

            showCond.setText("Temperature: " + String.valueOf(Temperature) + " F " + "\n" + "Humidity: " + humidity + "%" + "\n" + "WindSpeed: " + String.valueOf(windSpeed) + " mph" + "\n" + "Precipitation Probability: " + String.valueOf(PrecipitationProb) + "%");

        } catch(Exception e)
        {
           e.printStackTrace();
        }
    }

   //Temperature for the next five hours throughout the day.

    public void temperaturefornextfiveHours(View view)
    {
        try
        {
             JSONObject entireData = new JSONObject(myResponse);
             String hourlyData = entireData.getString("hourly");
             JSONObject weatherData = new JSONObject(hourlyData);
             String data = weatherData.getString("data");
             JSONArray dataperhour = new JSONArray(data);
             listOfTemp.setText(String.valueOf( dataperhour.getJSONObject(0).getDouble("temperature")) + "\n" + String.valueOf( dataperhour.getJSONObject(1).getDouble("temperature") + "\n" +  String.valueOf(dataperhour.getJSONObject(2).getDouble("temperature") + "\n" + String.valueOf( dataperhour.getJSONObject(3).getDouble("temperature") + "\n" + String.valueOf(dataperhour.getJSONObject(3).getDouble("temperature")) + "\n" + String.valueOf(dataperhour.getJSONObject(4).getDouble("temperature"))))));

        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
    }

    //Average temperature for the next 2 days at the current location
    public void averageTemperaturefor48hours(View view)
    {
        Double sumOfTemp = 0.0;
        Double totalNumHours = 48.0;
        Double averageTemperature;


        try
        {
            JSONObject data = new JSONObject(myResponse);
            String TempData = data.getString("hourly");
            JSONObject weatherPerHour = new JSONObject(TempData);
            String hourlyData = weatherPerHour.getString("data");
            JSONArray ListOfTemp = new JSONArray(hourlyData);

            //The ListOfTemp array shows the temperatures for the next 48 hours

            for(int k = 1; k < ListOfTemp.length(); k++)
            {
                JSONObject hourlyTemp = ListOfTemp.getJSONObject(k);
                sumOfTemp = sumOfTemp + hourlyTemp.getDouble("temperature");
            }

            averageTemperature = sumOfTemp / totalNumHours;
            //Log.i("Average Temperature", String.valueOf(averageTemperature));
            averageTemp.setText("Average Temp: " + String.valueOf(averageTemperature));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //This function will show the future temperatures for the next week.
    //Since the temperatures for the next week are categorized as tempHigh and tempLow, I averaged the two of them.
    //Thus, the textview is showing the average temperatures throughout the week.

    public void showFutureTemp(View view)
    {
        try
        {
            JSONObject overallData = new JSONObject(myResponse);
            String upcomingData = overallData.getString("daily");
            JSONObject upcomingTemp = new JSONObject(upcomingData);
            String dailyTemp = upcomingTemp.getString("data");
            JSONArray showListofUpcomingTemp = new JSONArray(dailyTemp);

            //Showing the average temperatures throughout the week
            showUpcomingWeek.setText("Current Average Temperature: " + ((showListofUpcomingTemp.getJSONObject(0).getDouble("temperatureHigh") +
                    showListofUpcomingTemp.getJSONObject(0).getDouble("temperatureLow")) / 2.0) + "\n" +
                    "Average Temperature Throughout the week: " + "\n" +
                    ((showListofUpcomingTemp.getJSONObject(1).getDouble("temperatureHigh") +
                    showListofUpcomingTemp.getJSONObject(1).getDouble("temperatureLow")) / 2.0) + "\n" +
                    ((showListofUpcomingTemp.getJSONObject(2).getDouble("temperatureHigh") + showListofUpcomingTemp.getJSONObject(2).getDouble("temperatureLow")) / 2.0) + "\n" +
                    ((showListofUpcomingTemp.getJSONObject(3).getDouble("temperatureHigh") + showListofUpcomingTemp.getJSONObject(3).getDouble("temperatureLow")) / 2.0) + "\n" +
                    ((showListofUpcomingTemp.getJSONObject(4).getDouble("temperatureHigh") + showListofUpcomingTemp.getJSONObject(4).getDouble("temperatureLow")) / 2.0) + "\n" +
                    ((showListofUpcomingTemp.getJSONObject(5).getDouble("temperatureHigh") + showListofUpcomingTemp.getJSONObject(5).getDouble("temperatureLow")) / 2.0) + "\n" +
                    ((showListofUpcomingTemp.getJSONObject(6).getDouble("temperatureHigh") + showListofUpcomingTemp.getJSONObject(6).getDouble("temperatureLow")) / 2.0) + "\n" +
                    ((showListofUpcomingTemp.getJSONObject(7).getDouble("temperatureHigh") + showListofUpcomingTemp.getJSONObject(7).getDouble("temperatureLow")) / 2.0) + "\n"

            );



        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }








}
