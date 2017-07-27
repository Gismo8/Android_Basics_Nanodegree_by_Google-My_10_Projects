package gismo.games.android.fightcounter;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    int scoreMrBlue = 0;
    int scoreMrRed = 0;
    int healthMrBlue = 100;
    int healthMrRed = 100;

    ImageView bangIcon;
    ImageView redHitHead;
    ImageView redHitAbs;
    ImageView redHitChest;
    ImageView blueHitHead;
    ImageView blueHitAbs;
    ImageView blueHitChest;
    ImageView blueHead;
    ImageView blueAbs;
    ImageView blueChest;
    ImageView redHead;
    ImageView redAbs;
    ImageView redChest;
    ImageView mrRed;
    ImageView mrBlue;

    Animation pulse2;
    Animation pulse;
    Animation animSlideFromRight;
    Animation animSlideFromLeft;
    Animation floatingMrBlue;
    Animation floatingMrRed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bangIcon = (ImageView) findViewById(R.id.bangIcon);
        mrRed = (ImageView) findViewById(R.id.mrRedView);
        mrBlue = (ImageView) findViewById(R.id.mrBlue);
        redHitHead = (ImageView) findViewById(R.id.redHitHead);
        redHitAbs = (ImageView) findViewById(R.id.redHitAbs);
        redHitChest = (ImageView) findViewById(R.id.redHitChest);
        blueHitHead = (ImageView) findViewById(R.id.blueHitHead);
        blueHitAbs = (ImageView) findViewById(R.id.blueHitAbs);
        blueHitChest = (ImageView) findViewById(R.id.blueHitChest);
        blueHead = (ImageView) findViewById(R.id.blueHead);
        blueAbs = (ImageView) findViewById(R.id.blueabs);
        blueChest = (ImageView) findViewById(R.id.bluechest);
        redHead = (ImageView) findViewById(R.id.redHead);
        redAbs = (ImageView) findViewById(R.id.redabs);
        redChest = (ImageView) findViewById(R.id.redchest);

        pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        pulse2 = AnimationUtils.loadAnimation(this, R.anim.pulse2);
        animSlideFromRight = AnimationUtils.loadAnimation(this, R.anim.animationfromright);
        animSlideFromLeft = AnimationUtils.loadAnimation(this, R.anim.animationfromleft);
        floatingMrBlue = AnimationUtils.loadAnimation(this, R.anim.floatingmrblue);
        floatingMrRed = AnimationUtils.loadAnimation(this, R.anim.floatingmrred);

        //bangIcon.setAnimation(pulse);
        mrRed.startAnimation(floatingMrRed);
        mrBlue.startAnimation(floatingMrBlue);

    }


    //methods for Mr Blue

    public void displayScoreMrBlue(int score) {

        TextView scoreView = (TextView) findViewById(R.id.mrblue_score);
        scoreView.setText(String.valueOf(score));

    }

    public void displayHealthMrBlue(int health) {

        TextView healthView = (TextView) findViewById(R.id.mrblue_health);
        healthView.setText(String.valueOf(health));

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void mrBlueHitsHead(View view) {

        if (healthMrBlue != 0 && healthMrRed != 0) {

            blueHitHead.startAnimation(pulse2);
            redHead.setVisibility(View.VISIBLE);
            redHead.animate().alpha(1.0f).setDuration(100).withEndAction(new Runnable() {
                @Override
                public void run() {
                    redHead.setVisibility(GONE);
                }
            });

            if (healthMrRed > 0) {
                scoreMrBlue = scoreMrBlue + 3;
                if (healthMrRed < 25) {
                    healthMrRed = 0;

                } else {
                    healthMrRed = healthMrRed - 25;
                }
            }

            displayHealthMrRed(healthMrRed);
            displayScoreMrBlue(scoreMrBlue);
            mrBlueWins(healthMrRed);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void mrBlueHitsAbs(View view) {

        if (healthMrBlue != 0 && healthMrRed != 0) {

            blueHitAbs.startAnimation(pulse2);
            redAbs.setVisibility(View.VISIBLE);
            redAbs.animate().alpha(1.0f).setDuration(100).withEndAction(new Runnable() {
                @Override
                public void run() {
                    redAbs.setVisibility(GONE);
                }
            });

            if (healthMrRed > 0) {
                scoreMrBlue = scoreMrBlue + 2;
                if (healthMrRed < 10) {
                    healthMrRed = 0;

                } else {
                    healthMrRed = healthMrRed - 10;
                }
            }

            displayHealthMrRed(healthMrRed);
            displayScoreMrBlue(scoreMrBlue);
            mrBlueWins(healthMrRed);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void mrBlueHitsChests(View view) {

        if (healthMrBlue != 0 && healthMrRed != 0) {

            blueHitChest.startAnimation(pulse2);
            redChest.setVisibility(View.VISIBLE);
            redChest.animate().alpha(1.0f).setDuration(100).withEndAction(new Runnable() {
                @Override
                public void run() {
                    redChest.setVisibility(GONE);
                }
            });

            if (healthMrRed > 0) {

                scoreMrBlue = scoreMrBlue + 1;
                if (healthMrRed < 3) {
                    healthMrRed = 0;

                } else {
                    healthMrRed = healthMrRed - 3;
                }

                displayScoreMrBlue(scoreMrBlue);
                displayHealthMrRed(healthMrRed);
                mrBlueWins(healthMrRed);

            }
        }
    }


    //methods for Mr Red

    public void displayScoreMrRed(int score) {

        TextView scoreView = (TextView) findViewById(R.id.mrred_score);
        scoreView.setText(String.valueOf(score));

    }

    public void displayHealthMrRed(int health) {

        TextView healthView = (TextView) findViewById(R.id.mrred_health);
        healthView.setText(String.valueOf(health));

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void mrRedHitsHead(View view) {

        if (healthMrBlue != 0 && healthMrRed != 0) {

            redHitHead.startAnimation(pulse2);
            blueHead.setVisibility(View.VISIBLE);
            blueHead.animate().alpha(1.0f).setDuration(100).withEndAction(new Runnable() {
                @Override
                public void run() {
                    blueHead.setVisibility(GONE);

                }
            });
            if (healthMrBlue > 0) {

                scoreMrRed = scoreMrRed + 3;
                if (healthMrBlue < 20) {
                    healthMrBlue = 0;
                } else {
                    healthMrBlue = healthMrBlue - 20;

                }
            }
            displayHealthMrBlue(healthMrBlue);
            displayScoreMrRed(scoreMrRed);
            mrRedWins(healthMrBlue);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void mrRedHitsAbs(View view) {

        if (healthMrBlue != 0 && healthMrRed != 0) {

            redHitAbs.startAnimation(pulse2);
            blueAbs.setVisibility(View.VISIBLE);
            blueAbs.animate().alpha(1.0f).setDuration(100).withEndAction(new Runnable() {
                @Override
                public void run() {
                    blueAbs.setVisibility(GONE);

                }
            });

            if (healthMrBlue > 0) {
                scoreMrRed = scoreMrRed + 2;
                if (healthMrBlue < 15) {
                    healthMrBlue = 0;
                } else {
                    healthMrBlue = healthMrBlue - 15;
                }
            }

            displayScoreMrRed(scoreMrRed);
            displayHealthMrBlue(healthMrBlue);
            mrRedWins(healthMrBlue);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void mrRedHitsChest(View view) {

        if (healthMrBlue != 0 && healthMrRed != 0) {

            redHitChest.startAnimation(pulse2);
            blueChest.setVisibility(View.VISIBLE);
            blueChest.animate().alpha(1.0f).setDuration(100).withEndAction(new Runnable() {
                @Override
                public void run() {
                    blueChest.setVisibility(GONE);
                }
            });

            scoreMrRed = scoreMrRed + 1;

            if (healthMrBlue < 5) {
                healthMrBlue = 0;
            } else {
                healthMrBlue = healthMrBlue - 5;
            }

            displayScoreMrRed(scoreMrRed);
            displayHealthMrBlue(healthMrBlue);
            mrRedWins(healthMrBlue);

        }
    }

    public void reSet(View v) {

        mrBlue.setImageResource(R.drawable.freeza);
        mrRed.setImageResource(R.drawable.goku);
        scoreMrBlue = 0;
        displayScoreMrBlue(scoreMrBlue);
        scoreMrRed = 0;
        displayScoreMrRed(scoreMrRed);
        healthMrBlue = 100;
        displayHealthMrBlue(healthMrBlue);
        healthMrRed = 100;
        displayHealthMrRed(healthMrRed);

    }

    public void mrBlueWins(int healthMrRed) {

        if (healthMrRed <= 0) {
            mrRed.setImageResource(R.drawable.goku_hurt);
            Toast toast = Toast.makeText(this, "OMG! KO! Frieza won! Rematch?", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, -400);
            toast.show();
        }

    }

    public void mrRedWins(int healthMrBlue) {

        if (healthMrBlue <= 0) {
            mrBlue.setImageResource(R.drawable.frieza_hurt);
            Toast toast = Toast.makeText(this, "OMG! KO! Goku won! Rematch?", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, -400);
            toast.show();
        }

    }

}