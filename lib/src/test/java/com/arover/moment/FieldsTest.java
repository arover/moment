package com.arover.moment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author arover
 *         created at 8/30/16 00:32
 */

public class FieldsTest {
    private Moment mMoment;

    @Before
    public void setup(){
        mMoment = new Moment(2016, 7, 26);
    }

    @Test
    public void year() throws Exception {
        assertEquals(2016, mMoment.fields().year());
    }

    @Test
    public void month() throws Exception {
        assertEquals(8, mMoment.fields().month().getNumber());
        assertEquals(7, mMoment.fields().month().getIndex());
    }

    @Test
    public void day() throws Exception {
        assertEquals(26, mMoment.fields().day());
    }

    @Test
    public void hour() throws Exception {
        assertEquals(0, mMoment.fields().hour());
    }


    @Test
    public void min() throws Exception {
        assertEquals(0, mMoment.fields().hour());
    }

    @Test
    public void second() throws Exception {
        assertEquals(0, mMoment.fields().second());
    }

}