package com.example.gismo.mygoodhabit.data;

import android.provider.BaseColumns;

/**
 * Created by gismo on 7/16/2017.
 */

public final class HabitContract {

    private HabitContract() {}

    public static final class HabitDbEntry implements BaseColumns {

        public final static String TABLE_NAME = "my_habits";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_SPORT = "activity";
        public final static String COLUMN_HABIT_HOURS = "hours";
        public final static String COLUMN_HABIT_DATE = "date";
    }
}
