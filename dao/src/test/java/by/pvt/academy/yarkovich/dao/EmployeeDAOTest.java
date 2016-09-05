//package by.pvt.academy.yarkovich.dao;
//
//import by.pvt.academy.yarkovich.entity.Employee;
//import junit.framework.Assert;
//import junit.framework.TestCase;
//
///**
// * Created by dima on 11.08.2016.
// */
//public class EmployeeDAOTest extends TestCase {
//    public void testGetWaiter() throws Exception {
//        Employee employee = EmployeeDAO.getInstance().getWaiter(1);
//
//        Assert.assertEquals(1, employee.getId());
//        Assert.assertEquals("manager", employee.getLogin());
//        Assert.assertEquals(1, employee.getType());
//    }
//
//        public void testGetWaiter1() throws Exception {
//        Employee employee = EmployeeDAO.getInstance().getWaiter("waiter","1");
//
//        Assert.assertEquals(2, employee.getId());
//        Assert.assertEquals("waiter", employee.getLogin());
//        Assert.assertEquals(2, employee.getType());
//    }
//
//}