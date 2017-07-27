package com.example.gismo.debrecentourguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 */

public class ListItemFragment extends Fragment {

    public ListItemFragment(){
    }

    CustomArrayAdapter listItemAdapter;
    ArrayList<ListItem> listItems = new ArrayList<>();
    ListView listView;
    String fragmentTitle;

    public ListItemFragment(ArrayList<ListItem> listItems, String fragmentTitle) {
        this.fragmentTitle = fragmentTitle;
        this.listItems = listItems;  }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listitem, container, false);

        TextView fragmentTitleView = (TextView) rootView.findViewById(R.id.fragment_title_view) ;
        fragmentTitleView.setText(fragmentTitle);

        listItemAdapter = new CustomArrayAdapter(getContext(), listItems);
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(listItemAdapter);

        return rootView;
    }
}
