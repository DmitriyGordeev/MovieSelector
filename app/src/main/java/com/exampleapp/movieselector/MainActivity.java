package com.exampleapp.movieselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    ListView movieList;
    MovieAdapter movieAdapter;
    HttpAsync httpAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Back to the Future", "url 1", "2001"));
        movies.add(new Movie("Back to the Future II", "url 2", "2002"));
        movies.add(new Movie("Back to the Future III", "url 3", "2003"));


        movieAdapter = new MovieAdapter(this, R.layout.listitem_movie, movies);
        movieList = (ListView) findViewById(R.id.moviesList);
        movieList.setAdapter(movieAdapter);


        httpAsync = new HttpAsync();
        httpAsync.delegate = this;
        httpAsync.execute("https://www.omdbapi.com/?apikey=69d51abd&t=back");

    }

    public void onFavorites(View view) {
        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
        startActivity(intent);
    }

    @Override
    public void processFinish(String response) {

        // String response = httpAsync.getResponse();
        Log.i("DELEGATE", response);

    }

}
