package com.arover.moment;

import android.util.Log;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;

/**
 * a date time class inspired by momentjs.
 * @author arover
 */
public class Moment implements Parcelable, Serializable{


    private final Calendar mCalendar;

    /**
     * construct a moment using the specific calendar instance
     * @param calendar specific the time
     */
    public Moment(Calendar calendar) {
        mCalendar = calendar;
    }

    /**
     * construct a moment using the default calendar instance
     */
    public Moment() {
        mCalendar = Calendar.getInstance();
    }

    /**
     * construct a moment using specified year, month, day.
     * @param   year    the year minus 1900.
     * @param   month   the month between 0-11.
     * @param   day    the day of the month between 1-31.
     */
    public Moment(int year, int month, int day) {
        mCalendar = Calendar.getInstance();
        mCalendar.set(year, month, day);
        Util.setTimeToBeginningOfDay(mCalendar);
    }


    public Moment(int year, Month month, int day) {
        mCalendar = Calendar.getInstance();
        mCalendar.set(year, month.getIndex(), day);
        Util.setTimeToBeginningOfDay(mCalendar);
    }

    /**
     * construct a moment with time in millisecond
     * @param timeInMillis time in millisecond
     */
    public Moment(long timeInMillis) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(timeInMillis);
    }

    /**
     * construct a moment with time in millisecond
     * @param timeInSeconds time in seconds
     */
    public Moment(int timeInSeconds){
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(timeInSeconds*1000);
    }

    protected Moment(Parcel in) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(in.readInt());
    }

    public static final Creator<Moment> CREATOR = new Creator<Moment>() {
        @Override
        public Moment createFromParcel(Parcel in) {
            return new Moment(in);
        }

        @Override
        public Moment[] newArray(int size) {
            return new Moment[size];
        }
    };

    /**
     *
     * @return a {@code Calendar} object of this moment
     */
    public Calendar getCalendar() {
        return mCalendar;
    }

    /**
     *
     * @return a date object of this moment
     */
    public Date getDate() {
        return new Date(mCalendar.getTimeInMillis());
    }

    /**
     *
     * @return editor to modify this moment.
     */
    public Editor edit(){
        return new Editor(this);
    }

    /**
     *
     * @return a query for you to do some queries.
     */
    public Query query(){
        return new Query(this);
    }

    /**
     *
     * @return a field getter for you to get field like year, month, day, etc.
     */
    public Field fields(){
        return new Field(this);
    }

    /**
     *
     * @return a display for you to show specific format
     */
    public Display display(){
        return new Display(this);
    }

    /**
     *
     * @return a copy of this moment.
     */
    public Moment copy(){
        return new Moment(mCalendar.getTimeInMillis());
    }

    /**
     *
     * @param moment the moment you want to copy
     * @return the copy of the moment
     */
    public static Moment copy(Moment moment){
        return new Moment(moment.getCalendar().getTimeInMillis());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mCalendar.getTimeInMillis());
    }

    /**
     *
     * @return format as iso 8601
     */
    @Override
    public String toString() {
        return new Display(this).formatIso8601();
    }
}
