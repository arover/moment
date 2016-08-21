package com.arover.moment;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by minstrel on 8/20/16.
 */
public class MomentFormat {
    private static final String TAG = "MomentFormat";
    private final Moment mMoment;

    public MomentFormat(Moment moment) {
        mMoment = moment;
    }

    /**
     * @return "2014-09-0808:02:17-0500"
     */
    public String format() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return format.format(mMoment.getDate());
    }

    public String time() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return format.format(mMoment.getDate());
    }

    public String date() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(mMoment.getDate());
    }

    public String date(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        return formatter.format(mMoment.getDate());
    }

    public String format(String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return format.format(mMoment.getDate());
    }

    public String format(String dateFormat,Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, locale);
        return format.format(mMoment.getDate());
    }

    public String fromNow(Context context, Moment moment){
        Calendar now = Calendar.getInstance();
        long secs = (now.getTimeInMillis() - moment.getTimeInMillis()) / 1000;
        if(secs < 0){
            Log.e(TAG, "this moment is after now");
            return "";
        }else if(secs < 7){
            return context.getResources().getString(R.string.now);
        }else if(secs < 60){
            return context.getResources().getString(R.string.in_secs, secs);
        }else if(secs < 3600){
            return context.getResources().getString(R.string.in_mins, secs/60);
        }else if(secs < 24*3600){
            return context.getResources().getString(R.string.in_hours, secs/3600);
        }else if(now.get(Calendar.YEAR) == moment.getCalendar().get(Calendar.YEAR) &&
                now.get(Calendar.MONTH) == moment.getCalendar().get(Calendar.MONTH) &&
                now.get(Calendar.WEEK_OF_MONTH) == moment.getCalendar().get(Calendar.WEEK_OF_MONTH)){

            SimpleDateFormat formatter = new SimpleDateFormat("EEEHH:mm",Locale.getDefault());
            return formatter.format(moment.getDate());
        }else if(secs < 24*3600*30) {
            return context.getResources().getString(R.string.in_days, secs / 24 / 3600);
        }else if(now.get(Calendar.MONTH) - moment.getMonth().ordinal() > 0){
            SimpleDateFormat formatter = new SimpleDateFormat("MMM",Locale.getDefault());
            return formatter.format(moment.getDate());
        }else if(now.get(Calendar.YEAR) - moment.getYear() > 0){
            return context.getResources().getString(R.string.in_years, now.get(Calendar.YEAR) - moment.getYear());
        }else{
            return "";
        }
    }

    public String fromNow(Context context){
        return fromNow(context,mMoment);
    }
}
