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
        Moment moment = new Moment();
        moment.setDay(19);
        Moment m1 = new Moment();
        m1.setDay(21);
        Log.d(TAG,"now.format()="+moment.fromNow(this,m1));
    }
}
