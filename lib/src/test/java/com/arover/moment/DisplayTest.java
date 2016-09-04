package com.arover.moment;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
/**
 * @author arover
 *         created at 8/30/16 00:59
 */

public class DisplayTest {

    @Test
    public void date() throws Exception{
        Moment moment = new Moment(2016,8,2);
        String date = moment.display().shortestDate();
        System.out.println(date);
        assertEquals("9/2",date);
        date = moment.display().format("yyyy/MM/dd");
        assertEquals("2016/09/02",date);

        assertEquals("2016-09-02 00:00:00",moment.display().format());
        if(Locale.ENGLISH == Locale.getDefault()) {
            assertEquals("Sep 2", moment.display().date());
        }
        moment.edit().hour(22).minute(30);

        assertEquals("22:30",moment.display().simpleTime());

    }
}
