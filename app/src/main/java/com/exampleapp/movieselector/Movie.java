package com.exampleapp.movieselector;


public class Movie {

    private int id;
    private String title;
    private String imageUrl;
    private String year;


    public Movie(int id, String title, String imageUrl, String year) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.year = year;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public String getYear() { return year; }

}
