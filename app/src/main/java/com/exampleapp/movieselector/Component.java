package com.exampleapp.movieselector;

@dagger.Component(modules = DataBaseHandlerModule.class)
public interface Component {
    void inject(MainActivity mainActivity);
}
