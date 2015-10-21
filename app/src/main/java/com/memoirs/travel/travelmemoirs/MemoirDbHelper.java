package com.memoirs.travel.travelmemoirs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mat on 20.10.2015..
 */
public class MemoirDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "travelmemoirs.db";

    public MemoirDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //SQL upit za kreiranje lokalne baze memoara
        final String SQL_CREATE_MEMOIR_TABLE="CREATE TABLE" + MemoirContract.MemoirEntry.TABLE_NAME + " (" +
                MemoirContract.MemoirEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MemoirContract.MemoirEntry.COLUMN_THUMBNAIL + "TEXT UNIQUE NOT NULL, " +
                MemoirContract.MemoirEntry.COLUMN_TITLE + "TEXT NOT NULL, " +
                MemoirContract.MemoirEntry.COLUMN_DESCRIPTION + "TEXT, " +
                MemoirContract.MemoirEntry.COLUMN_DATE + "DATE, " +
                MemoirContract.MemoirEntry.COLUMN_RATING + "FLOAT DEFAULT 0, " +
                MemoirContract.MemoirEntry.COLUMN_CITY + "TEXT NOT NULL, " +
                MemoirContract.MemoirEntry.COLUMN_COUNTRY + "TEXT NOT NULL, " +
                " );";

        db.execSQL(SQL_CREATE_MEMOIR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MemoirContract.MemoirEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
