package com.arover.momentsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.arover.moment.Moment;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Moment now = new Moment();
        Log.d(TAG,"now.format()="+now.format());
        Log.d(TAG,"locale "+ Locale.getDefault().getISO3Country());
    }
}
