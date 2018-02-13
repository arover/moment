package com.arover.moment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author arover
 *         created at 8/30/16 00:32
 */

public class FieldsTest {
    private Moment moment;

    @Before
    public void setup(){
        moment = new Moment(2016, 7, 26);
    }

    @Test
    public void year() throws Exception {
        assertEquals(2016, moment.fields().year());
    }

    @Test
    public void month() throws Exception {
        assertEquals(7, moment.fields().month());
    }

    @Test
    public void day() throws Exception {
        assertEquals(26, moment.fields().day());
    }

    @Test
    public void hour() throws Exception {
        assertEquals(0, moment.fields().hour());
    }


    @Test
    public void min() throws Exception {
        assertEquals(0, moment.fields().hour());
    }

    @Test
    public void second() throws Exception {
        assertEquals(0, moment.fields().second());
    }

}