package com.dmytrobohdanov.testtaskfilemanager.Utils.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dmytrobohdanov.testtaskfilemanager.Utils.Constants;
import com.dmytrobohdanov.testtaskfilemanager.Utils.FileType;
import com.dmytrobohdanov.testtaskfilemanager.Utils.Utils;

import java.util.Date;

/**
 * Database
 */
public class DBHelper extends SQLiteOpenHelper {
    //data table columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FILE_NAME = "fileName";
    public static final String COLUMN_FILE_TYPE = "fileType";
    public static final String COLUMN_MODIFIED_DATE = "date";
    public static final String COLUMN_FOLDER_ID = "folderId";
    public static final String COLUMN_IS_ORANGE = "isOrange";
    public static final String COLUMN_IS_BLUE = "isBlue";
    public static final String COLUMN_IS_FOLDER = "isFolder";
    //table name
    static final String TABLE_DATA = "tableData";
    //db name
    private static final String DATABASE_NAME = "testTaskDB.db";
    //version of db
    private static final int DATABASE_VERSION = 3;
    //table creation script
    private static final String DB_SCRIPT_CREATE_DATA_TABLE = "create table "
            + TABLE_DATA + " ("
            + COLUMN_ID + " integer primary key autoincrement,"
            + COLUMN_FILE_NAME + " text,"
            + COLUMN_FILE_TYPE + " text,"
            + COLUMN_MODIFIED_DATE + " long,"
            + COLUMN_FOLDER_ID + " integer,"
            + COLUMN_IS_ORANGE + " integer,"
            + COLUMN_IS_BLUE + " integer,"
            + COLUMN_IS_FOLDER + " integer);";


    private static DBHelper instance;
    private Context context;

    DBHelper(Context context) {
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

        insertRootFolder(db);
    }

    /**
     * inserting root folder, that keeps all other folders and files
     */
    private void insertRootFolder(SQLiteDatabase db) {
        DataModel rootFolder
                = new DataModel("rootFolder", 1, true, new Date(0), FileType.NONE, false, false);

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.COLUMN_FILE_NAME, rootFolder.getFileName());

        contentValues.put(DBHelper.COLUMN_FILE_TYPE, rootFolder.getFileType().name()); //todo check
        contentValues.put(DBHelper.COLUMN_MODIFIED_DATE, rootFolder.getModifiedDate().getTime());
        contentValues.put(DBHelper.COLUMN_FOLDER_ID, rootFolder.getFolderId());

        if (rootFolder.isOrange()) {
            contentValues.put(DBHelper.COLUMN_IS_ORANGE, Constants.INT_TRUE);
        } else {
            contentValues.put(DBHelper.COLUMN_IS_ORANGE, Constants.INT_FALSE);
        }

        if (rootFolder.isBlue()) {
            contentValues.put(DBHelper.COLUMN_IS_BLUE, Constants.INT_TRUE);
        } else {
            contentValues.put(DBHelper.COLUMN_IS_BLUE, Constants.INT_FALSE);
        }

        if (rootFolder.isFolder()) {
            contentValues.put(DBHelper.COLUMN_IS_FOLDER, Constants.INT_TRUE);
        } else {
            contentValues.put(DBHelper.COLUMN_IS_FOLDER, Constants.INT_FALSE);
        }

        db.insert(TABLE_DATA, null, Utils.getContentValuesFromDataModel(rootFolder));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(db);
    }
}
