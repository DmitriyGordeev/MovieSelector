package com.exampleapp.movieselector;

import android.util.Log;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class test_HttpAsync {

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

        return responseBuffer.toString();
    }

    @Test
    public void connectionIsOk() throws MalformedURLException, IOException {

        URL url;
        HttpURLConnection urlConnection;

        url = new URL("https://www.omdbapi.com/?apikey=69d51abd&t=back");
        urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        String response = readStream(in);
        urlConnection.disconnect();

        System.out.println(response);

    }

}
