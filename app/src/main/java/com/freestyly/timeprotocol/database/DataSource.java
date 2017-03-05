package com.freestyly.timeprotocol.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freestyly on 05.03.2017.
 */

public class DataSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    static public String[] columns_time = {
            DBHelper.COL_DAY,
            DBHelper.COL_COME,
            DBHelper.COL_LEAVE
    };

    static public String[] columns_config = {
            DBHelper.COL_ID,
            DBHelper.COL_WORKING_MINUTES,
            DBHelper.COL_STARTING_OVERTIME
    };

    public DataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public Worktime createWorktimes(String day, String come, String leave) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_DAY, day);
        values.put(DBHelper.COL_COME, come);
        values.put(DBHelper.COL_LEAVE, leave);

        long insertId = database.insert(DBHelper.TABLE_WORKTIME, null, values);

        Cursor cursor = database.query(DBHelper.TABLE_WORKTIME,
                columns_time, DBHelper.COL_DAY + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Worktime wt = cursorToWorktime(cursor);
        cursor.close();

        return wt;
    }

    private Worktime cursorToWorktime(Cursor cursor) {

        String day = cursor.getString(cursor.getColumnIndex(DBHelper.COL_DAY));
        String come = cursor.getString(cursor.getColumnIndex(DBHelper.COL_COME));
        String leave = cursor.getString(cursor.getColumnIndex(DBHelper.COL_LEAVE));

        Worktime worktime = new Worktime(day, come, leave);

        return worktime;
    }

    public List<Worktime> getAllWorktimes() {
        List<Worktime> worktimes = new ArrayList<>();

        Cursor cursor = database.query(DBHelper.TABLE_WORKTIME,
                columns_time, null, null, null, null, dbHelper.COL_DAY + " ASC");

        cursor.moveToFirst();
        Worktime worktime;

        while(!cursor.isAfterLast()) {
            worktime = cursorToWorktime(cursor);
            worktimes.add(worktime);
            cursor.moveToNext();
        }
        cursor.close();

        return worktimes;
    }

    public void deleteWorktime(Worktime w) {
        String id = w.getDay();
        database.delete(dbHelper.TABLE_WORKTIME, dbHelper.COL_DAY + "=" + id, null);
    }

    public void updateWorktime(Worktime w) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_DAY, w.getDay());
        values.put(dbHelper.COL_COME, w.getCome());
        values.put(dbHelper.COL_LEAVE, w.getLeave());

        database.update(dbHelper.TABLE_WORKTIME,
                values,
                dbHelper.COL_DAY + "=" + w.getDay(),
                null);
    }

    public Config createConfig() {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_ID, 0);
        values.put(DBHelper.COL_WORKING_MINUTES, 0);
        values.put(DBHelper.COL_STARTING_OVERTIME, 0);

        long insertId = database.insert(DBHelper.TABLE_CONFIG, null, values);

        Cursor cursor = database.query(DBHelper.TABLE_CONFIG,
                columns_time, DBHelper.COL_ID + "=" + 0,
                null, null, null, null);

        cursor.moveToFirst();
        Config c = cursorToConfig(cursor);
        cursor.close();

        return c;
    }

    private Config cursorToConfig(Cursor cursor) {

        int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_ID));
        int work = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_WORKING_MINUTES));
        int start = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_STARTING_OVERTIME));

        Config c = new Config(id, work, start);

        return c;
    }

    public void updateConfig(Config cfg) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_ID, cfg.getId());
        values.put(dbHelper.COL_WORKING_MINUTES, cfg.getTimeToWork());
        values.put(dbHelper.COL_STARTING_OVERTIME, cfg.getStartOvertime());

        database.update(dbHelper.TABLE_CONFIG,
                values,
                dbHelper.COL_ID + "=" + cfg.getId(),
                null);
    }

}