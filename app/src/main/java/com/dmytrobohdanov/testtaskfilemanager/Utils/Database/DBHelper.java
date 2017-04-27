package com.dmytrobohdanov.testtaskfilemanager.Utils.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database
 */
public class DBHelper extends SQLiteOpenHelper {
    //db name
    private static final String DATABASE_NAME = "testTaskDB.db";

    //version of db
    private static final int DATABASE_VERSION = 1;

    //table name
    static final String TABLE_DATA = "tableData";

    //data table columns
    static final String COLUMN_ID = "id";
    static final String COLUMN_FILE_NAME = "fileName";
    static final String COLUMN_FILE_TYPE = "fileType";
    static final String COLUMN_DATE = "date";
    static final String COLUMN_IS_ORANGE = "isOrange";
    static final String COLUMN_IS_BLUE = "isBlue";
    static final String COLUMN_IS_FILE = "isFile";

    //table creation script
    private static final String DB_SCRIPT_CREATE_DATA_TABLE = "create table "
            + TABLE_DATA + " ("
            + COLUMN_ID + " integer primary key autoincrement,"
            + COLUMN_FILE_NAME + " text,"
            + COLUMN_FILE_TYPE + " text,"
            + COLUMN_DATE + " text,"
            + COLUMN_IS_ORANGE + " integer,"
            + COLUMN_IS_BLUE + " integer,"
            + COLUMN_IS_FILE + " integer);";


    private static DBHelper instance;
    private Context context;

    DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_SCRIPT_CREATE_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(db);
    }
}
