package com.arover.moment;

import java.util.Calendar;
import java.util.Date;

/**
 * a date time class inspired by momentjs.
 */
public class Moment {

    private final Calendar mCalendar;

    public Moment(Calendar now) {
        mCalendar = now;
    }

    public Moment() {
        mCalendar = Calendar.getInstance();
    }

    public Moment(int year, int month, int day) {
        mCalendar = Calendar.getInstance();
        mCalendar.set(year, month, day);
    }

    public Moment(long timeInMillis) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(timeInMillis);
    }

    public Calendar getCalendar() {
        return mCalendar;
    }

    public Date getDate() {
        return new Date(mCalendar.getTimeInMillis());
    }

    public Editor edit(){
        return new Editor(this);
    }

    public Query query(){
        return new Query(this);
    }

    public Field fields(){
        return new Field(this);
    }

    public Display display(){
        return new Display(this);
    }

    @Override
    public String toString() {
        return new Display(this).formatIso8601();
    }
}
