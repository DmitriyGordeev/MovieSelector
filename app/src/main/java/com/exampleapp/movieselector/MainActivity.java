package com.exampleapp.movieselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    ListView movieList;
    MovieAdapter movieAdapter;
    ArrayList<Movie> movies;
    HttpAsync httpAsync;

    String requestUrl;

    DataBaseHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestUrl = "https://www.omdbapi.com/?apikey=69d51abd&s=";
        database = new DataBaseHandler(this, "movies_database");
        movies = new ArrayList<>();

    }

    public void onFavorites(View view) {
        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
        startActivity(intent);
    }

    @Override
    public void processFinish(String response) {

        if(response == null) {
            return;
        }

        try {
            movies = MovieParser.parse(response);

            movieAdapter = new MovieAdapter(this, R.layout.listitem_movie, movies, false);
            movieList = (ListView) findViewById(R.id.moviesList);
            movieList.setAdapter(movieAdapter);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if(!s.isEmpty()) {
                    httpAsync = new HttpAsync();
                    httpAsync.delegate = MainActivity.this;
                    httpAsync.execute(requestUrl + s);
                }

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
