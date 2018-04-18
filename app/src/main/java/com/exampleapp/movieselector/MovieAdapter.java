package com.exampleapp.movieselector;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    DataBaseHandler database;
    boolean rmMode;

    public MovieAdapter(Context context, int resource, List<Movie> items, boolean rmMode) {
        super(context, resource, items);
        database = new DataBaseHandler(context, "movies_database");

        this.rmMode = rmMode;

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

                        Log.i("[adapter list position]", Integer.toString(position));

                        if(rmMode) {
                            database.remove(m.getId());
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
