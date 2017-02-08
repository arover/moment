package com.arover.moment;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * a date time class inspired by momentjs.
 *
 * @author arover
 */
public class Moment implements Parcelable, Serializable {

    /**
     * parcelable methods.
     */
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
     * calendar object to storage time.
     */
    private final Calendar mCalendar;

    /**
     * construct a moment using the specific calendar instance.
     *
     * @param calendar specific the time
     */
    public Moment(final Calendar calendar) {
        mCalendar = calendar;
    }

    /**
     * construct a moment using the default calendar instance.
     */
    public Moment() {
        mCalendar = Calendar.getInstance();
    }

    /**
     * construct a moment using specified year, month, day.
     *
     * @param year  the year minus 1900.
     * @param month the month between 0-11.
     * @param day   the day of the month between 1-31.
     */
    public Moment(final int year, final int month, final int day) {
        mCalendar = Calendar.getInstance();
        mCalendar.set(year, month, day);
        Util.setTimeToBeginningOfDay(mCalendar);
    }

    /**
     *
     * @param year  the year minus 1900.
     * @param month Month enum, JANUARY - DECEMBER
     * @param day   the day of the month between 1-31.
     */
    public Moment(final int year, final Month month, final int day) {
        mCalendar = Calendar.getInstance();
        mCalendar.set(year, month.index(), day);
        Util.setTimeToBeginningOfDay(mCalendar);
    }

    /**
     * construct a moment with time in millisecond.
     *
     * @param timeInMillis time in millisecond
     */
    public Moment(final long timeInMillis) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(timeInMillis);
    }

    /**
     * construct a moment with time in seconds.
     *
     * @param timeInSeconds time in seconds
     */
    public Moment(final int timeInSeconds) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(timeInSeconds * 1000);
    }

    /**
     * construct a moment by parsing date text with specific format and default locale.
     *
     * @param dateText date text to be parse
     * @param format the format of date text.
     */
    public Moment(final String dateText, final String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        Date date = formatter.parse(dateText);
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(date.getTime());
    }

    /**
     * construct a moment by parsing date text with specific format and locale.
     *
     * @param dateText date text to be parse
     * @param format the format of date text.
     * @param locale locale for format
     */
    public Moment(final String dateText, final String format, final Locale locale)
            throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
        Date date = formatter.parse(dateText);
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(date.getTime());
    }

    /**
     * parcelable methods.
     * @param in parcel
     */
    protected Moment(final Parcel in) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(in.readInt());
    }

    /**
     * @return a {@code Calendar} object of this moment
     */
    public Calendar getCalendar() {
        return mCalendar;
    }

    /**
     * @return a date object of this moment
     */
    public Date getDate() {
        return new Date(mCalendar.getTimeInMillis());
    }

    /**
     *
     * @return chinese lunar calendar
     */
    public Lunar getLunar(){
        return new Lunar(mCalendar.getTimeInMillis());
    }
    /**
     *
     * @return editor to modify this moment.
     */
    public Editor edit() {
        return new Editor(this);
    }

    /**
     * @return a query for you to do some queries.
     */
    public Query query() {
        return new Query(this);
    }

    /**
     * @return a field getter for you to get field like year, month, day, etc.
     */
    public Field fields() {
        return new Field(this);
    }

    /**
     * @return a display for you to show specific format
     */
    public Display display() {
        return new Display(this);
    }

    /**
     * @return a copy of this moment.
     */
    public Moment copy() {
        return new Moment(mCalendar.getTimeInMillis());
    }

    /**
     * @param moment the moment you want to copy
     * @return the copy of the moment
     */
    public static Moment copy(final Moment moment) {
        return new Moment(moment.getCalendar().getTimeInMillis());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeLong(mCalendar.getTimeInMillis());
    }

    /**
     * @return format as iso 8601
     */
    @Override
    public String toString() {
        return new Display(this).formatIso8601();
    }
}
