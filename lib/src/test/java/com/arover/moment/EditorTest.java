package com.arover.moment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author arover
 *         created at 8/30/16 00:41
 */

public class EditorTest {
    private Moment mMoment;

    @Before
    public void setup() {
        mMoment = new Moment();
    }

    @Test
    public void setYear() throws Exception {
        assertEquals(2018, mMoment.edit().setYear(2018).getMoment().fields().year());
    }

    @Test
    public void setMonth() throws Exception {
        assertEquals(Month.APRIL, mMoment.edit().setMonth(Month.APRIL).getMoment().fields().month());
        assertEquals(0, mMoment.edit().setMonth(0).getMoment().fields().month().getIndex());
    }

    @Test
    public void setDay() throws Exception {
        assertEquals(9, mMoment.edit().setDay(9).getMoment().fields().day());
    }

    @Test
    public void setHour() throws Exception {
        assertEquals(11, mMoment.edit().setHour(11).getMoment().fields().hour());
    }

    @Test
    public void setMinute() throws Exception {
        assertEquals(30, mMoment.edit().setMinute(30).getMoment().fields().minute());
    }

    @Test
    public void setSecond() throws Exception {
        assertEquals(55, mMoment.edit().setSecond(55).getMoment().fields().second());
    }
}
