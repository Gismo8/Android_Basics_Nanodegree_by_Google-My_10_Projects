package com.example.gismo.mygoodhabit.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.gismo.mygoodhabit.data.HabitContract.HabitDbEntry;

/**
 * Created by gismo on 7/16/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    //DATABASE VERSION
    private static final int DATABASE_VERSION = 1;

    //DATABASE NAME
    private static final String DATABASE_NAME = "my_habits.db";

    //DATABASE
    private SQLiteDatabase sqLiteDatabase;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //CREATE NEW TABLE
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_HABIT_TRACKER_TABLE = "CREATE TABLE " + HabitDbEntry.TABLE_NAME + "(" +
                HabitDbEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitDbEntry.COLUMN_HABIT_SPORT + " TEXT NOT NULL, " +
                HabitDbEntry.COLUMN_HABIT_HOURS + " INT NOT NULL DEFAULT 0, " +
                HabitDbEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL DEFAULT 0); ";
        sqLiteDatabase.execSQL(SQL_CREATE_HABIT_TRACKER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + HabitDbEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    void insertValue(ContentValues contentValues) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(HabitDbEntry.TABLE_NAME, null, contentValues);
    }

    Cursor readHabit(int id) {
        Cursor cursor;
        String selection = HabitDbEntry._ID + "=?";
        String[] sArgs = new String[]{Integer.toString(id)};
        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(true,
                HabitDbEntry.TABLE_NAME, null, selection, sArgs, null, null,
                null, null);
        cursor.moveToFirst();
        sqLiteDatabase.close();
        return cursor;
    }
}
