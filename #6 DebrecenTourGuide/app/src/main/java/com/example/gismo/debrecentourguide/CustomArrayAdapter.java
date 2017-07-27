package com.example.gismo.debrecentourguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by gismo on 6/18/2017.
 */

public class CustomArrayAdapter extends ArrayAdapter<ListItem> {

    public CustomArrayAdapter(Context context, ArrayList<ListItem> listItems) {
        super(context, 0, listItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListItem listItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listitem, parent, false);
        }

        TextView nameView = (TextView) convertView.findViewById(R.id.name_view);
        TextView detailsView = (TextView) convertView.findViewById(R.id.details_view);
        TextView priceRangeView = (TextView) convertView.findViewById(R.id.price_range_view);
        TextView openingTimeView = (TextView) convertView.findViewById(R.id.opening_time_view);
        ImageView listItemImageView = (ImageView) convertView.findViewById(R.id.image_view);


        if (listItem.getPhoto() != 0){
            listItemImageView.setVisibility(View.VISIBLE);
            listItemImageView.setImageResource(listItem.getPhoto());
        } else {
            listItemImageView.setVisibility(View.GONE);
        }

        nameView.setText(listItem.getName());
        detailsView.setText(listItem.getDetails());
        priceRangeView.setText(listItem.getPriceRange());
        if (!TextUtils.isEmpty(listItem.getStarCount())){
            openingTimeView.setText(listItem.getStarCount());
        } else {
            openingTimeView.setText(listItem.getOpeningTime());
        }
        return convertView;
    }
}
