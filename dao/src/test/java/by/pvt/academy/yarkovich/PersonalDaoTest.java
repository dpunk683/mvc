package by.pvt.academy.yarkovich;

import by.pvt.academy.yarkovich.dao.PersonalDAO;
import by.pvt.academy.yarkovich.entity.Personal;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class PersonalDaoTest
        extends TestCase
{
    public void testgetWaiter() throws SQLException
    {
        Personal personal = PersonalDAO.getInstance().getWaiter(1);

        Assert.assertEquals(1, personal.getId());
        Assert.assertEquals("manager", personal.getLogin());
        Assert.assertEquals(1, personal.getType());
        return;
    }

    public void testgetWaiter2() throws SQLException
    {
        Personal personal = PersonalDAO.getInstance().getWaiter("waiter","1");

        Assert.assertEquals(2, personal.getId());
        Assert.assertEquals("waiter", personal.getLogin());
        Assert.assertEquals(2, personal.getType());
        return;
    }
}
