package com.arover.moment;

import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * a date time class inspired by momentjs.
 */
public class Moment {
    private final Calendar mCalendar;
    private static int[] sDaysOfMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

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

    public long getMillisecond() {
        return mCalendar.getTimeInMillis();
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

    public int getYear(){
        return mCalendar.get(Calendar.YEAR);
    }

    public boolean isLeapYear(){
        return getYear() % 4 == 0;
    }

    public void setMillisecond(long timeInMillis){
        if(timeInMillis < 0 )
            throw new InvalidParameterException("can't millisecond to "+timeInMillis);

        mCalendar.setTimeInMillis(timeInMillis);
    }

    public void setSecond(int sec){
        if(sec < 0 || sec >59)
            throw new InvalidParameterException("can't sec second to "+sec);
        mCalendar.set(Calendar.SECOND,sec);
    }

    public void setMinute(int min){
        if(min < 0 || min >59)
            throw new InvalidParameterException("can't sec minute to "+min);
        mCalendar.set(Calendar.MINUTE,min);
    }

    /**
     *
     * @param hour [0,23]
     */
    public void setHour(int hour){
        if(hour < 0 || hour >23)
            throw new InvalidParameterException("can't sec hour to "+hour);
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
    }

    public void setDay(int day){
        if(day < 0 || day >23)
            throw new InvalidParameterException("can't sec minute to "+day);

        mCalendar.set(Calendar.DAY_OF_MONTH, day);
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
        return new MomentFormat(this).format();
    }

    public String format(String dateFormat){
        return new MomentFormat(this).format(dateFormat);
    }

    public String format(String dateFormat, Locale locale){
        return new MomentFormat(this).format(dateFormat,locale);
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
