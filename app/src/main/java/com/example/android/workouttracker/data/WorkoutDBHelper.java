package com.example.android.workouttracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.workouttracker.data.WorkoutContract.workoutEntry;

/**
 * Created by Mick Jagger on 4/11/2017.
 */

public class WorkoutDBHelper extends SQLiteOpenHelper {

    //Create TABLE string
    private static final String SQL_CREATE_WORKOUT_TABLE =
            "CREATE TABLE " + workoutEntry.TABLE_NAME + " (" +
                    workoutEntry._ID + " INTEGER PRIMARY KEY," +
                    workoutEntry.COLUMN_DATE + " TEXT);";

    //Delete TABLE string
    private static final String SQL_DELETE_WORKOUT_TABLE =
            "DROP TABLE IF EXISTS " + workoutEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GymTime.db";

    public WorkoutDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_WORKOUT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_WORKOUT_TABLE);
        onCreate(db);
    }
}
