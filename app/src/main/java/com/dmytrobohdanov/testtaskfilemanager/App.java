package com.dmytrobohdanov.testtaskfilemanager;


import android.app.Application;

import com.dmytrobohdanov.testtaskfilemanager.Utils.Database.DBHandler;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBHandler.getInstance(getApplicationContext());
    }
}
