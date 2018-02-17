package com.arover.moment;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueryTest {
    @Test
    public void isBefore() throws Exception{
        Moment moment = new Moment(2016,1,1);
        Moment earlier = new Moment(2015,1,1);
        assertTrue(earlier.query().isBefore(moment));

        moment = new Moment(2016,10,1);
        earlier = new Moment(2016,9,1);
        assertTrue(earlier.query().isBefore(moment));

        moment = new Moment(2016,10,2);
        earlier = new Moment(2016,10,1);
        assertTrue(earlier.query().isBefore(moment));
    }

    @Test
    public void firstDayOfMonth() throws Exception {
        Moment moment = new Moment(2016, Moment.AUGUST, 30);
        Moment day1 = moment.query().firstDayOfMonth();
        assertEquals(Moment.AUGUST, day1.fields().month());
        assertEquals(1, day1.fields().day());
    }

    @Test
    public void lastMonday() throws Exception {
        Moment moment = new Moment(2018,Moment.FEBRUARY, 17);
        Moment lastMonday = moment.query().lastMonday();
        assertEquals(2018, lastMonday.fields().year());
        assertEquals(Moment.FEBRUARY, lastMonday.fields().month());
        assertEquals(12, lastMonday.fields().day());
    }

    @Test
    public void daysOfMonth() throws Exception {
        assertEquals(28, Query.daysOfMonth(2018, Moment.FEBRUARY));
        assertEquals(31, Query.daysOfMonth(2018, Moment.JANUARY));
        assertEquals(31, Query.daysOfMonth(2018, Moment.MARCH));
        assertEquals(30, Query.daysOfMonth(2018, Moment.APRIL));
        assertEquals(31, Query.daysOfMonth(2018, Moment.MAY));
        assertEquals(30, Query.daysOfMonth(2018, Moment.JUNE));
        assertEquals(31, Query.daysOfMonth(2018, Moment.JULY));
        assertEquals(31, Query.daysOfMonth(2018, Moment.AUGUST));
        assertEquals(30, Query.daysOfMonth(2018, Moment.SEPTEMBER));
        assertEquals(31, Query.daysOfMonth(2018, Moment.OCTOBER));
        assertEquals(30, Query.daysOfMonth(2018, Moment.NOVEMBER));
        assertEquals(31, Query.daysOfMonth(2018, Moment.DECEMBER));
    }

    @Test
    public void isSameMoment() throws Exception {
        Moment moment = new Moment();

        assertEquals(true, moment.query().isSame(MomentUnit.YEAR, moment));
        assertEquals(true, moment.query().isSame(MomentUnit.MONTH, moment));
        assertEquals(true, moment.query().isSame(MomentUnit.DAY, moment));
        assertEquals(true, moment.query().isSame(MomentUnit.HOUR, moment));
        assertEquals(true, moment.query().isSame(MomentUnit.MINUTE, moment));
        assertEquals(true, moment.query().isSame(MomentUnit.SECOND, moment));
        assertEquals(true, moment.query().isSame(MomentUnit.MILLISECOND, moment));
    }


    @Test
    public void isAfter() throws Exception {
        Moment moment = new Moment();
        Moment twoDayBefore = moment.copy().edit().minus(2,MomentUnit.DAY).moment();
        Moment twoDayAfter = moment.copy().edit().add(2,MomentUnit.DAY).moment();

        System.out.println(moment.display().format());
        System.out.println(twoDayBefore.display().format());

        assertEquals(true, moment.query().isAfter(twoDayBefore));
        assertEquals(true, moment.query().isBefore(twoDayAfter));
        assertEquals(true, moment.query().isBeforeOrSame(moment));
        assertEquals(true, moment.query().isAfterOrSame(moment));
        assertEquals(true, moment.query().isAfterOrSame(twoDayBefore));

    }

}
