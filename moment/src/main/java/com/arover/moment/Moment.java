package com.arover.moment;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * a date time class inspired by momentjs.
 */
public class Moment {
    private final Calendar mCalendar;
    static int[] sDaysOfMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private DayOfWeek mFirstDayOfWeek = DayOfWeek.SUNDAY;

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

    public Moment getLastMonday(){

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mCalendar.getTimeInMillis());
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int offset = ((Calendar.MONDAY-dayOfWeek)-7)%7;
        cal.add(Calendar.DAY_OF_MONTH, offset);
        setTimeToBeginningOfDay(cal);
        return new Moment(cal);
    }

    public void setFirstDayOfWeek(DayOfWeek dayOfWeek){
        mFirstDayOfWeek = dayOfWeek;
    }

    public int getDaysOfMonth(int year, int month) {
        if (month == Calendar.FEBRUARY && year % 4 == 0) {
            return 29;
        }
        return sDaysOfMonth[month];
    }

    public Date getDate(){
        return new Date(mCalendar.getTimeInMillis());
    }

    public long getTimeInSeconds() {
        return mCalendar.getTimeInMillis() / 1000;
    }

    public int getSecond(){
        return mCalendar.get(Calendar.SECOND);
    }

    public int getMinute(){
        return mCalendar.get(Calendar.MINUTE);
    }

    public int getHour(){
        return mCalendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getDay(){
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public Month getMonth(){
        return Month.from(mCalendar.get(Calendar.MONTH));
    }

    public void setMonth(Month month){
        mCalendar.set(Calendar.MONTH, month.ordinal());
    }

    /**
     *
     * @param month [Calendar.JANUARY,Calendar.DECEMBER]
     */
    public void setMonth(int month){
        mCalendar.set(Calendar.MONTH, month);
    }

    public long getMillisecond() {
        return mCalendar.getTimeInMillis();
    }

    public void setMillisecond(long timeInMillis){
        mCalendar.setTimeInMillis(timeInMillis);
    }

    public Moment setBeginningOfDay() {
        setTimeToBeginningOfDay(mCalendar);
        return this;
    }

    public Moment setEndOfDay() {
        setTimeToEndOfDay(mCalendar);
        return this;
    }

    public Moment getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mCalendar.getTimeInMillis());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        setTimeToBeginningOfDay(cal);
        return new Moment(cal);
    }

    public String format(){
        return new MomentFormater(this).format();
    }

    public String format(String dateFormat){
        return new MomentFormater(this).format(dateFormat);
    }

    public String format(String dateFormat, Locale locale){
        return new MomentFormater(this).format(dateFormat,locale);
    }

    private void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
    }

    private void setTimeToEndOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
    }
}
