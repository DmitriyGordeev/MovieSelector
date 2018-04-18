package com.exampleapp.movieselector;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieParser {

    public static ArrayList<Movie> parse(String json) throws JSONException {

        ArrayList<Movie> output = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("Search");

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject movieObject = (JSONObject) jsonArray.get(i);
            output.add(new Movie(0,
                    movieObject.getString("Title"),
                    movieObject.getString("Poster"),
                    movieObject.getString("Year")));
        }

        return output;
    }

}
