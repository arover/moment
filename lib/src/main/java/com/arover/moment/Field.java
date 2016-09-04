package com.arover.moment;

import java.util.Calendar;

/**
 * Created by minstrel on 8/27/16.
 */
public class Field {

    private Moment mMoment;
    private Calendar mCalendar;

    /**
     * construct moment field getter.
     * @param moment
     */
    public Field(Moment moment) {
        mCalendar = moment.getCalendar();
        mMoment = moment;
    }

    public long timeInSeconds() {
        return mCalendar.getTimeInMillis() / 1000;
    }

    /**
     *
     * @return time in millisecond
     */
    public long timeInMillis() {
        return mCalendar.getTimeInMillis();
    }

    public int millis(){
        return mCalendar.get(Calendar.MILLISECOND);
    }

    public int second(){
        return mCalendar.get(Calendar.SECOND);
    }

    public int minute(){
        return mCalendar.get(Calendar.MINUTE);
    }

    public int hour(){
        return mCalendar.get(Calendar.HOUR_OF_DAY);
    }

    public int day(){
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public Month month(){
        return Month.from(mCalendar.get(Calendar.MONTH));
    }

    /**
     *
     * @return index of month , 0 - 11.
     */
    public int monthIndex(){
        return mCalendar.get(Calendar.MONTH);
    }

    public int year(){
        return mCalendar.get(Calendar.YEAR);
    }

}
