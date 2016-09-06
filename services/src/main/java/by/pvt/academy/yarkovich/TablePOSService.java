package by.pvt.academy.yarkovich;

import by.pvt.academy.yarkovich.dao.TablePOSDAO;
import by.pvt.academy.yarkovich.entity.TablePOS;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by dima on 05.09.2016.
 */
public class TablePOSService {

    private static TablePOSService instance;

    public static TablePOSService getInstance() {
        if (instance == null) {
            instance = new TablePOSService();
        }
        return instance;
    }

    public List<TablePOS> getAll() {
        Session session = HibernateUtil.getHibernateUtil().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List <TablePOS> list = TablePOSDAO.getInstance().getAll(session);
        tx.commit();
        return list;
    }
}
