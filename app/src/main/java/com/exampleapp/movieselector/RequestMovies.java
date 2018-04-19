package com.exampleapp.movieselector;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RequestMovies {

    private String response;

    public String request(String urlString) {

        Observable.fromCallable(() -> {
            URL url;
            HttpURLConnection connection = null;

            try {
                url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    response = readStream(connection.getInputStream());
                }

                if (connection != null) {
                    connection.disconnect();
                }
            }

            // perform catch in subscribe method ?
            catch(MalformedURLException e) {
                e.printStackTrace();
            }
            catch(IOException e) {
                e.printStackTrace();
            }

            return response;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    response = result;
                });

        return response;
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

        return responseBuffer.toString();
    }

}
