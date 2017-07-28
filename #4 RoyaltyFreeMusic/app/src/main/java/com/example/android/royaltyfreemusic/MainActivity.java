package com.example.android.royaltyfreemusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  Activity currentActivity;
    private TextView mTextMessage;
    private Button browseButton;
    private Button nowPlayingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentActivity = this;

        mTextMessage = (TextView) findViewById(R.id.message);
        mTextMessage.setText(R.string.title_home);
        browseButton = (Button) findViewById(R.id.browseButton);
        nowPlayingButton = (Button) findViewById(R.id.nowPlayingButton);
        final Intent i = new Intent(getBaseContext(), BrowseActivity.class);
        final Intent i2 = new Intent(getBaseContext(), NowPlayingActivity.class);

        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(currentActivity,i);
            }
        });

        nowPlayingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(currentActivity,i2);
            }
        });
    }

    public static void startActivity(Activity currentActivity, Intent intent) {
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
