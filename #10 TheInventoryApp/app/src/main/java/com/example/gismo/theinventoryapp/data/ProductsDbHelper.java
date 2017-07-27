package com.example.gismo.theinventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gismo on 7/20/2017.
 */

public class ProductsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "inventory.db";

    public ProductsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES = "CREATE TABLE " + ProductsContract.ProductEntry.TABLE_NAME +
                "(" +
                ProductsContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ProductsContract.ProductEntry.COLUMN_PRODUCT_NAME + " TEXT," +
                ProductsContract.ProductEntry.COLUMN_PRODUCT_PRICE + " TEXT NOT NULL DEFAULT 0," +
                ProductsContract.ProductEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER DEFAULT 0," +
                ProductsContract.ProductEntry.COLUMN_PRODUCT_PICTURE + " TEXT NOT NULL," +
                ProductsContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " TEXT NOT NULL, " +
                ProductsContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}