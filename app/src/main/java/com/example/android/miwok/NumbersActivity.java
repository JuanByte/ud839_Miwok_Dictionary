package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        //Create list of words
        ArrayList<String> words = new ArrayList<>();
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");

        //find the root view so we can add child views to it and assign it to
        // rootView linear layout.
        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        //create a variable to keep track of the current index position.
        //keep looping until we reach the end of the list
        for (int i = 0; i < words.size(); i++) {
            //create a new text view
            TextView wordView = new TextView(this);
            //set text to be word at the current position
            wordView.setText(words.get(i));
            //add this text view as another child to the root view of this layout
            rootView.addView(wordView);
        }
    }
}
