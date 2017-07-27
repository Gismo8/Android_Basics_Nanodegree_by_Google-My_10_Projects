package com.example.gismo.awesomebooklistingapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String KEYWORDS_KEY = "KEYWORDS";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (!(networkInfo != null && networkInfo.isConnectedOrConnecting())) {
            Toast.makeText(this, getString(R.string.no_connection), Toast.LENGTH_LONG).show();
        }

        editText = ((EditText) findViewById(R.id.edit_text_view));

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                    performSearch();
                return true;
            }
        });
    }

    public void onSearchButtonClick(View view) {
        performSearch();
    }

    private void performSearch() {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (!(networkInfo != null && networkInfo.isConnectedOrConnecting())) {
            Toast.makeText(this, getString(R.string.no_connection), Toast.LENGTH_LONG).show();
        } else {
            String userKeywords = editText.getText().toString();

            if (userKeywords.isEmpty()) {
                Toast toast = Toast.makeText(this, R.string.enter_keywords, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                Intent intent = new Intent(this, BookListActivity.class);
                intent.putExtra(KEYWORDS_KEY, userKeywords);
                startActivity(intent);
            }
        }
    }
}

