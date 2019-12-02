package com.arover.moment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author arover
 *         created at 8/30/16 00:41
 */

public class EditorTest {
    private Moment moment;

    @Before
    public void setup() {
        moment = new Moment(2017,Moment.MARCH,11);
    }

    @Test
    public void setYear() throws Exception {
        assertEquals(2018, moment.edit().year(2018).moment().fields().year());
    }

    @Test
    public void setMonth() throws Exception {
        assertEquals(Moment.APRIL, moment.edit().month(Moment.APRIL).moment().fields()
                .month());
        assertEquals(0, moment.edit().month(0).moment().fields().month());
    }

    @Test
    public void setDay() throws Exception {
        assertEquals(9, moment.edit().day(9).moment().fields().day());
    }

    @Test
    public void setHour() throws Exception {
        assertEquals(11, moment.edit().hour(11).moment().fields().hour());
    }

    @Test
    public void setMinute() throws Exception {
        assertEquals(30, moment.edit().minute(30).moment().fields().minute());
    }

    @Test
    public void setSecond() throws Exception {
        assertEquals(55, moment.edit().second(55).moment().fields().second());
    }

    @Test
    public void minus() throws Exception {

        assertEquals(10, moment.edit().minus(1,MomentUnit.DAY).moment().fields().day());
        assertEquals(Moment.FEBRUARY, moment.edit().minus(1,MomentUnit.MONTH).moment()
                .fields().month());

        assertEquals(1, moment.edit().add(1,MomentUnit.HOUR).moment().fields().hour());
        assertEquals(2, moment.edit().add(2,MomentUnit.MINUTE).moment().fields().minute());
        assertEquals(5, moment.edit().add(5,MomentUnit.SECOND).moment().fields().second());
        assertEquals(2020, new Moment("2019-12-02","yyyy-MM-dd")
                .edit().add(1, MomentUnit.YEAR).moment().fields().year());
        assertEquals(2021,new Moment("2019-12-02","yyyy-MM-dd")
                .edit().add(20,MomentUnit.MONTH).moment().fields().year());
    }
}
