package com.arover.moment;

import java.util.Calendar;

/**
 * Created by minstrel on 8/27/16.
 */
public class Field {

    private Moment moment;
    private Calendar calendar;

    /**
     * construct moment field getter.
     *
     * @param moment
     */
    public Field(Moment moment) {
        calendar = moment.getCalendar();
        this.moment = moment;
    }

    public long timeInSeconds() {
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * @return time in millisecond
     */
    public long timeInMillis() {
        return calendar.getTimeInMillis();
    }

    public int millis() {
        return calendar.get(Calendar.MILLISECOND);
    }

    public int second() {
        return calendar.get(Calendar.SECOND);
    }

    public int minute() {
        return calendar.get(Calendar.MINUTE);
    }

    public int hour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int day() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public @Moment.Month int month() {
        return calendar.get(Calendar.MONTH);
    }

    /**
     * @return index of month , 0 - 11.
     */
    public int monthIndex() {
        return calendar.get(Calendar.MONTH);
    }

    public int year() {
        return calendar.get(Calendar.YEAR);
    }

}
