package com.arover.momentsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.arover.moment.Moment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Moment moment = new Moment();
        moment.edit().setDay(19);

        Moment m1 = new Moment();
        m1.edit().setDay(21);

        Log.d(TAG,"now.format()="+moment.display().fromNow(this,m1));
        Log.d(TAG,"first day of month="+moment.query().firstDayOfMonth());
        Log.d(TAG,"last monday="+moment.query().lastMonday());
        TextView demoText = (TextView) findViewById(R.id.text);
        demoText.setText(moment.query().lastMonday().toString());

    }
}
