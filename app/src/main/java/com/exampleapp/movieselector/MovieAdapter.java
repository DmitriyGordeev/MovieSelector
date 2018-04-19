package com.exampleapp.movieselector;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class RequestImage {

    public static Bitmap request(String requestUrl) {

        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);

            Bitmap bmp = BitmapFactory.decodeStream(bis);
            return bmp;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            if(connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }
}

public class MovieAdapter extends ArrayAdapter<Movie> {

    DataBaseHandler database;
    Context context;
    boolean rmMode;
    List<Movie> movies;

    public MovieAdapter(Context context, int resource, List<Movie> items, boolean rmMode) {
        super(context, resource, items);
        database = new DataBaseHandler(context, "movies_database");
        this.context = context;

        this.rmMode = rmMode;
        movies = items;

        // TODO: check database status
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listitem_movie, null);
        }

        final Movie m = getItem(position);
        if (m != null) {

            TextView tv_movieTitle = (TextView) v.findViewById(R.id.tv_movieTitle);
            TextView tv_movieYear = (TextView) v.findViewById(R.id.tv_movieYear);
            ImageView iv_moviePoster = (ImageView) v.findViewById(R.id.iv_moviePoster);
            ImageButton btn_addToFavorites = (ImageButton) v.findViewById(R.id.btn_addToFavorites);

            // download image:
            Observable.fromCallable(() -> {
                return RequestImage.request(m.getImageUrl());
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((response) -> {
                        if(response != null) {
                            iv_moviePoster.setImageBitmap(response);
                        }
                    });

            if(rmMode) {
                btn_addToFavorites.setImageResource(android.R.drawable.ic_menu_delete);
            }

            if(tv_movieTitle != null) {
                tv_movieTitle.setText(m.getTitle());
            }

            if(tv_movieYear != null) {
                tv_movieYear.setText(m.getYear());
            }

            if(btn_addToFavorites != null) {
                btn_addToFavorites.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if(rmMode) {
                            database.remove(m.getId());
                            movies.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Удалено из избранного", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            database.put(m);
                            Toast.makeText(context, "Добавлено в избранное", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        }

        return v;
    }

}
