package com.exampleapp.movieselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView movieList;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Back to the Future", "url 1", 2001));
        movies.add(new Movie("Back to the Future II", "url 2", 2002));
        movies.add(new Movie("Back to the Future III", "url 3", 2003));


        movieAdapter = new MovieAdapter(this, R.layout.listitem_movie, movies);

        movieList = (ListView) findViewById(R.id.moviesList);
        movieList.setAdapter(movieAdapter);

    }

    public void onFavorites(View view) {
        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
        startActivity(intent);
    }
}
