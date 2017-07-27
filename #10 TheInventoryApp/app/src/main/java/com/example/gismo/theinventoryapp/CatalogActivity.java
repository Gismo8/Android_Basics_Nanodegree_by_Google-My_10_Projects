package com.example.gismo.theinventoryapp;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.gismo.theinventoryapp.data.ProductsContract;

/**
 * Created by gismo on 7/20/2017.
 */

public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PRODUCT_LOADER = 0;

    ProductCursorAdapter cursorAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    View emptyView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.list_view);
        layoutManager = new LinearLayoutManager(CatalogActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        emptyView = findViewById(R.id.empty_view);

        cursorAdapter = new ProductCursorAdapter(this, null);
        recyclerView.setAdapter(cursorAdapter);

        getSupportLoaderManager().initLoader(PRODUCT_LOADER, null, this);
    }

    public void onProductClick(long id) {
        Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);

        Uri currentProductUri = ContentUris.withAppendedId(ProductsContract.ProductEntry.CONTENT_URI, id);
        intent.setData(currentProductUri);

        startActivity(intent);
    }

    public void onBuyNowClick(long id, int quantity) {
        Uri currentProductUri = ContentUris.withAppendedId(ProductsContract.ProductEntry.CONTENT_URI, id);
        Log.v("CatalogActivity", "Uri: " + currentProductUri);
        quantity--;
        ContentValues values = new ContentValues();
        values.put(ProductsContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        int rowsEffected = getContentResolver().update(currentProductUri, values, null, null);
    }

    private void insertProduct() {

        ContentValues values = new ContentValues();
        values.put(ProductsContract.ProductEntry.COLUMN_PRODUCT_NAME, getString(R.string.dummyDataName));
        values.put(ProductsContract.ProductEntry.COLUMN_PRODUCT_PRICE, getString(R.string.dummyDataPrice));
        values.put(ProductsContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, 0);
        values.put(ProductsContract.ProductEntry.COLUMN_PRODUCT_PICTURE, getString(R.string.dummyDataPicUri));
        values.put(ProductsContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME, getString(R.string.dummyDataSupplierName));
        values.put(ProductsContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL, getString(R.string.dummyDataSupplierEmail));

        Uri uri = getContentResolver().insert(ProductsContract.ProductEntry.CONTENT_URI, values);
        Log.v("CatalogActivity", "Uri of new product: " + uri);

    }

    private void deleteAllProducts() {
        int rowsDeleted = getContentResolver().delete(ProductsContract.ProductEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from product database");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertProduct();
                return true;
            case R.id.action_delete_all_products:
                deleteAllProducts();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ProductsContract.ProductEntry._ID,
                ProductsContract.ProductEntry.COLUMN_PRODUCT_NAME,
                ProductsContract.ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductsContract.ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductsContract.ProductEntry.COLUMN_PRODUCT_PICTURE,
                ProductsContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                ProductsContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL};

        return new CursorLoader(this,
                ProductsContract.ProductEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.getCount() == 0) {
            emptyView.setVisibility(View.VISIBLE);

        } else {
            emptyView.setVisibility(View.GONE);
        }
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
