package com.example.gismo.theinventoryapp;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gismo.theinventoryapp.data.ProductsContract;

/**
 * Created by gismo on 7/20/2017.
 */

public class ProductCursorAdapter extends CursorRecyclerAdapter<ProductCursorAdapter.ViewHolder> {

    private CatalogActivity activity = new CatalogActivity();

    public ProductCursorAdapter(CatalogActivity context, Cursor c) {
        super(context, c);
        this.activity = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView nameTextView;
        protected TextView priceTextView;
        protected TextView quantityTextView;
        protected TextView availableTextView;
        protected TextView buy;
        protected ImageView productPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.text_product);
            priceTextView = (TextView) itemView.findViewById(R.id.text_product_price);
            quantityTextView = (TextView) itemView.findViewById(R.id.text_product_quantity);
            buy = (TextView) itemView.findViewById(R.id.buy);
            productPicture = (ImageView) itemView.findViewById(R.id.product_image);
            availableTextView = (TextView) itemView.findViewById(R.id.text_available);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {

        final long id;
        final int mQuantity;

        id = cursor.getLong(cursor.getColumnIndex(ProductsContract.ProductEntry._ID));
        int nameColumnIndex = cursor.getColumnIndex(ProductsContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductsContract.ProductEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(ProductsContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int pictureColumnIndex = cursor.getColumnIndex(ProductsContract.ProductEntry.COLUMN_PRODUCT_PICTURE);

        String productName = cursor.getString(nameColumnIndex);
        String productPrice = cursor.getString(priceColumnIndex);
        int quantity = cursor.getInt(quantityColumnIndex);
        String imageUriString = cursor.getString(pictureColumnIndex);
        Uri imageUri = Uri.parse(imageUriString);

        mQuantity = quantity;

        viewHolder.nameTextView.setText(productName);
        viewHolder.priceTextView.setText(productPrice);
        viewHolder.quantityTextView.setText(String.valueOf(quantity));
        if (quantity == 0) {
            viewHolder.quantityTextView.setVisibility(View.GONE);
            viewHolder.availableTextView.setTextColor(activity.getResources().getColor(R.color.red));
            viewHolder.availableTextView.setText(activity.getString(R.string.soldOut));
        } else {
            viewHolder.quantityTextView.setVisibility(View.VISIBLE);
            viewHolder.quantityTextView.setTextColor(activity.getResources().getColor(R.color.lightDarkLine));
            viewHolder.availableTextView.setTextColor(activity.getResources().getColor(R.color.lightDarkLine));
            viewHolder.availableTextView.setText(activity.getString(R.string.availableProduct));
        }
        viewHolder.productPicture.setImageURI(imageUri);
        viewHolder.productPicture.invalidate();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onProductClick(id);
            }
        });

        viewHolder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuantity > 0) {
                    activity.onBuyNowClick(id, mQuantity);
                } else {
                    Toast.makeText(activity, R.string.soldOut, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


