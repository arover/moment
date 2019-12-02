package com.arover.moment;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by minstrel on 8/27/16.
 */
public class MomentUnit {


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, MILLISECOND,WEEK})
    public @interface Unit {}

    public static final int YEAR = 1;
    public static final int MONTH = 2;
    public static final int DAY = 3;
    public static final int HOUR = 4;
    public static final int MINUTE = 5;
    public static final int SECOND = 6;
    public static final int MILLISECOND = 7;
    public static final int WEEK = 8;
}
