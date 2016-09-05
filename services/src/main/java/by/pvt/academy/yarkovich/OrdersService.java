package by.pvt.academy.yarkovich;

import by.pvt.academy.yarkovich.dao.AcceptedOrderDAO;
import by.pvt.academy.yarkovich.entity.AcceptedOrder;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by dima on 03.09.2016.
 */
public class OrdersService {

    private static OrdersService instance;

    public static OrdersService getInstance() {
        if (instance == null) {
            instance = new OrdersService();
        }
        return instance;
    }


    public List<AcceptedOrder> getAll() {
        Session session = HibernateUtil.getHibernateUtil().getSession();
        Transaction tx = session.beginTransaction();
        List <AcceptedOrder> list = AcceptedOrderDAO.getInstance().getAll(session);
        tx.commit();
        return list;
    }
}
