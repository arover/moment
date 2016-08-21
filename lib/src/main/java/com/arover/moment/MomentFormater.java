package com.arover.moment;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by minstrel on 8/20/16.
 */
public class MomentFormater {
    private final Moment mMoment;

    public MomentFormater(Moment moment) {
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

    public String dateWest() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        return format.format(mMoment.getDate());
    }
    public String format(String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return format.format(mMoment.getDate());
    }

    public String format(String dateFormat,Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, locale);
        return format.format(mMoment.getDate());
    }
}
