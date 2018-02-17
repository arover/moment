package com.arover.moment;

import java.security.InvalidParameterException;
import java.util.Calendar;

/**
 * the Editor of Moment
 * the editor doesn't change the origin moment.
 * call @{Editor#moment} to get the edited moment.
 *
 * @author arover
 *         created at 8/26/16 23:32
 */
public class Editor {

    private Moment moment;
    private Calendar calendar;

    /**
     * construct a moment editor
     *
     * @param moment for edit
     */
    public Editor(Moment moment) {
        this.moment = moment;
        calendar = moment.getCalendar();
    }

    /**
     * add x unit to moment.
     *
     * @param x    0-n
     * @param unit time unit
     */
    private void set(int x, @MomentUnit.Unit int unit) {
        switch (unit) {
            case MomentUnit.YEAR: {
                int year = calendar.get(Calendar.YEAR);
                calendar.set(Calendar.YEAR, year + x);
                break;
            }
            case MomentUnit.MONTH: {
                int months = x % 12;
                int year = x / 12;

                int month = calendar.get(Calendar.MONTH);
                if (month + x > Calendar.DECEMBER) {
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
                    calendar.set(Calendar.MONTH, (month + x) % 11);
                } else if (month + x < Calendar.JANUARY) {
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
                    calendar.set(Calendar.MONTH, (month + x) % 11);
                } else {
                    calendar.set(Calendar.MONTH, (month + x));
                }
                break;
            }
            case MomentUnit.DAY:
                calendar.add(Calendar.DAY_OF_YEAR, x);
                break;
            case MomentUnit.HOUR:
                calendar.add(Calendar.HOUR, x);
                break;
            case MomentUnit.MINUTE:
                calendar.add(Calendar.MINUTE, x);
                break;
            case MomentUnit.SECOND:
                calendar.add(Calendar.SECOND, x);
                break;
            case MomentUnit.MILLISECOND:
                calendar.add(Calendar.MILLISECOND, x);
                break;
        }
    }

    /**
     * @param n    the amount to add.
     *             negative number will be transforming to positive.
     * @param unit time unit.
     * @return Editor for chain
     */
    public Editor add(int n, int unit) {
        if (n < 0) {
            n = Math.abs(n);
        }
        set(n, unit);
        return this;
    }

    /**
     * minus amount of specific time unit time.
     *
     * @param n    the amount to minus to the field
     * @param unit time field/unit.
     *             @see MomentUnit
     * @return Editor for chain
     */
    public Editor minus(int n, int unit) {
        if (n < 0) {
            n = Math.abs(n);
        }
        set(-n, unit);
        return this;
    }

    /**
     *
     * @param timeInMillis time in milliseconds
     * @return Editor for chain
     */
    public Editor timeInMillis(long timeInMillis) {
        if (timeInMillis < 0)
            throw new InvalidParameterException("can't time in millisecond: " + timeInMillis);

        calendar.setTimeInMillis(timeInMillis);
        return this;
    }

    /**
     *
     * @param sec second [0,59]
     * @return Editor for chain
     */
    public Editor setSecond(int sec) {
        if (sec < 0 || sec > 59)
            throw new InvalidParameterException("can't sec second to " + sec);
        calendar.set(Calendar.SECOND, sec);
        return this;
    }

    public Editor timeInSeconds(int timeInSeconds) {
        if (timeInSeconds < 0)
            throw new InvalidParameterException("can't time in seconds: " + timeInSeconds);

        calendar.setTimeInMillis(timeInSeconds * 1000);
        return this;
    }

    public Editor second(int sec) {
        if (sec < 0 || sec > 59)
            throw new InvalidParameterException("can't sec second to " + sec);
        calendar.set(Calendar.SECOND, sec);
        return this;
    }

    /**
     * set the minute of the time
     *
     * @param min minute , [0,59]
     * @return editor for chain
     */
    public Editor minute(int min) {
        if (min < 0 || min > 59)
            throw new InvalidParameterException("can't sec minute to " + min);
        calendar.set(Calendar.MINUTE, min);
        return this;
    }

    /**
     * set the hour of day
     *
     * @param hour [0,23]
     * @return editor for chain
     */
    public Editor hour(int hour) {
        if (hour < 0 || hour > 23)
            throw new InvalidParameterException("can't sec hour to " + hour);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return this;
    }

    /**
     * set the day of month
     *
     * @param day [1,31]
     * @return editor for chain
     */
    public Editor day(int day) {
        if (day < 1 || day > 31)
            throw new InvalidParameterException("can't set day to " + day);

        calendar.set(Calendar.DAY_OF_MONTH, day);
        return this;
    }

    /**
     * set the day of year
     *
     * @param day [1,366]
     * @return editor for chain
     */
    public Editor dayOfYear(int day) {
            if (day < 1 || day > 366)
            throw new InvalidParameterException("can't sec day to " + day);

        calendar.set(Calendar.DAY_OF_YEAR, day);
        return this;
    }

    /**
     * set month of moment
     *
     * @param month [Calendar.JANUARY,Calendar.DECEMBER]
     *              0-11
     */
    public Editor month(int month) {
        calendar.set(Calendar.MONTH, month);
        return this;
    }

    /**
     * set time to the beginning of the day(00:00:00).
     */
    public Editor setBeginningOfDay() {
        Util.setTimeToBeginningOfDay(calendar);
        return this;
    }

    /**
     * set time to the beginning of the day(23:59:59).
     *
     * @return editor for chain
     */
    public Editor setEndOfDay() {
        Util.setTimeToEndOfDay(calendar);
        return this;
    }

    /**
     * change the year
     *
     * @param year [0,...]
     * @return editor for chain
     */
    public Editor year(int year) {
        calendar.set(Calendar.YEAR, year);
        return this;
    }

    /**
     * return moment this editor edited
     * @return edited moment
     */
    public Moment moment() {
        return moment;
    }
}
