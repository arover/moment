package com.arover.momentsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arover.moment.Moment;
import com.arover.moment.Month;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Moment now = new Moment();
        now.setMonth(Calendar.JANUARY);
    }
}
