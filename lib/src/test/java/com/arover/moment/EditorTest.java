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
        mMoment = new Moment(2017,Month.MARCH,11);
    }

    @Test
    public void setYear() throws Exception {
        assertEquals(2018, mMoment.edit().year(2018).moment().fields().year());
    }

    @Test
    public void setMonth() throws Exception {
        assertEquals(Month.APRIL, mMoment.edit().month(Month.APRIL).moment().fields().month());
        assertEquals(0, mMoment.edit().month(0).moment().fields().month().index());
    }

    @Test
    public void setDay() throws Exception {
        assertEquals(9, mMoment.edit().day(9).moment().fields().day());
    }

    @Test
    public void setHour() throws Exception {
        assertEquals(11, mMoment.edit().hour(11).moment().fields().hour());
    }

    @Test
    public void setMinute() throws Exception {
        assertEquals(30, mMoment.edit().minute(30).moment().fields().minute());
    }

    @Test
    public void setSecond() throws Exception {
        assertEquals(55, mMoment.edit().second(55).moment().fields().second());
    }

    @Test
    public void minus() throws Exception {

        assertEquals(10, mMoment.edit().minus(1,MomentUnit.DAY).moment().fields().day());
        assertEquals(Month.FEBRUARY, mMoment.edit().minus(1,MomentUnit.MONTH).moment().fields().month());

        assertEquals(1, mMoment.edit().add(1,MomentUnit.HOUR).moment().fields().hour());
        assertEquals(2, mMoment.edit().add(2,MomentUnit.MINUTE).moment().fields().minute());
        assertEquals(5, mMoment.edit().add(5,MomentUnit.SECOND).moment().fields().second());
        assertEquals(2018, mMoment.edit().add(1,MomentUnit.YEAR).moment().fields().year());
    }
}
