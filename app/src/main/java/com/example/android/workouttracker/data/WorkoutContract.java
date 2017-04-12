package com.example.android.workouttracker.data;

import android.provider.BaseColumns;

/**
 * Created by Mick Jagger on 4/11/2017.
 */

public final class WorkoutContract {

    private WorkoutContract() {
    }

    public static final class workoutEntry implements BaseColumns {
        //table name
        public static final String TABLE_NAME = "workouts";

        //id label
        public static final String _ID = BaseColumns._ID;

        //time of work out
        public static final String COLUMN_DATE = "date";
    }
}
