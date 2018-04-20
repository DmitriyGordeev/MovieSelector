package com.exampleapp.movieselector;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseHandlerModule {

    private Context context;
    private String name;

    public DataBaseHandlerModule(Context context, String name) {
        this.context = context;
        this.name = name;
    }

    @Provides
    DataBaseHandler provideDatabaseHandler() {
        return new DataBaseHandler(context, name); // ?
    }
}
