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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;

    public DownloadImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String...urls) {
        String url = urls[0];
        Bitmap image = null;

        Log.i("downloading image", url);

        try {
            InputStream input = new URL(url).openStream();
            image = BitmapFactory.decodeStream(input);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    protected void onPostExecute(Bitmap result) {
        if(result != null) {
            imageView.setImageBitmap(result);
        }
    }
}

public class MovieAdapter extends ArrayAdapter<Movie> {

    DataBaseHandler database;
    boolean rmMode;
    List<Movie> movies;

    public MovieAdapter(Context context, int resource, List<Movie> items, boolean rmMode) {
        super(context, resource, items);
        database = new DataBaseHandler(context, "movies_database");

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
            Button btn_addToFavorites = (Button) v.findViewById(R.id.btn_addToFavorites);

            new DownloadImageTask(iv_moviePoster).execute(m.getImageUrl());

            if(rmMode) {
                btn_addToFavorites.setText("Remove");
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
                        }
                        else {
                            database.put(m);
                        }
                    }
                });
            }

        }

        return v;
    }

}
