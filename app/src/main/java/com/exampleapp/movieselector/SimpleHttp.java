package com.exampleapp.movieselector;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleHttp {

    public static String request(String urlString) throws IOException {

        URL url;
        HttpURLConnection urlConnection;

//        url = new URL("https://www.omdbapi.com/?apikey=69d51abd&t=back");

        url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();

        String response = "";
        if(urlConnection.getResponseCode() == 200) {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            response = readStream(in);
            Log.i("connection status", Integer.toString(urlConnection.getResponseCode()));
        }

        urlConnection.disconnect();
        Log.i("response", response);
        return response;
    }

    private static String readStream(InputStream input) {

        BufferedReader reader = null;
        StringBuffer responseBuffer = new StringBuffer();

        try {
            reader = new BufferedReader(new InputStreamReader(input));
            String line = "";
            while((line = reader.readLine()) != null) {
                responseBuffer.append(line + "\n");
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        finally {
            if(reader != null) {
                try {
                    reader.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.i("responseBuffer", responseBuffer.toString());
        return responseBuffer.toString();
    }

}
