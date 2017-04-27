package com.dmytrobohdanov.testtaskfilemanager.Utils.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dmytrobohdanov.testtaskfilemanager.Utils.Utils;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Contains methods to work with DB
 */
public class DBHandler {
    private static DBHandler instance;
    private SQLiteDatabase database;

    private DBHandler(Context context) {
        database = new DBHelper(context).getWritableDatabase();
    }

    public static DBHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DBHandler(context);
        }
        return instance;
    }

    /**
     * Getting all files from specified folder
     *
     * @param folderId id of folder to get files from
     * @return List of DataModel's instances
     */
    public ArrayList<DataModel> getFilesByFolderId(int folderId) {
        LinkedList<DataModel> dataModels = new LinkedList<>();

        String query = "select * from "
                + DBHelper.TABLE_DATA
                + " where "
                + DBHelper.COLUMN_FOLDER_ID
                + " = "
                + folderId;

        Cursor cursor = database.rawQuery(query, null);

        //getting all data from folder
        while (cursor.moveToNext()) {
            dataModels.add(Utils.getDataModelFromCursor(cursor));
        }

        cursor.close();

        return new ArrayList<>(dataModels);
    }


    /**
     * Adding data to database
     *
     * @param dataModel instance of DataModel with data to add
     * @return id of inserted data
     */
    public int addDataToDB(DataModel dataModel) {
        //get values for fields
        ContentValues value = Utils.getContentValuesFromDataModel(dataModel);

        //add to DB
        return (int) database.insert(DBHelper.TABLE_DATA, null, value);
    }
}
