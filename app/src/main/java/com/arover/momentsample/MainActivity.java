package com.arover.momentsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.arover.moment.Display;
import com.arover.moment.Lunar;
import com.arover.moment.Moment;
import com.arover.moment.MomentUnit;
import com.arover.moment.Query;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StringBuilder builder = new StringBuilder();
        Moment moment = new Moment();

//        moment.edit().setDay(19);

        Moment twoDaysAndTwoHoursBefore = new Moment().edit()
                .minus(2, MomentUnit.HOUR)
                .minus(2, MomentUnit.DAY)
                .moment();

//        moment.display().milliseconds();
        builder.append("Display: two days and two hours before: ").append(twoDaysAndTwoHoursBefore.toString()).append('\n');
        Display display = moment.display();

        builder.append("Display: time from now = ").append(display.fromNow(this, twoDaysAndTwoHoursBefore)).append('\n');
        builder.append("Display: date = ").append(display.date()).append('\n');
        builder.append("Display: dateZhDefault = ").append(display.dateZhDefault()).append('\n');
        builder.append("Display: formatIso8601 = ").append(display.formatIso8601()).append('\n');
        builder.append("Display: shortestDate = ").append(display.shortestDate()).append('\n');
        builder.append("Display: simpleTime = ").append(display.simpleTime()).append('\n');
        builder.append("Display: time = ").append(display.time()).append('\n');

        Query query = moment.query();

        builder.append("Query: is leap year = ").append(query.isLeapYear()).append('\n');
        builder.append("Query: last monday = ").append(query.lastMonday()).append('\n');
        builder.append("Query: first day of month = ").append(query.firstDayOfMonth()).append('\n');

        Lunar lunar = moment.getLunar();

        builder.append("Chinese Lunar: ")
                .append(lunar.getLunarYear())
                .append("年")
                .append(lunar.getLunarMonth())
                .append("月")
                .append(lunar.getLunarDay())
                .append('\n');

        TextView demoText = (TextView) findViewById(R.id.text);
        demoText.setText(builder.toString());

    }
}
