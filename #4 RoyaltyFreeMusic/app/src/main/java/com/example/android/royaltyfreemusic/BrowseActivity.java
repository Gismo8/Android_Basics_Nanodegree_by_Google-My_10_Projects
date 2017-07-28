package com.example.android.royaltyfreemusic;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gismo on 5/1/2017.
 */

public class BrowseActivity extends AppCompatActivity {

    private ArrayList<SongVM> listedSongs = new ArrayList<>();
    private Button homeButton;
    private Button nowPlayingButton;
    private Activity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        currentActivity = this;

        homeButton = (Button) findViewById(R.id.homeButton);
        nowPlayingButton = (Button) findViewById(R.id.nowPlayingButton);
        final Intent i = new Intent(getBaseContext(), MainActivity.class);
        final Intent i2 = new Intent(getBaseContext(), NowPlayingActivity.class);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(currentActivity, i);
            }
        });

        nowPlayingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(currentActivity, i2);
            }
        });

        //I am adding here the songs to listedSongs
        listedSongs.add(new SongVM("BenSound", "Happiness", "4:42", "Rock"));
        listedSongs.add(new SongVM("NeXt Day", "Sadness", "4:02", "Rock"));
        listedSongs.add(new SongVM("CodeYard Band", "Crazyness", "3:30", "Rock"));
        listedSongs.add(new SongVM("FeelIt", "This is awesome", "1:42", "Rock"));
        listedSongs.add(new SongVM("BetterGo", "You will win!", "2:42", "Rock"));
        listedSongs.add(new SongVM("Chosens", "Pleased to meet ya", "4:31", "Rock"));
        listedSongs.add(new SongVM("The Kingdom", "Never again", "3:22", "Rock"));
        listedSongs.add(new SongVM("Feets on the Ground", "Hold me back", "2:07", "Rock"));

        SongAdapter listedSongsAdapter = new SongAdapter(this, android.R.layout.simple_list_item_1, listedSongs);
        listedSongsAdapter.addAll(listedSongs);
        ListView songsListView = (ListView) findViewById(R.id.listedSongListView);
        songsListView.setAdapter(listedSongsAdapter);

    }

    public static void startActivity(Activity currentActivity, Intent intent) {
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
