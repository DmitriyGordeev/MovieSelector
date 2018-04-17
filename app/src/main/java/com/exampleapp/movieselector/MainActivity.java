package com.exampleapp.movieselector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ListView movieList;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Back to the Future", "url 1", 1985));
        movies.add(new Movie("Back to the Future II", "url 2", 1986));
        movies.add(new Movie("Back to the Future III", "url 3", 1991));


        movieAdapter = new MovieAdapter(this, R.layout.listitem_movie, movies);

        movieList = (ListView) findViewById(R.id.moviesList);
        movieList.setAdapter(movieAdapter);

    }
}
