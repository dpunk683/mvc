package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.entity.Personal;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by dima on 11.08.2016.
 */
public class PersonalDAOTest extends TestCase {
    public void testGetWaiter() throws Exception {
        Personal personal = PersonalDAO.getInstance().getWaiter(1);

        Assert.assertEquals(1, personal.getId());
        Assert.assertEquals("manager", personal.getLogin());
        Assert.assertEquals(1, personal.getType());
    }

        public void testGetWaiter1() throws Exception {
        Personal personal = PersonalDAO.getInstance().getWaiter("waiter","1");

        Assert.assertEquals(2, personal.getId());
        Assert.assertEquals("waiter", personal.getLogin());
        Assert.assertEquals(2, personal.getType());
    }

}