package com.exampleapp.movieselector;

import org.json.JSONException;
import org.junit.Test;

import java.util.ArrayList;

public class test_MovieParser {

    @Test
    public void jsonParsedSucessfully() throws JSONException {


        String json =
                "{\n" +
                "    \"Search\": [\n" +
                "        {\n" +
                "            \"Title\": \"Back to the Future\",\n" +
                "            \"Year\": \"1985\",\n" +
                "            \"imdbID\": \"tt0088763\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://ia.media-imdb.com/images/M/MV5BZmU0M2Y1OGUtZjIxNi00ZjBkLTg1MjgtOWIyNThiZWIwYjRiXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Back to the Future Part II\",\n" +
                "            \"Year\": \"1989\",\n" +
                "            \"imdbID\": \"tt0096874\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://ia.media-imdb.com/images/M/MV5BZTMxMGM5MjItNDJhNy00MWI2LWJlZWMtOWFhMjI5ZTQwMWM3XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Back to the Future Part III\",\n" +
                "            \"Year\": \"1990\",\n" +
                "            \"imdbID\": \"tt0099088\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://ia.media-imdb.com/images/M/MV5BYjhlMGYxNmMtOWFmMi00Y2M2LWE5NWYtZTdlMDRlMGEzMDA3XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"totalResults\": \"48\",\n" +
                "    \"Response\": \"True\"\n" +
                "}";

        ArrayList<Movie> movies = MovieParser.parse(json);



    }
}
