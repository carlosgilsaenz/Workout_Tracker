package com.example.android.workouttracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.android.workouttracker.data.WorkoutContract.workoutEntry;
import com.example.android.workouttracker.data.WorkoutDBHelper;

public class MainActivity extends AppCompatActivity {

    private WorkoutDBHelper mDbHelper;

    @OnClick(R.id.completed_workout_card)
    public void completedClicked() {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        //  Most logic performed on helper method
        insertWorkout(database);

        database.close();
    }

    @OnClick(R.id.last_workout_card)
    public void lastClicked() {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        //  Most logic performed on helper method
        String results = getLastWorkout(database);

        Toast.makeText(this, results, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mDbHelper = new WorkoutDBHelper(this);
    }

    public String getLastWorkout(SQLiteDatabase db) {

        // projection of data to grab
        String[] projection = {
                workoutEntry.COLUMN_DATE};

        // Perform a query on the workout table
        Cursor cursor = db.query(
                workoutEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        // Figure out the index of each column
        int dateColumnIndex = cursor.getColumnIndex(workoutEntry.COLUMN_DATE);

        String currentDate;

        // Move to last row
        if (cursor.moveToLast()) {
            currentDate = getString(R.string.get_last_response_one) + cursor.getString(dateColumnIndex);
        } else {
            currentDate = getString(R.string.get_last_response_two);
        }

        // close all to save resources
        cursor.close();
        db.close();

        return currentDate;
    }

    public void insertWorkout(SQLiteDatabase db) {
        //  Grabs current date/time
        String currentDateString = DateFormat.getDateTimeInstance().format(new Date());

        // Create values to insert to db
        ContentValues values = new ContentValues();
        values.put(workoutEntry.COLUMN_DATE, currentDateString);

        // Insert the new row, returning the primary key value of the new row
        long id = db.insert(workoutEntry.TABLE_NAME, null, values);

        //  Checks if save is successful and prompts accordingly
        if (id == -1) {
            Toast.makeText(this, R.string.insert_response_one, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.insert_response_two, Toast.LENGTH_SHORT).show();
        }
    }
}
