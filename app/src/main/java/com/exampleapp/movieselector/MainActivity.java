package com.exampleapp.movieselector;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Handler;
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

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ListView movieList;
    MovieAdapter movieAdapter;
    ArrayList<Movie> movies;

    String requestUrl;

    @Inject
    DataBaseHandler database;

    Handler handler;
    String searchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestUrl = "https://www.omdbapi.com/?apikey=69d51abd&s=";
        movies = new ArrayList<>();

        // database = new DataBaseHandler(this, "movies_database");
        ((App)getApplicationContext()).getComponent().inject(this);

        handler = new Handler();
    }

    public void onFavorites(View view) {
        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
        startActivity(intent);
    }

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

                searchQuery = s;
                handler.removeCallbacksAndMessages(null);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        searchQuery = searchQuery.replace(" ", "_");
                        if(!searchQuery.isEmpty()) {

                            Observable.fromCallable(() -> {
                                return RequestMovies.request(requestUrl + searchQuery);
                            })
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe((response) -> {
                                        processFinish(response);
                                    });
                        }
                    }
                }, 700);



                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
