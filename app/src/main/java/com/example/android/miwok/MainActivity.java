/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //Find the View that shows the numbers category
        TextView numbers = findViewById(R.id.numbers);
        // Set a clickListener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // the code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });
        }

//    public void openNumbersList(View view){
//        Intent intentNumbers = new Intent(MainActivity.this, NumbersActivity.class);
//        startActivity(intentNumbers);
//    }
    public void openFamilyList(View view){
        Intent intentFamily = new Intent(MainActivity.this, FamilyMembers.class);
        startActivity(intentFamily);
    }
    public void openPhrasesList(View view){
        Intent intentPhrases = new Intent(MainActivity.this, Phrases.class);
        startActivity(intentPhrases);
    }
    public void openColorsList(View view){
        Intent intentColors = new Intent(MainActivity.this, Colors.class);
        startActivity(intentColors);
    }
}
