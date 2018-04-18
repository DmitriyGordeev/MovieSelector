package com.exampleapp.movieselector;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_parseMovies() throws JSONException {

        String json =
                "{\n" +
                "    \"Search\": [\n" +
                "        {\n" +
                "            \"Title\": \"Star Wars: Episode V - The Empire Strikes Back\",\n" +
                "            \"Year\": \"1980\",\n" +
                "            \"imdbID\": \"tt0080684\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://ia.media-imdb.com/images/M/MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg\"\n" +
                "        },\n" +
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
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Jay and Silent Bob Strike Back\",\n" +
                "            \"Year\": \"2001\",\n" +
                "            \"imdbID\": \"tt0261392\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://ia.media-imdb.com/images/M/MV5BY2EyYWEwZmQtZWU0Yy00M2Y3LThiZTktOTQxZDUxY2ZjOTYwXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"The Way Way Back\",\n" +
                "            \"Year\": \"2013\",\n" +
                "            \"imdbID\": \"tt1727388\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://ia.media-imdb.com/images/M/MV5BNTU5ODk5NDg0Nl5BMl5BanBnXkFtZTcwNzQwMjI1OQ@@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Jack Reacher: Never Go Back\",\n" +
                "            \"Year\": \"2016\",\n" +
                "            \"imdbID\": \"tt3393786\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BODQ3ODQ3NDI4NV5BMl5BanBnXkFtZTgwMDY1Mzk5OTE@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"The Way Back\",\n" +
                "            \"Year\": \"2010\",\n" +
                "            \"imdbID\": \"tt1023114\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BMTg5MTc5MTM3Ml5BMl5BanBnXkFtZTcwMDI2NzgwNA@@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"Never Back Down\",\n" +
                "            \"Year\": \"2008\",\n" +
                "            \"imdbID\": \"tt1023111\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://ia.media-imdb.com/images/M/MV5BMTkzNDg3MTIyMF5BMl5BanBnXkFtZTcwOTAwNDc1MQ@@._V1_SX300.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Title\": \"The Back-up Plan\",\n" +
                "            \"Year\": \"2010\",\n" +
                "            \"imdbID\": \"tt1212436\",\n" +
                "            \"Type\": \"movie\",\n" +
                "            \"Poster\": \"https://ia.media-imdb.com/images/M/MV5BNjAzODM4NTc1OF5BMl5BanBnXkFtZTcwNDU2ODUzMw@@._V1_SX300.jpg\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"totalResults\": \"2806\",\n" +
                "    \"Response\": \"True\"\n" +
                "}";


        ArrayList<Movie> movies = MovieParser.parse(json);
        Assert.assertFalse(movies.isEmpty());
    }
}