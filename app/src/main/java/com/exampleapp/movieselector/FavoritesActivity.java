package com.exampleapp.movieselector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    ListView movieList;
    MovieAdapter movieAdapter;
    DataBaseHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        database = new DataBaseHandler(this, "movies_database");

        // TODO: check database errors
        ArrayList<Movie> movies = database.get();


        movieAdapter = new MovieAdapter(this, R.layout.listitem_movie, movies, true);

        movieList = (ListView) findViewById(R.id.favoriteList);
        movieList.setAdapter(movieAdapter);
    }
}
