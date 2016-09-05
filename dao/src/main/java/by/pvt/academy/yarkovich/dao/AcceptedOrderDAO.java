package by.pvt.academy.yarkovich.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

import by.pvt.academy.yarkovich.constants.HQLRequests;
import by.pvt.academy.yarkovich.constants.SQLRequests;
import by.pvt.academy.yarkovich.entity.AcceptedOrder;
import by.pvt.academy.yarkovich.exceptions.DAOException;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AcceptedOrderDAO extends BaseDao {

    private static AcceptedOrderDAO instance;

    public static AcceptedOrderDAO getInstance() {
        if (instance == null) {
            instance = new AcceptedOrderDAO();
        }
        return instance;
    }

    public void addOrder(AcceptedOrder acceptedOrder, String ip) {
        String currTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        acceptedOrder.setIp(ip);
        acceptedOrder.setStarttime(currTime);
        try {
            AcceptedOrderDAO.getInstance().saveOrUpdate(acceptedOrder);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public List<AcceptedOrder> getAll(Session session) {
        Query query = session.createQuery(SQLRequests.SQL_QUERY_GET_ORDERS);
        List<AcceptedOrder> acc_orders = query.list();
        return acc_orders;
    }


}
