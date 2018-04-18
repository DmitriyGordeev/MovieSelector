package com.exampleapp.movieselector;


public class Movie {

    private String title;
    private String imageUrl;
    private String year;


    public Movie(String title, String imageUrl, String year) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.year = year;
    }

    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public String getYear() { return year; }

}
