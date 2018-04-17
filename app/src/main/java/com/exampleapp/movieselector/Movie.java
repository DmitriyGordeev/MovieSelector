package com.exampleapp.movieselector;


public class Movie {

    private String title;
    private String imageUrl;
    private int year;


    public Movie(String title, String imageUrl, int year) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.year = year;
    }

    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public int getYear() { return year; }

}
