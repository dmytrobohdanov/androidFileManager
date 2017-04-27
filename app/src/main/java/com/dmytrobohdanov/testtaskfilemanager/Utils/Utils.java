package com.dmytrobohdanov.testtaskfilemanager.Utils;


import android.content.ContentValues;
import android.database.Cursor;

import com.dmytrobohdanov.testtaskfilemanager.Utils.Database.DBHelper;
import com.dmytrobohdanov.testtaskfilemanager.Utils.Database.DataModel;

import java.util.Date;

public class Utils {
    public static ContentValues getContentValuesFromDataModel(DataModel dataModel) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.COLUMN_ID, dataModel.getId());
        contentValues.put(DBHelper.COLUMN_FILE_NAME, dataModel.getFileName());

        contentValues.put(DBHelper.COLUMN_FILE_TYPE, dataModel.getFileType().name()); //todo check
        contentValues.put(DBHelper.COLUMN_MODIFIED_DATE, dataModel.getModifiedDate().getTime());
        contentValues.put(DBHelper.COLUMN_FOLDER_ID, dataModel.getFolderId());

        if (dataModel.isOrange()) {
            contentValues.put(DBHelper.COLUMN_IS_ORANGE, Constants.INT_TRUE);
        } else {
            contentValues.put(DBHelper.COLUMN_IS_ORANGE, Constants.INT_FALSE);
        }

        if (dataModel.isBlue()) {
            contentValues.put(DBHelper.COLUMN_IS_BLUE, Constants.INT_TRUE);
        } else {
            contentValues.put(DBHelper.COLUMN_IS_BLUE, Constants.INT_FALSE);
        }

        if (dataModel.isFolder()) {
            contentValues.put(DBHelper.COLUMN_IS_FOLDER, Constants.INT_TRUE);
        } else {
            contentValues.put(DBHelper.COLUMN_IS_FOLDER, Constants.INT_FALSE);
        }

        return contentValues;
    }

    /**
     * Extract DataModel from Cursor
     */
    public static DataModel getDataModelFromCursor(Cursor cursor) {
        int dataModelId = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
        String fileName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_FILE_NAME));
        String fileType = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_FILE_TYPE));
        long modDate = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_MODIFIED_DATE));
        int folderId = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_FOLDER_ID));

        int isOrange = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_IS_ORANGE));
        int isBlue = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_IS_BLUE));
        int isFolder = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_IS_FOLDER));


        return new DataModel(dataModelId, fileName, folderId,
                isFolder == Constants.INT_TRUE,
                new Date(modDate),
                FileType.valueOf(fileType),
                isOrange == Constants.INT_TRUE,
                isBlue == Constants.INT_TRUE);
    }
}
