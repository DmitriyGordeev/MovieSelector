package com.exampleapp.movieselector;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Context context, int resource, List<Movie> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listitem_movie, null);
        }

        Movie m = getItem(position);
        if (m != null) {

            TextView tv_movieTitle = (TextView)v.findViewById(R.id.tv_movieTitle);
            TextView tv_movieYear = (TextView)v.findViewById(R.id.tv_movieYear);

            if(tv_movieTitle != null) {
                tv_movieTitle.setText(m.getTitle());
            }

            if(tv_movieYear != null) {
                tv_movieTitle.setText(Integer.toString(m.getYear()));
            }

        }

        return v;
    }

}
