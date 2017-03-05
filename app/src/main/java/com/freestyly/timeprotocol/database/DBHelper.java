package com.freestyly.timeprotocol.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Freestyly on 05.03.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "TimeProtocol.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_WORKTIME = "WORKTIMES";
    public static final String COL_DAY = "DAY";
    public static final String COL_COME = "COME";
    public static final String COL_LEAVE = "LEAVE";

    public static final String TABLE_CONFIG = "CONFIG";
    public static final String COL_ID = "_id";
    public static final String COL_WORKING_MINUTES = "WORKING_MINUTES";
    public static final String COL_STARTING_OVERTIME = "STARTING_OVERTIME";

    public static final String SQL_CREATE_WORKTIME = "CREATE TABLE IF NOT EXISTS "+ TABLE_WORKTIME + "( " +
            COL_DAY + " TEXT PRIMARY KEY," +
            COL_COME + " TEXT," +
            COL_LEAVE +" TEXT);";

    public static final String SQL_CREATE_CONFIG = "CREATE TABLE IF NOT EXISTS "+ TABLE_CONFIG +" ( " +
            COL_ID +" INTEGER PRIMARY KEY," +
            COL_WORKING_MINUTES + " INTEGER NOT NULL," +
            COL_STARTING_OVERTIME + " INTEGER NOT NULL);";

    public DBHelper(Context context) {
        super(context, DB_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE_WORKTIME);
            db.execSQL(SQL_CREATE_CONFIG);

        }
        catch (Exception ex) {
            Log.e("DbHelper", "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    public void consistencyCheck(SQLiteDatabase db){
        try {
            db.execSQL(SQL_CREATE_WORKTIME);
            db.execSQL(SQL_CREATE_CONFIG);
        }
        catch (Exception ex) {
            Log.e("DbHelper", "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
