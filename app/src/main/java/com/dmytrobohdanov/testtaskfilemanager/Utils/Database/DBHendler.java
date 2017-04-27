package com.dmytrobohdanov.testtaskfilemanager.Utils.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Contains methods to work with DB
 */
public class DBHendler {
    private static DBHendler instance;
    private SQLiteDatabase database;

    private DBHendler (Context context){
        database = new DBHelper(context).getWritableDatabase();
    }

    public static DBHendler getInstance(Context context){
        if (instance == null) {
            instance = new DBHendler(context);
        }
        return instance;
    }

}
