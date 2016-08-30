package com.arover.moment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * @author arover
 *         created at 8/30/16 00:59
 */

public class DisplayTest {
    private Moment mMoment;

    @Before
    public void setup() {
        mMoment = new Moment();
    }

    @Test
    public void date() throws Exception{
        String date = mMoment.display().shortestDate();
        System.out.println(date);
    }
}
