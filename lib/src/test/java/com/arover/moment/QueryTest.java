package com.arover.moment;

import org.junit.Test;

import static org.junit.Assert.*;

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
        Moment moment = new Moment(2016, Month.AUGUST, 30);
        Moment day1 = moment.query().firstDayOfMonth();
        assertEquals(8, day1.fields().month().number());
        assertEquals(1, day1.fields().day());
    }
}
