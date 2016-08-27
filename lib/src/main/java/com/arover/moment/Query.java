package com.arover.moment;

import java.util.Calendar;

/**
 * @author arover
 */
public class Query {
    static int[] sDaysOfMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private final Calendar mCalendar;
    private Moment mMoment;

    public Query(Moment moment) {
        mMoment = moment;
        mCalendar = moment.getCalendar();
    }

    public Moment lastMonday(){

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mCalendar.getTimeInMillis());
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int offset = ((Calendar.MONDAY-dayOfWeek)-7)%7;
        cal.add(Calendar.DAY_OF_MONTH, offset);
        Util.setTimeToBeginningOfDay(cal);
        return new Moment(cal);
    }

    public static int daysOfMonth(int year, int month) {
        if (month == Calendar.FEBRUARY && year % 4 == 0) {
            return 29;
        }
        return sDaysOfMonth[month];
    }

    public boolean isLeapYear(){
        return mMoment.fields().year() % 4 == 0;
    }

    public Moment firstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mCalendar.getTimeInMillis());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Util.setTimeToBeginningOfDay(cal);
        return new Moment(cal);
    }

    public boolean isBefore(){
        // TODO: 8/27/16 isBefore
        return false;
    }

    public boolean isBeforeOrSame(){
        // TODO: 8/27/16 isBeforeOrSame
        return false;
    }

    public boolean isBeforeIn(MomentUnit momentUnit){
        // TODO: 8/27/16 isBeforeIn momentUnit
        return false;
    }

    public boolean isBeforeOrSameIn(MomentUnit momentUnit){
        // TODO: 8/27/16 isBeforeOrSameIn momentUnit
        return false;
    }

    public boolean isAfter(){
        // TODO: 8/27/16 isAfter
        return false;
    }


    public boolean isAfterOrSame(){
        // TODO: 8/27/16 isAfterOrSame
        return false;
    }

    public boolean isAfterIn(MomentUnit momentUnit){
        // TODO: 8/27/16 isAfter in momentUnit;
        return false;
    }


    public boolean isAfterOrSameIn(MomentUnit momentUnit){
        // TODO: 8/27/16 isAfterOrSameIn MomentUnit unit
        return false;
    }
}
