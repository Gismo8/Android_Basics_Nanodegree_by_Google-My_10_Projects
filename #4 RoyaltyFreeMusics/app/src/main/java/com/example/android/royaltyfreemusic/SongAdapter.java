package com.example.android.royaltyfreemusic;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gismo on 5/26/2017.
 */

public class SongAdapter extends ArrayAdapter<SongVM> {

    public SongAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public SongAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SongAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull SongVM[] objects) {
        super(context, resource, objects);
    }

    public SongAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull SongVM[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public SongAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<SongVM> objects) {
        super(context, resource, objects);
    }

    public SongAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<SongVM> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item_view, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        SongVM currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView artistNameView = (TextView) listItemView.findViewById(R.id.artistNameView);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        artistNameView.setText(currentSong.getArtistName());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView songTitleView = (TextView) listItemView.findViewById(R.id.songTitleView);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        songTitleView.setText(currentSong.getTrackTitle());

        TextView songLenthView = (TextView) listItemView.findViewById(R.id.songLengthView);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        songLenthView.setText(currentSong.getTrackLength());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }


}
