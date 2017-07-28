package com.example.android.royaltyfreemusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by gismo on 6/17/2017.
 */

public class PaymentActivity extends AppCompatActivity {

    private Button homeButton;
    private Button nowPlayingButton;
    private Button browseButton;
    private ImageView payPalImage;
    private Activity currentActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        currentActivity = this;

        homeButton = (Button) findViewById(R.id.homeButton);
        nowPlayingButton = (Button) findViewById(R.id.nowPlayingButton);
        browseButton = (Button) findViewById(R.id.browseButton);
        payPalImage = (ImageView) findViewById(R.id.payPalImage);
        final Intent i = new Intent(getBaseContext(), MainActivity.class);
        final Intent i2 = new Intent(getBaseContext(), NowPlayingActivity.class);
        final Intent i3 = new Intent(getBaseContext(), BrowseActivity.class);

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

        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(currentActivity, i3);
            }
        });

        payPalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(currentActivity, "You are too kind! Thanks for the Coffee!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void startActivity(Activity currentActivity, Intent intent) {
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
