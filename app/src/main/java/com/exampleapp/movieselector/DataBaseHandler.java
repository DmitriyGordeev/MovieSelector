package com.exampleapp.movieselector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    private String db_name;
    private final static String LOGTAG = "[DataBaseHandler]";

    public DataBaseHandler(Context context, String name) {
        super(context, name, null, 1);
        db_name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // TODO: refactor field types:
        db.execSQL("create table movies (" +
                "id integer primary key autoincrement," +
                "title text, " +
                "year text," +
                "poster text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void put(Movie movie) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("title", movie.getTitle());
        cv.put("year", movie.getYear());
        cv.put("poster", movie.getImageUrl());

        db.insert("movies", null, cv);
        db.close();
    }

    public ArrayList<Movie> get() {

        ArrayList<Movie> output = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("movies", null, null, null, null, null, null);

        if(c.moveToFirst())
        {
            int titleIndex = c.getColumnIndex("title");
            int yearIndex = c.getColumnIndex("year");
            int posterIndex = c.getColumnIndex("poster");

            do {
                Movie m = new Movie(c.getString(titleIndex), c.getString(yearIndex), c.getString(posterIndex));
                output.add(m);
            }
            while (c.moveToNext());
        }

        return output;
    }

    public boolean remove(int index) {
        SQLiteDatabase db = getReadableDatabase();
        return db.delete("movies", "id=" + index, null) > 0;
    }

}
