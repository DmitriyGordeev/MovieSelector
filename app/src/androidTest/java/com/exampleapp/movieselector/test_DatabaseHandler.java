package com.exampleapp.movieselector;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class test_DatabaseHandler {

    @Test
    public void simpleDatabaseTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        DataBaseHandler database = new DataBaseHandler(appContext, "test_db");

        Movie movie = new Movie(0, "Title", "imageUrl", "2018");
        database.put(movie);
        ArrayList<Movie> movies = database.get();

        Assert.assertEquals(1, movies.size());

        Movie test = movies.get(0);

        // TODO: refactor
        Assert.assertEquals(movie.getId(), test.getId());
        Assert.assertEquals(movie.getTitle(), test.getTitle());
        Assert.assertEquals(movie.getImageUrl(), test.getImageUrl());
        Assert.assertEquals(movie.getYear(), test.getYear());

        database.remove(test.getId());

        movies.clear();
        movies = database.get();
        Assert.assertTrue(movies.isEmpty());
    }
}
