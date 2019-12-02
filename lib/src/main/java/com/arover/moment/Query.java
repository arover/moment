package com.arover.moment;

import java.security.InvalidParameterException;
import java.util.Calendar;

/**
 * @author arover
 */
public class Query {
    private static final String TAG = "Query";
    private static int[] daysOfMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private final Calendar calendar;
    private Moment moment;

    public Query(Moment moment) {
        this.moment = moment;
        calendar = moment.getCalendar();
    }

    public Moment lastMonday() {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(calendar.getTimeInMillis());
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int offset = ((Calendar.MONDAY - dayOfWeek) - 7) % 7;
        cal.add(Calendar.DAY_OF_MONTH, offset);
        Util.setTimeToBeginningOfDay(cal);
        return new Moment(cal);
    }

    /**
     *
     * @param year
     * @param month
     * @return days of the specified month.
     */
    public static int daysOfMonth(int year, int month) {
        if (month == Calendar.FEBRUARY && isLeapYear(year)) {
            return 29;
        }
        return daysOfMonth[month];
    }

    public boolean isLeapYear() {
        int year = moment.fields().year();
        return isLeapYear(year);
    }

    private static boolean isLeapYear(int year) {
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
        cal.setTimeInMillis(calendar.getTimeInMillis());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Util.setTimeToBeginningOfDay(cal);
        return new Moment(cal);
    }

    /**
     * query two moment is same in same time unit.
     * "2016/10/1 10:20".isSame(DAY,"2016/10/1 9:10")? => true
     *
     * @param unit time unit
     * @param moment moment to compare
     * @return true if same with moment in specific time unit.
     */
    public boolean isSame(final @MomentUnit.Unit int unit, final Moment moment) {
        switch (unit) {
            case MomentUnit.MILLISECOND: {
                long millis = calendar.getTimeInMillis() - moment.getCalendar().getTimeInMillis();
                return millis == 0;
            }
            case MomentUnit.SECOND: {
                long millis = calendar.getTimeInMillis() - moment.getCalendar().getTimeInMillis();
                return Math.abs(millis) < 1000;
            }
            case MomentUnit.MINUTE: {
                long sec = calendar.getTimeInMillis() - moment.getCalendar().getTimeInMillis();
                return Math.abs(sec) < 1000 * 59;
            }
            case MomentUnit.HOUR: {
                long min = calendar.getTimeInMillis() - moment.getCalendar().getTimeInMillis();
                return Math.abs(min) < 1000 * 3600;
            }
            case MomentUnit.DAY: {
                if (this.moment.fields().year() == moment.fields().year()
                        && this.moment.fields().month() == moment.fields().month()
                        && this.moment.fields().day() == moment.fields().day()) {
                    return true;
                }
                return false;

            }
            case MomentUnit.MONTH: {
                if (this.moment.fields().year() == moment.fields().year()
                        && this.moment.fields().month() == moment.fields().month()) {
                    return true;
                }
                return false;
            }
            case MomentUnit.YEAR: {
                if (this.moment.fields().year() == moment.fields().year()) {
                    return true;
                }
                return false;
            }
            default:
                throw new InvalidParameterException("unknown time unit " + unit);
        }
    }

    public boolean isBefore(Moment moment) {
        return calendar.getTimeInMillis() < moment.getCalendar().getTimeInMillis();
    }

    public boolean isBeforeOrSame(Moment moment) {
        return calendar.getTimeInMillis() <= moment.getCalendar().getTimeInMillis();
    }

    public boolean isAfter(Moment moment) {
        return calendar.getTimeInMillis() > moment.getCalendar().getTimeInMillis();
    }

    public boolean isAfterOrSame(Moment moment) {
        return calendar.getTimeInMillis() >= moment.getCalendar().getTimeInMillis();
    }

    public Moment moment() {
        return moment;
    }
}
