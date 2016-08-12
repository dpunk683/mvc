package by.pvt.academy.yarkovich.connection;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.sql.Connection;

/**
 * Created by dima on 11.08.2016.
 */
public class DBConnectionPoolTest extends TestCase {
    protected static DBConnectionPool poolInstance;

    public void testFreeConnection() throws Exception {
        poolInstance = DBConnectionPool.getInstance();
        Connection connection = poolInstance.getConnection();
        poolInstance.freeConnection(connection);
        Assert.assertEquals(false, connection.isClosed());
    }

}