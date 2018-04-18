package com.exampleapp.movieselector;


import android.os.AsyncTask;
import android.util.Log;

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
    AsyncResponse delegate;

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

            return response;
        }
        catch(MalformedURLException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

//        connection.disconnect();
        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }


    private String readStream(InputStream input) {

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


    public String getResponse() {
        return response;
    }

}
