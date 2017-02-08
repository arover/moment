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
<<<<<<< HEAD
        assertEquals(2018, mMoment.edit().setYear(2018).getMoment().fields().year());
=======
        assertEquals(2018, mMoment.edit().year(2018).moment().fields().year());
>>>>>>> origin/master
    }

    @Test
    public void setMonth() throws Exception {
<<<<<<< HEAD
        assertEquals(Month.APRIL, mMoment.edit().setMonth(Month.APRIL).getMoment().fields().month());
        assertEquals(0, mMoment.edit().setMonth(0).getMoment().fields().month().getIndex());
=======
        assertEquals(Month.APRIL, mMoment.edit().month(Month.APRIL).moment().fields().month());
        assertEquals(0, mMoment.edit().month(0).moment().fields().month().index());
>>>>>>> origin/master
    }

    @Test
    public void setDay() throws Exception {
<<<<<<< HEAD
        assertEquals(9, mMoment.edit().setDay(9).getMoment().fields().day());
=======
        assertEquals(9, mMoment.edit().day(9).moment().fields().day());
>>>>>>> origin/master
    }

    @Test
    public void setHour() throws Exception {
<<<<<<< HEAD
        assertEquals(11, mMoment.edit().setHour(11).getMoment().fields().hour());
=======
        assertEquals(11, mMoment.edit().hour(11).moment().fields().hour());
>>>>>>> origin/master
    }

    @Test
    public void setMinute() throws Exception {
<<<<<<< HEAD
        assertEquals(30, mMoment.edit().setMinute(30).getMoment().fields().minute());
=======
        assertEquals(30, mMoment.edit().minute(30).moment().fields().minute());
>>>>>>> origin/master
    }

    @Test
    public void setSecond() throws Exception {
<<<<<<< HEAD
        assertEquals(55, mMoment.edit().setSecond(55).getMoment().fields().second());
=======
        assertEquals(55, mMoment.edit().second(55).moment().fields().second());
>>>>>>> origin/master
    }
}
