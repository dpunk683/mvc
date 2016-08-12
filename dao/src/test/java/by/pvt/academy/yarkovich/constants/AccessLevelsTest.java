package by.pvt.academy.yarkovich.constants;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by dima on 12.08.2016.
 */
public class AccessLevelsTest extends TestCase {
    public void testConstants(){
        Assert.assertEquals(99,AccessLevels.GUEST);
        Assert.assertEquals(3,AccessLevels.COCK);
        Assert.assertEquals(2,AccessLevels.WAITER);
        Assert.assertEquals(1,AccessLevels.ADMINISTRATOR);
    }
    public void StatusToStringTest(){
        Assert.assertEquals("user.not.blocked",AccessLevels.StatusToString(1));
        Assert.assertEquals("user.not.blocked",AccessLevels.StatusToString(2));
        Assert.assertEquals("Unknown AccessLevel",AccessLevels.StatusToString(3));
        Assert.assertEquals("user.blocked",AccessLevels.StatusToString(99));
    }
}