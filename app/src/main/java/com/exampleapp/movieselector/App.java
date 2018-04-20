package com.exampleapp.movieselector;

import android.app.Application;

public class App extends Application {

    private Component component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerComponent.builder()
                .dataBaseHandlerModule(new DataBaseHandlerModule(getApplicationContext(), DBInfo.databaseName))
                .build();
    }

    public Component getComponent() {
        return component;
    }
}
