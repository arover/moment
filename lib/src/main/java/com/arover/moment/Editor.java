package com.arover.moment;

import java.util.Calendar;

/**
 * @author arover
 * created at 8/26/16 23:32
 */

public class Editor {

    private static final int YEAR = 1;
    private static final int MONTH = 2;
    private static final int DAY = 3;
    private static final int HOUR = 4;
    private static final int MINUTE = 5;
    private static final int SECOND = 6;
    private static final int MILISECOND = 7;

    private Moment mMoment;

    public Editor(Moment moment) {
        mMoment = moment;
    }

    /**
     * add x unit to moment.
     * @param x 0-n
     * @param unit time unit
     */
    public void plus(int x, int unit){
        
        switch (unit){
            case YEAR:
                mMoment.getCalendar().add(Calendar.YEAR, x);
                break;
            case MONTH:
                mMoment.getCalendar().add(Calendar.MONTH, x);
                break;
            case DAY:
                mMoment.getCalendar().add(Calendar.DAY_OF_YEAR, x);
                break;
            case HOUR:
                mMoment.getCalendar().add(Calendar.HOUR,x);
                break;
            case MINUTE:
                mMoment.getCalendar().add(Calendar.MINUTE,x);
                break;
            case SECOND:
                mMoment.getCalendar().add(Calendar.SECOND,x);
                break;
            case MILISECOND:
                mMoment.getCalendar().add(Calendar.MILLISECOND,x);
                break;
        }
    }

}
