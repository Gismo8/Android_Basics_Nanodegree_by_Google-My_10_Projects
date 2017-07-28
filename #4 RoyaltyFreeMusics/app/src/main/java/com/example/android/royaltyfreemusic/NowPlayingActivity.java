package com.example.android.royaltyfreemusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Gismo on 5/1/2017.
 */

public class NowPlayingActivity extends AppCompatActivity {

    private Button homeButton;
    private Button browseButton;
    private FloatingActionButton fab;
    private Activity currentActivity;


    @Override
    protected void onCreate(final Bundle savedInstance){

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_now_playing);

        currentActivity  = this;

        fab = (FloatingActionButton) findViewById(R.id.fab) ;
        fab.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pulse));

        homeButton = (Button) findViewById(R.id.homeButton);
        browseButton = (Button) findViewById(R.id.browseButton);
        final Intent i = new Intent(getBaseContext(), MainActivity.class);
        final Intent i2 = new Intent(getBaseContext(), BrowseActivity.class);
        final Intent i3 = new Intent(getBaseContext(), PaymentActivity.class);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(currentActivity, i);
            }
        });

        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(currentActivity, i2);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(currentActivity, i3);
            }
        });


    }

    public static void startActivity(Activity currentActivity, Intent intent) {
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
