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

    /**
     * construct a moment editor
     * @param moment for edit
     */
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
     */
    public Editor minus(int n, int unit) {
        if(n > 0){
            n = -n;
        }
        set(-n, unit);
        return this;
    }

    /**
     * set time in millisecond
     * @param timeInMillis time in millisecond
     * @return editor for chain
     */
    public Editor timeInMillis(long timeInMillis) {
        if (timeInMillis < 0)
            throw new InvalidParameterException("can't time in millisecond: " + timeInMillis);

        mCalendar.setTimeInMillis(timeInMillis);
        return this;
    }

    /**
     * set time in seconds
     * @param timeInSeconds time in seconds
     * @return editor for chain
     */
    public Editor timeInSeconds(int timeInSeconds) {
        if (timeInSeconds < 0)
            throw new InvalidParameterException("can't time in seconds: " + timeInSeconds);

        mCalendar.setTimeInMillis(timeInSeconds*1000);
        return this;
    }

    /**
     * set the second of the time
     * @param second 0-59
     * @return editor for chain
     */
    public Editor second(int second) {
        if (second < 0 || second > 59)
            throw new InvalidParameterException("can't sec second to " + second);
        mCalendar.set(Calendar.SECOND, second);
        return this;
    }
    /**
     * set the minute of the time
     * @param min minute , [0,59]
     * @return editor for chain
     */
    public Editor minute(int min) {
        if (min < 0 || min > 59)
            throw new InvalidParameterException("can't sec minute to " + min);
        mCalendar.set(Calendar.MINUTE, min);
        return this;
    }

    /**
     * set the hour of day
     * @param hour [0,23]
     * @return editor for chain
     */
    public Editor hour(int hour) {
        if (hour < 0 || hour > 23)
            throw new InvalidParameterException("can't sec hour to " + hour);
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
        return this;
    }

    /**
     * set the day of month
     * @param day [1,31]
     * @return editor for chain
     */
    public Editor day(int day) {
        if (day < 0 || day > 31)
            throw new InvalidParameterException("can't set day to " + day);

        mCalendar.set(Calendar.DAY_OF_MONTH, day);
        return this;
    }
    /**
     * set the day of year
     * @param day [1,366]
     * @return editor for chain
     */
    public Editor dayOfYear(int day) {
        if (day < 0 || day > 366)
            throw new InvalidParameterException("can't sec day to " + day);

        mCalendar.set(Calendar.DAY_OF_YEAR, day);
        return this;
    }

    /**
     * set month of moment
     * @return editor for chain
     */
    public Editor month(Month month) {
        mCalendar.set(Calendar.MONTH, month.ordinal());
        return this;
    }

    /**
     * set month of moment
     * @param month [Calendar.JANUARY,Calendar.DECEMBER]
     *              0-11
     */
    public Editor month(int month) {
        mCalendar.set(Calendar.MONTH, month);
        return this;
    }

    /**
     * set time to the beginning of the day(00:00:00).
     */
    public Editor setBeginningOfDay() {
        Util.setTimeToBeginningOfDay(mCalendar);
        return this;
    }

    /**
     * set time to the beginning of the day(23:59:59).
     * @return editor for chain
     */
    public Editor setEndOfDay() {
        Util.setTimeToEndOfDay(mCalendar);
        return this;
    }

    /**
     * change the year
     * @param year [0,...]
     * @return editor for chain
     */
    public Editor year(int year) {
        mCalendar.set(Calendar.YEAR,year);
        return this;
    }

    /**
     * @return the moment
     */
    public Moment moment(){
        return mMoment;
    }
}
