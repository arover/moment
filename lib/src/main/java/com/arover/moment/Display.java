package com.arover.moment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by minstrel on 8/20/16.
 */
public class Display {
    private static final String TAG = "MomentFormat";
    private final Moment mMoment;

    /**
     * display action
     *
     * @param moment moment to display
     */
    public Display(Moment moment) {
        mMoment = moment;
    }

    /**
     * default format: yyyy-MM-dd HH:mm:ss ,eg: 2014-09-0808:02:17-0500
     *
     * @return formatted date text
     */
    public String format() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return format.format(mMoment.getDate());
    }

    /**
     * format date with format "M/d",eg: 9/2
     *
     * @return M/d "9/2"
     */
    public String shortestDate() {
        SimpleDateFormat format = new SimpleDateFormat("M/d", Locale.getDefault());
        return format.format(mMoment.getDate());
    }

    /**
     * format date with format "MMM d",eg: Sep 2
     *
     * @return "M/d" format date
     */
    public String date() {
        SimpleDateFormat format = new SimpleDateFormat("MMM d", Locale.getDefault());
        return format.format(mMoment.getDate());
    }

    /**
     * format date with format "HH:mm",eg: 22:30
     *
     * @return "HH:mm" format date
     */
    public String simpleTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return format.format(mMoment.getDate());
    }


    /**
     * format date with format "yyyy-MM-dd'T'HH:mm:ssZ",eg: 2016-09-02T22:30:20:202+0800
     *
     * @return formatted date
     */
    public String formatIso8601() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale
                .getDefault());
        return format.format(mMoment.getDate());
    }

    /**
     * format date with format "HH:mm:ss",eg: 22:30:20
     *
     * @return formatted date
     */
    public String time() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return format.format(mMoment.getDate());
    }

    /**
     * format date with format "yyyy-MM-dd",eg: 2016-09-02
     *
     * @return formatted date
     */
    public String dateIso() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(mMoment.getDate());
    }

    /**
     * format date with specific format
     *
     * @param dateFormat date format string
     * @return formatted date
     */
    public String format(@NonNull String dateFormat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.getDefault());
            return format.format(mMoment.getDate());
        }catch (Exception e){
            Log.e(TAG,"format date erro"+dateFormat,e);
            throw new InvalidParameterException("invalid date format:"+dateFormat);
        }
    }


    /**
     * format date with specific format and locale
     *
     * @param dateFormat date format string
     * @return formatted date
     */
    public String format(String dateFormat, Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, locale);
        return format.format(mMoment.getDate());
    }

    /**
     * A common way of displaying time
     *
     * @param context context for load string resource
     * @param moment  moment to compare with
     * @return timeago or relative time.
     */
    public String fromNow(Context context, Moment moment) {
        Calendar now = Calendar.getInstance();
        long secs = (now.getTimeInMillis() - moment.fields().timeInMillis()) / 1000;
        if (secs < 0) {
            Log.e(TAG, "this moment is after now");
            return "";
        } else if (secs < 15) {
            return context.getResources().getString(R.string.now);
        } else if (secs < 60) {
            return context.getResources().getString(R.string.in_secs, secs);
        } else if (secs < 3600) {
            return context.getResources().getString(R.string.in_mins, secs / 60);
        } else if (secs < 24 * 3600) {
            return context.getResources().getString(R.string.in_hours, secs / 3600);
        } else if (now.get(Calendar.YEAR) == moment.getCalendar().get(Calendar.YEAR) &&
                now.get(Calendar.MONTH) == moment.getCalendar().get(Calendar.MONTH) &&
                now.get(Calendar.WEEK_OF_MONTH) == moment.getCalendar().get(Calendar.WEEK_OF_MONTH)) {

            SimpleDateFormat formatter = new SimpleDateFormat("EEEHH:mm", Locale.getDefault());
            return formatter.format(moment.getDate());
        } else if (secs < 24 * 3600 * 30) {
            return context.getResources().getString(R.string.in_days, secs / 24 / 3600);
        } else if (now.get(Calendar.MONTH) - moment.fields().month()> 0) {
            SimpleDateFormat formatter = new SimpleDateFormat("MMM", Locale.getDefault());
            return formatter.format(moment.getDate());
        } else if (now.get(Calendar.YEAR) - moment.fields().year() > 0) {
            return context.getResources().getString(R.string.in_years, now.get(Calendar.YEAR) - moment.fields().year());
        } else {
            return "";
        }
    }

    /**
     * @param context for string resource
     * @see #fromNow(Context, Moment)
     */
    public String fromNow(Context context) {
        return fromNow(context, mMoment);
    }

    public long milliseconds(){
        return mMoment.getCalendar().getTimeInMillis();
    }
}
