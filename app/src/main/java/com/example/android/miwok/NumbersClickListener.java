package com.example.android.miwok;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Toast;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

@SuppressLint("Registered")
public class NumbersClickListener extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        Context context = getApplicationContext();
        CharSequence text = "Open the Numbers List";
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }
}
