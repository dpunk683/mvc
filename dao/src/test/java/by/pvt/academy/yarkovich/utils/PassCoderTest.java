package by.pvt.academy.yarkovich.utils;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by dima on 11.08.2016.
 */
public class PassCoderTest extends TestCase {
    public void testgetHashCode()
    {
        Assert.assertEquals("c4ca4238a0b923820dcc509a6f75849b", PassCoder.getHashCode("1"));
    }
}