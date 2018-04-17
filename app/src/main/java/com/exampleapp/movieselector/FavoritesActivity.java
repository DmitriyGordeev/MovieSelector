package com.exampleapp.movieselector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    ListView movieList;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Back to the Future", "url 1", 2001));
        movies.add(new Movie("Back to the Future II", "url 2", 2002));
        movies.add(new Movie("Back to the Future III", "url 3", 2003));


        movieAdapter = new MovieAdapter(this, R.layout.listitem_movie, movies);

        movieList = (ListView) findViewById(R.id.favoriteList);
        movieList.setAdapter(movieAdapter);
    }
}
