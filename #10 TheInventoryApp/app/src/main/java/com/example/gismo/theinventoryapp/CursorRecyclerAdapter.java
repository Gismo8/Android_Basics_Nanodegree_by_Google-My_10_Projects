package com.example.gismo.theinventoryapp;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;

/**
 * Created by gismo on 7/20/2017.
 */

public abstract class CursorRecyclerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private Cursor cur;
    private CatalogActivity ac;
    private boolean dataValid;
    private int rowIdColumn;
    private DataSetObserver dataSetObserver;

    public CursorRecyclerAdapter(CatalogActivity context, Cursor c) {
        this.ac = context;
        this.cur = c;
        this.dataValid = cur != null;
        rowIdColumn = dataValid ? cur.getColumnIndex("_id") : -1;
        dataSetObserver = new NotifyingDataSetObserver();
        if (cur != null) {
            cur.registerDataSetObserver(dataSetObserver);
        }
    }

    @Override
    public int getItemCount() {
        if (dataValid && cur != null) {
            return cur.getCount();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if (dataValid && cur != null && cur.moveToPosition(position)) {
            return cur.getLong(rowIdColumn);
        }
        return 0;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }


    public abstract void onBindViewHolder(VH viewHolder, Cursor cursor);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (!dataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!cur.moveToPosition(position)) {
            throw new IllegalStateException("couldn't move cursor to position " + position);
        }

        onBindViewHolder(holder, cur);


    }

    public Cursor swapCursor(Cursor newCursor) {
        if (newCursor == cur) {
            return null;
        }
        final Cursor oldCursor = cur;
        if (oldCursor != null && dataSetObserver != null) {
            oldCursor.unregisterDataSetObserver(dataSetObserver);
        }
        cur = newCursor;
        if (cur != null) {
            if (dataSetObserver != null) {
                cur.registerDataSetObserver(dataSetObserver);
            }
            rowIdColumn = newCursor.getColumnIndexOrThrow("_id");
            dataValid = true;
            notifyDataSetChanged();
        } else {
            rowIdColumn = -1;
            dataValid = false;
            notifyDataSetChanged();
        }
        return oldCursor;
    }

    private class NotifyingDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            dataValid = true;
            notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            dataValid = false;
            notifyDataSetChanged();
        }
    }

}
