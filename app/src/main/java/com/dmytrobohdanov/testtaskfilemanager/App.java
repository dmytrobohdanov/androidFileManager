package com.dmytrobohdanov.testtaskfilemanager;


import android.app.Application;

import com.dmytrobohdanov.testtaskfilemanager.Utils.Database.DBHandler;
import com.dmytrobohdanov.testtaskfilemanager.Utils.Database.DataModel;
import com.dmytrobohdanov.testtaskfilemanager.Utils.FileType;

import java.util.Date;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //todo do in background
        DBHandler handler = DBHandler.getInstance(getApplicationContext());

        //adding some data to DB if there is nothing there
//        if (handler.getFilesByFolderId(0).isEmpty()) {
        addSomeData(handler);
//        }
    }

    private void addSomeData(DBHandler handler) {
        int firstFolderId = handler.addDataToDB(new DataModel("first folder", 0, true, new Date(2017, 11, 12), FileType.NONE, true, true));
        int secondFolderId = handler.addDataToDB(new DataModel("second  folder", 0, true, new Date(2017, 11, 12), FileType.NONE, false, true));
        int thirdFolderId = handler.addDataToDB(new DataModel("third folder", 0, true, new Date(2017, 11, 12), FileType.NONE, true, false));

        handler.addDataToDB(new DataModel("Movie 0", 0, true, new Date(2017, 11, 12), FileType.MOVIE, true, true));
        handler.addDataToDB(new DataModel("PDF 0", 0, true, new Date(2017, 11, 12), FileType.PDF, true, false));
        handler.addDataToDB(new DataModel("MUSIC 0", 0, true, new Date(2017, 11, 12), FileType.MUSIC, false, false));

        handler.addDataToDB(new DataModel("Movie 1", firstFolderId, true, new Date(2017, 11, 12), FileType.MOVIE, true, true));
        handler.addDataToDB(new DataModel("PDF 1", firstFolderId, true, new Date(2017, 11, 12), FileType.PDF, true, false));
        handler.addDataToDB(new DataModel("MUSIC 1", firstFolderId, true, new Date(2017, 11, 12), FileType.MUSIC, false, false));

        handler.addDataToDB(new DataModel("Movie 2", secondFolderId, true, new Date(2017, 11, 12), FileType.MOVIE, true, true));
        handler.addDataToDB(new DataModel("PDF 2", secondFolderId, true, new Date(2017, 11, 12), FileType.PDF, true, false));
        handler.addDataToDB(new DataModel("MUSIC 2", secondFolderId, true, new Date(2017, 11, 12), FileType.MUSIC, false, false));

        handler.addDataToDB(new DataModel("Movie 3", thirdFolderId, true, new Date(2017, 11, 12), FileType.MOVIE, true, true));
        handler.addDataToDB(new DataModel("PDF 3", thirdFolderId, true, new Date(2017, 11, 12), FileType.PDF, true, false));
        handler.addDataToDB(new DataModel("MUSIC 3", thirdFolderId, true, new Date(2017, 11, 12), FileType.MUSIC, false, false));
    }
}
