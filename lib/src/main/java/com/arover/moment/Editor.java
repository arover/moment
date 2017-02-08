package com.arover.moment;

import java.security.InvalidParameterException;
import java.util.Calendar;

/**
 * @author arover
 *         created at 8/26/16 23:32
 */

public class Editor {

    private Moment mMoment;
    private Calendar mCalendar;

    public Editor(Moment moment) {
        mMoment = moment;
        mCalendar = moment.getCalendar();
    }

    /**
     * add x unit to moment.
     *
     * @param x    0-n
     * @param unit time unit
     */
    private void set(int x, int unit) {
        switch (unit) {
            case MomentUnit.YEAR: {
                int year = mCalendar.get(Calendar.YEAR);
                mCalendar.set(Calendar.YEAR, year + x);
                break;
            }
            case MomentUnit.MONTH: {
                int months = x % 12;
                int year = x / 12;

                int month = mCalendar.get(Calendar.MONTH);
                if (month + x > Calendar.DECEMBER) {
                    mCalendar.set(Calendar.YEAR, mCalendar.get(Calendar.YEAR) + 1);
                    mCalendar.set(Calendar.MONTH, (month + x) % 11);
                } else if (month + x < Calendar.JANUARY) {
                    mCalendar.set(Calendar.YEAR, mCalendar.get(Calendar.YEAR) - 1);
                    mCalendar.set(Calendar.MONTH, (month + x) % 11);
                } else {
                    mCalendar.set(Calendar.MONTH, (month + x));
                }
                break;
            }
            case MomentUnit.DAY:
                mCalendar.add(Calendar.DAY_OF_YEAR, x);
                break;
            case MomentUnit.HOUR:
                mCalendar.add(Calendar.HOUR, x);
                break;
            case MomentUnit.MINUTE:
                mCalendar.add(Calendar.MINUTE, x);
                break;
            case MomentUnit.SECOND:
                mCalendar.add(Calendar.SECOND, x);
                break;
            case MomentUnit.MILISECOND:
                mCalendar.add(Calendar.MILLISECOND, x);
                break;
        }
    }

    /**
     *
     * @param n the amount to add.
     *          negative number will be transforming to positive.
     * @param unit time unit.
     * @return Editor for chain
     */
    public Editor add(int n, int unit) {
        if(n < 0){
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
        if(n > 0){
            n = -n;
        }
        set(n, unit);
        return this;
    }

    /**
     *
     * @param timeInMillis time in milliseconds
     * @return Editor for chain
     */
    public Editor setMillisecond(long timeInMillis) {
        if (timeInMillis < 0)
            throw new InvalidParameterException("can't millisecond to " + timeInMillis);

        mCalendar.setTimeInMillis(timeInMillis);
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
        mCalendar.set(Calendar.SECOND, sec);
        return this;
    }

    /**
     *
     * @param min minute [0,59]
     * @return Editor for chain
     */
    public Editor setMinute(int min) {
        if (min < 0 || min > 59)
            throw new InvalidParameterException("can't sec minute to " + min);
        mCalendar.set(Calendar.MINUTE, min);
        return this;
    }

    /**
     * @param hour [0,23]
     * @return Editor for chain
     */
    public Editor setHour(int hour) {
        if (hour < 0 || hour > 23)
            throw new InvalidParameterException("can't sec hour to " + hour);
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
        return this;
    }

    /**
     *
     * @param day day of month
     * @return Editor for chain
     */
    public Editor setDay(int day) {
        if (day < 1 || day > 31)
            throw new InvalidParameterException("can't sec day to " + day);

        mCalendar.set(Calendar.DAY_OF_MONTH, day);
        return this;
    }

    /**
     *
     * @param day day of year
     * @return Editor for chain
     */
    public Editor setDayOfYear(int day) {
        if (day < 1 || day > 366)
            throw new InvalidParameterException("can't sec day to " + day);

        mCalendar.set(Calendar.DAY_OF_YEAR, day);
        return this;
    }

    /**
     *
     * @param month month
     * @return Editor for chain
     */
    public Editor setMonth(Month month) {
        mCalendar.set(Calendar.MONTH, month.ordinal());
        return this;
    }

    /**
     * @param month [Calendar.JANUARY,Calendar.DECEMBER]
     * @return Editor for chain
     */
    public Editor setMonth(int month) {
        mCalendar.set(Calendar.MONTH, month);
        return this;
    }

    /**
     * @return Editor for chain
     */
    public Editor setBeginningOfDay() {
        Util.setTimeToBeginningOfDay(mCalendar);
        return this;
    }

    /**
     * @return Editor for chain
     */
    public Editor setEndOfDay() {
        Util.setTimeToEndOfDay(mCalendar);
        return this;
    }

    public Editor setYear(int year) {
        mCalendar.set(Calendar.YEAR,year);
        return this;
    }

    /**
     *
     * @return edit moment
     */
    public Moment getMoment() {
        return mMoment;
    }
}
