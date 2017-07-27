package com.example.gismo.mygoodhabit;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gismo.mygoodhabit.data.HabitDbHelper;
import com.example.gismo.mygoodhabit.data.HabitContract.HabitDbEntry;

public class HabitActivity extends AppCompatActivity {

    private HabitDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);

        dbHelper = new HabitDbHelper(this);
        Cursor cursor = readHabitsDatabase();
        insertHabit();
        displayDatabaseInfo(cursor);
    }

    private Cursor readHabitsDatabase(){
        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitDbEntry._ID,
                HabitDbEntry.COLUMN_HABIT_SPORT,
                HabitDbEntry.COLUMN_HABIT_HOURS,
                HabitDbEntry.COLUMN_HABIT_DATE};

        // Perform a query on the habits table
        Cursor cursor = db.query(
                HabitDbEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                 // The sort order

        return cursor;
    }

    private void displayDatabaseInfo(Cursor cursor) {

        TextView displayView = (TextView) findViewById(R.id.info_text);

        try {
            displayView.setText("The habits table contains " + cursor.getCount() + " habit records.\n\n");
            displayView.append(HabitDbEntry._ID + " - " +
                    HabitDbEntry.COLUMN_HABIT_SPORT + " - " +
                    HabitDbEntry.COLUMN_HABIT_HOURS + " - " +
                    HabitDbEntry.COLUMN_HABIT_DATE + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitDbEntry._ID);
            int sportColumnIndex = cursor.getColumnIndex(HabitDbEntry.COLUMN_HABIT_SPORT);
            int hoursColumnIndex = cursor.getColumnIndex(HabitDbEntry.COLUMN_HABIT_HOURS);
            int dayColumnIndex = cursor.getColumnIndex(HabitDbEntry.COLUMN_HABIT_DATE);

            cursor.moveToFirst();

            int currentID = cursor.getInt(idColumnIndex);
            String currentSport = cursor.getString(sportColumnIndex);
            int currentHours = cursor.getInt(hoursColumnIndex);
            String  currentDay = cursor.getString(dayColumnIndex);

            displayView.append(("\n" + currentID + " - " +
                    currentSport + " - " +
                    currentHours + " - " +
                    currentDay));
        } finally {
            cursor.close();
        }
    }

    private void insertHabit() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitDbEntry.COLUMN_HABIT_SPORT, "Gym");
        values.put(HabitDbEntry.COLUMN_HABIT_HOURS, 1);
        values.put(HabitDbEntry.COLUMN_HABIT_DATE, "15/07/2017");

        long newRowId = db.insert(HabitDbEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Habit saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
}


