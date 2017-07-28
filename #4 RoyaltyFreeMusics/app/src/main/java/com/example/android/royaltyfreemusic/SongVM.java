package com.example.android.royaltyfreemusic;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by gismo on 5/26/2017.
 */

public class SongVM extends AppCompatActivity {

    private String artistName;
    private String trackTitle;
    private String trackLength;
    private String category;
    private ImageButton playButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_item_view);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        playButton = (ImageButton) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), NowPlayingActivity.class);
                startActivity(i);
            }
        });
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public String getTrackLength() {
        return trackLength;
    }

    public String getArtistName() {
        return artistName;
    }

    public SongVM(String artistName, String trackTitle, String trackLength, String category) {
        this.artistName = artistName;
        this.trackTitle = trackTitle;
        this.trackLength = trackLength;
        this.category = category;
    }
}
