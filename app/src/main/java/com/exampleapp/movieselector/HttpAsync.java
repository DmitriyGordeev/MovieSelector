package com.exampleapp.movieselector;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpAsync extends AsyncTask<String, Integer, String> {

    String response;

    @Override
    protected String doInBackground(String ... urls) {

        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                response = readStream(connection.getInputStream());
            }
        }
        catch(MalformedURLException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    private String readStream(InputStream input) {

        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();

        try {
            reader = new BufferedReader(new InputStreamReader(input));
            String line = "";
            while((line = reader.readLine()) != null) {
                response.append(line + "\n");
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

        return response.toString();
    }

}
