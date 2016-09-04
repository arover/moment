package com.arover.moment;

import java.security.InvalidParameterException;
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

    public int daysOfMonth(int year, int month) {
        if (month == Calendar.FEBRUARY && isLeapYear(year)) {
            return 29;
        }
        return sDaysOfMonth[month];
    }

    public int daysOfMonth(int year, Month month) {
        return daysOfMonth(year, month.index());
    }

    public boolean isLeapYear(){
        int year = mMoment.fields().year();
        return isLeapYear(year);
    }

    private boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }

    public Moment firstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mCalendar.getTimeInMillis());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Util.setTimeToBeginningOfDay(cal);
        return new Moment(cal);
    }

    /**
     * query two moment is same in same time unit.
     * "2016/10/1 10:20".isSame(DAY,"2016/10/1 9:10")? => true
     * @param unit
     * @param moment
     * @return
     */
    public boolean isSame(int unit,Moment moment){
        switch (unit) {
            case MomentUnit.SECOND: {
                long millis = mCalendar.getTimeInMillis() - moment.getCalendar().getTimeInMillis();
                if (Math.abs(millis) < 1000)
                    return true;
                return false;
            }
            case MomentUnit.MINUTE: {
                long sec = mCalendar.getTimeInMillis() - moment.getCalendar().getTimeInMillis();
                if (Math.abs(sec) < 1000 * 59)
                    return true;
                return false;
            }
            case MomentUnit.HOUR: {
                long min = mCalendar.getTimeInMillis() - moment.getCalendar().getTimeInMillis();
                if (Math.abs(min) < 1000 * 3600)
                    return true;
                return false;
            }
            case MomentUnit.DAY: {
                if (mMoment.fields().year() == moment.fields().year()
                        && mMoment.fields().month() == moment.fields().month()
                        && mMoment.fields().day() == moment.fields().day()) {
                    return true;
                }
                return false;

            }
            case MomentUnit.MONTH: {
                if (mMoment.fields().year() == moment.fields().year()
                        && mMoment.fields().month() == moment.fields().month()) {
                    return true;
                }
                return false;
            }
            case MomentUnit.YEAR: {
                if (mMoment.fields().year() == moment.fields().year()) {
                    return true;
                }
                return false;
            }
        }
        throw new InvalidParameterException("unknown time unit "+unit);
    }

    public boolean isBefore(Moment moment){
        return mCalendar.getTimeInMillis() < moment.getCalendar().getTimeInMillis();
    }

    public boolean isBeforeOrSame(Moment moment){
        return mCalendar.getTimeInMillis() <= moment.getCalendar().getTimeInMillis();
    }

    public boolean isAfter(Moment moment){
        return mCalendar.getTimeInMillis() > moment.getCalendar().getTimeInMillis();
    }

    public boolean isAfterOrSame(Moment moment){
        return mCalendar.getTimeInMillis() >= moment.getCalendar().getTimeInMillis();
    }

    public Moment moment(){
        return mMoment;
    }
}
