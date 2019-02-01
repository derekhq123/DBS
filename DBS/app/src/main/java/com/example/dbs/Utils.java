package com.example.dbs;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class Utils {

    public static final String UTILS_TAG = "UtilsTag";

    public static InputStream getInputStream(URL url){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        InputStream inputStream = null;
/*
        String userCredentials = "Y1:6bf230e0-e61d-a5c5-522b3e970530";
        String basicAuth = "Basic " + new String(java.util.Base64.getEncoder().encode(userCredentials.getBytes()));
         urlConnection.setRequestProperty ("Authorization", basicAuth);
        */

        try{
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("identity", "Y1");
            urlConnection.setRequestProperty("token", "6bf230e0-e61d-a5c5-522b3e970530");
            urlConnection.setDoInput(true);
            urlConnection.connect();


            // Request not successful
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Request Failed. HTTP Error Code: " + urlConnection.getResponseCode());
            }

            inputStream = urlConnection.getInputStream();

        }catch(IOException e) {
            e.printStackTrace();
            inputStream = null;
        }

        return inputStream;

    }

    public static String getJson(URL url){

        return convertStreamToString(getInputStream(url));
    }

    public static String convertStreamToString(InputStream inputStream){

        BufferedReader reader = null;
        String outString;

        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
            // Nothing to do.
            return null;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try{
            while ((line = reader.readLine()) != null) {
   /* Since it's JSON, adding a newline isn't necessary (it won't affect
      parsing) but it does make debugging a *lot* easier if you print out the
      completed buffer for debugging. */
                buffer.append(line + "\n");
            }

        } catch( IOException e){
            e.printStackTrace();

        }
        if (buffer.length() == 0) {
            // Stream was empty.  No point in parsing.
            return null;
        }
        outString = buffer.toString();
        Log.i(UTILS_TAG,outString);
        return outString;

    }





    public static Bitmap convertStreamToBitmap (InputStream inputStream){

        return BitmapFactory.decodeStream(inputStream);

    }

    public static Bitmap getBitmap(URL url){

        return convertStreamToBitmap(getInputStream(url));
    }


}