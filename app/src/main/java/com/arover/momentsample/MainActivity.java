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
        StringBuilder builder = new StringBuilder();
        Moment moment = new Moment();
//        moment.edit().setDay(19);

        Moment m1 = new Moment();
        m1.edit().setDay(21);

        builder.append("Display: time from now=").append(moment.display().fromNow(this, m1)).append('\n');
        builder.append("Display: date=").append(moment.display().date()).append('\n');
        builder.append("Display: dateIso=").append(moment.display().dateIso()).append('\n');
        builder.append("Display: formatIso8601=").append(moment.display().formatIso8601()).append('\n');
        builder.append("Display: shortestDate=").append(moment.display().shortestDate()).append('\n');
        builder.append("Display: simpleTime=").append(moment.display().simpleTime()).append('\n');
        builder.append("Display: time=").append(moment.display().time()).append('\n');


        builder.append("Query: is leap year=").append(moment.query().isLeapYear()).append('\n');
        builder.append("Query: last monday=").append(moment.query().lastMonday()).append('\n');
        builder.append("Query: first day of month=").append(moment.query().firstDayOfMonth()).append('\n');

        TextView demoText = (TextView) findViewById(R.id.text);
        demoText.setText(builder.toString());

    }
}
