package com.exampleapp.movieselector;

import android.app.Application;

public class App extends Application {

    private Component component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerComponent.builder()
                .dataBaseHandlerModule(new DataBaseHandlerModule(getApplicationContext(), "movies_database"))
                .build();
    }

    public Component getComponent() {
        return component;
    }
}
