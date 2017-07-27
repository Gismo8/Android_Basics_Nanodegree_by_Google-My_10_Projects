package com.example.android.simplemathquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FancyButton button = (FancyButton) findViewById(R.id.score_button);
        button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pulse));

    }

    public void showScore(View view) {
        RadioButton firstFirst = (RadioButton) findViewById(R.id.firstfirst);
        boolean firstAnswer = firstFirst.isChecked();

        CheckBox firstCheckbox = (CheckBox) findViewById(R.id.first_checkbox);
        CheckBox secondCheckbox = (CheckBox) findViewById(R.id.second_checkbox);
        CheckBox thirdCheckbox = (CheckBox) findViewById(R.id.third_checkbox);
        CheckBox fourthCheckbox = (CheckBox) findViewById(R.id.fourth_checkbox);

        boolean secondAnswer1 = firstCheckbox.isChecked();
        boolean secondAnswer2 = secondCheckbox.isChecked();
        boolean secondAnswer3 = thirdCheckbox.isChecked();
        boolean secondAnswer4 = fourthCheckbox.isChecked();



        EditText sequenceNumber = (EditText) findViewById(R.id.sequence_number);
        String thirdAnswerText = String.valueOf(sequenceNumber.getText());
        boolean thirdAnswer = false;
        if (thirdAnswerText.equals("34")) {
            thirdAnswer = true;
        }

        EditText oddNumber = (EditText) findViewById(R.id.odd_number);
        boolean fourthAnswer = false;
        if (oddNumber.getText().toString().equals("")) {
            fourthAnswer = false;
        } else {
            int fourthAnswerOdd = Integer.parseInt(oddNumber.getText().toString());

            if (fourthAnswerOdd >= 0) {
                if ((fourthAnswerOdd % 2) == 1) {
                    fourthAnswer = true;
                } else {
                    fourthAnswer = false;
                }
            }
        }

        EditText evenNumber = (EditText) findViewById(R.id.even_number);
        boolean fifthAnswer = false;
        if (evenNumber.getText().toString().equals("")) {
            fifthAnswer = false;
        } else {
            int fifthAnswerEven = Integer.parseInt(evenNumber.getText().toString());

            if (fifthAnswerEven >= 0) {
                if ((fifthAnswerEven % 2) == 0 || fifthAnswerEven == 0) {
                    fifthAnswer = true;
                } else {
                    fifthAnswer = false;
                }
            }
        }

        RadioButton sixthFirst = (RadioButton) findViewById(R.id.triangle_angle);
        boolean sixthAnswer = sixthFirst.isChecked();

        RadioButton seventhFirst = (RadioButton) findViewById(R.id.rectangle);
        boolean seventhAnswer = seventhFirst.isChecked();

        RadioButton eighthFirst = (RadioButton) findViewById(R.id.square_false);
        boolean eighthAnswer = eighthFirst.isChecked();

        int score = calculateScore(firstAnswer, secondAnswer1, secondAnswer2, secondAnswer3, secondAnswer4, thirdAnswer, fourthAnswer, fifthAnswer, sixthAnswer, seventhAnswer, eighthAnswer);

        EditText username = (EditText) findViewById(R.id.user_name);
        name = String.valueOf(username.getText());

        displayScore(score, name);
    }

    public int calculateScore(boolean firstAnswer, boolean secondAnswer1, boolean secondAnswer2, boolean secondAnswer3, boolean secondAnswer4, boolean thirdAnswer, boolean fourthAnswer, boolean fifthAnswer, boolean sixthAnswer, boolean seventhAnswer, boolean eighthAnswer) {

        int score = 0;

        if (firstAnswer) {
            score = score + 1;
        }

        if (secondAnswer1 && secondAnswer4 && !secondAnswer3 && !secondAnswer2) {
            score = score + 1;
        }

        if (thirdAnswer) {
            score = score + 1;
        }

        if (fourthAnswer) {
            score = score + 1;
        }

        if (fifthAnswer) {
            score = score + 1;
        }

        if (sixthAnswer) {
            score = score + 1;
        }

        if (seventhAnswer) {
            score = score + 1;
        }

        if (eighthAnswer) {
            score = score + 1;
        }

        return score;
    }

    public void displayScore(int score, String name) {
        if (!name.equals("")) {

            if (score == 0) {
                Toast.makeText(this, "Come on, " + name + "! You scored 0 points! You can do better! Try again!", Toast.LENGTH_LONG).show();
            } else if (score < 5) {
                Toast.makeText(this, "Not bad, " + name + "! Your score is: " + score + "/8. You can do better! Try again!", Toast.LENGTH_LONG).show();
            } else if (score == 7) {
                Toast.makeText(this, "You are great " + name + "! Your score is: " + score + "/8. Only one right answer from being perfect!", Toast.LENGTH_LONG).show();
            } else if (score == 8) {
                Toast.makeText(this, "You are such a genious " + name + "! Your score is: " + score + "/8. IT'S JUST PERFECT! Congratulations!", Toast.LENGTH_LONG).show();
            } else if (score >= 5) {
                Toast.makeText(this, "Good, " + name + "! Your score is: " + score + "/8. You answered more than half of the question right!", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You need to answer at least the first question!", Toast.LENGTH_LONG).show();
        }
    }
}
