package com.arover.moment;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * a date time utility inspired by Momentjs.
 *
 * @author arover
 */
public class Moment implements Parcelable, Serializable {


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST,
            SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER})
    public @interface Month {}

    public static final int JANUARY = 0;
    public static final int FEBRUARY = 1;
    public static final int MARCH = 2;
    public static final int APRIL = 3;
    public static final int MAY = 4;
    public static final int JUNE = 5;
    public static final int JULY = 6;
    public static final int AUGUST = 7;
    public static final int SEPTEMBER = 8;
    public static final int OCTOBER = 9;
    public static final int NOVEMBER = 10;
    public static final int DECEMBER = 11;

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
    private final Calendar calendar;

    /**
     * construct a moment by calendar.
     *
     * @param calendar specific the time
     */
    public Moment(final Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * construct a moment by date.
     *
     * @param date date
     */
    public Moment(final Date date) {
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
    }

    /**
     * construct a moment using the default calendar instance.
     */
    public Moment() {
        calendar = Calendar.getInstance();
    }

    /**
     * construct a moment using specified year, month, day.
     *
     * @param year  the minimum year is 1900.
     * @param month the month between 0-11.
     * @param day   the day of the month between 1-31.
     */
    public Moment(final int year, @IntRange(from=0, to=11) final int month,
                  @IntRange(from=1, to=31)
    final int
            day) {
        calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Util.setTimeToBeginningOfDay(calendar);
    }

    /**
     * construct a moment by time in millisecond.
     *
     * @param timeInMillis time in millisecond
     */
    public Moment(final long timeInMillis) {
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
    }

    /**
     * construct a moment by time in seconds.
     *
     * @param timeInSeconds time in seconds
     */
    public Moment(final int timeInSeconds) {
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInSeconds * 1000);
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
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
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
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
    }

    /**
     * Returns milliseconds since boot, including time spent in sleep.
     *
     * @return elapsed milliseconds since boot.
     */
    public long getElapsedRealtimeInMs(){
        return SystemClock.elapsedRealtime();
    }

    /**
     * Returns nanoseconds since boot, including time spent in sleep.
     *
     * @return elapsed nanoseconds since boot.
     */
    public long getElapsedRealtimeInNanos(){
        return SystemClock.elapsedRealtimeNanos();
    }

    /**
     * parcelable methods.
     * @param in parcel
     */
    protected Moment(final Parcel in) {
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(in.readInt());
    }

    /**
     * @return a {@code Calendar} object of this moment
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * @return a date object of this moment
     */
    public Date getDate() {
        return new Date(calendar.getTimeInMillis());
    }

    /**
     *
     * @return chinese lunar calendar
     */
    public Lunar getLunar(){
        return new Lunar(calendar.getTimeInMillis());
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
        return new Moment(calendar.getTimeInMillis());
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
        dest.writeLong(calendar.getTimeInMillis());
    }

    /**
     * @return format as iso 8601
     */
    @Override
    public String toString() {
        return new Display(this).formatIso8601();
    }

    /**
     *
     * @return time from now in seconds.
     */
    public long timeFromNowInSeconds(){
        return (new Date().getTime() - calendar.getTimeInMillis())/1000;
    }
}
