package com.arover.moment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author arover
 *         created at 9/4/16 23:19
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DisplayTest.class,
        EditorTest.class,
        FieldsTest.class,
        QueryTest.class,
        MomentTest.class
})
public class AllTest {

}
