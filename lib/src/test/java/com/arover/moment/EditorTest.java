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
        assertEquals(2018, mMoment.edit().setYear(2018).fields().year());
    }

    @Test
    public void setMonth() throws Exception {
        assertEquals(Month.APRIL, mMoment.edit().setMonth(Month.APRIL).fields().month());
        assertEquals(0, mMoment.edit().setMonth(0).fields().month().getIndex());
    }

    @Test
    public void setDay() throws Exception {
        assertEquals(9, mMoment.edit().setDay(9).fields().day());
    }

    @Test
    public void setHour() throws Exception {
        assertEquals(11, mMoment.edit().setHour(11).fields().hour());
    }

    @Test
    public void setMinute() throws Exception {
        assertEquals(30, mMoment.edit().setMinute(30).fields().minute());
    }

    @Test
    public void setSecond() throws Exception {
        assertEquals(55, mMoment.edit().setSecond(55).fields().second());
    }
}
