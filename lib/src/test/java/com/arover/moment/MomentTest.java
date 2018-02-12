package com.arover.moment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @author arover
 *         created at 9/5/16 00:28
 */

public class MomentTest {
    @Test
    public void parse() throws Exception{
        Moment moment = new Moment("2016-09-02","yyyy-MM-dd");
        assertEquals(Moment.SEPTEMBER, moment.fields().month());
        assertEquals(2, moment.fields().day());
        assertEquals(2016, moment.fields().year());
    }
}
