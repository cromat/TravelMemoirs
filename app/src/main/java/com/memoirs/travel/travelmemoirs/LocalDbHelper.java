package com.memoirs.travel.travelmemoirs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mat on 20.10.2015..
 */
public class LocalDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "travelmemoirs.db";

    public LocalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //SQL upit za kreiranje lokalne baze draftova
        final String SQL_CREATE_DRAFT_TABLE="CREATE TABLE" + LocalContract.DraftEntry.TABLE_NAME_DRAFTS + " (" +
                LocalContract.DraftEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LocalContract.DraftEntry.COLUMN_THUMBNAIL + "TEXT UNIQUE NOT NULL, " +
                LocalContract.DraftEntry.COLUMN_TITLE + "TEXT NOT NULL, " +
                LocalContract.DraftEntry.COLUMN_DESCRIPTION + "TEXT, " +
                LocalContract.DraftEntry.COLUMN_CITY + "TEXT NOT NULL, " +
                LocalContract.DraftEntry.COLUMN_COUNTRY + "TEXT NOT NULL, " +
                " );";

        final String SQL_CREATE_DRAFT_PICTURES="CREATE TABLE" + LocalContract.DraftEntry.TABLE_NAME_DRAFT_PICTURES + " (" +
                LocalContract.DraftEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LocalContract.DraftEntry.COLUMN_DRAFT_ID + " INTEGER," +
                LocalContract.DraftEntry.COLUMN_PICTURE_PATH + "TEXT UNIQUE NOT NULL, " +
                "FOREIGN KEY (" + LocalContract.DraftEntry.COLUMN_DRAFT_ID +") REFERENCES " +
                LocalContract.DraftEntry.TABLE_NAME_DRAFTS + "(" + LocalContract.DraftEntry._ID + ")" +
                " );";

        db.execSQL(SQL_CREATE_DRAFT_TABLE);
        db.execSQL(SQL_CREATE_DRAFT_PICTURES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LocalContract.DraftEntry.TABLE_NAME_DRAFTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LocalContract.DraftEntry.TABLE_NAME_DRAFT_PICTURES);
        onCreate(sqLiteDatabase);
    }
}
