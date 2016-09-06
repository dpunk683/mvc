package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.constants.HQLRequests;
import by.pvt.academy.yarkovich.entity.Client;
import by.pvt.academy.yarkovich.entity.TablePOS;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by dima on 05.09.2016.
 */
public class TablePOSDAO extends BaseDao {

    private static TablePOSDAO instance;

    public static TablePOSDAO getInstance() {
        if (instance == null) {
            instance = new TablePOSDAO();
        }
        return instance;
    }

    public List<TablePOS> getAll(Session session) {
        Query query = session.createQuery(HQLRequests.HQL_GET_POSES);
        List<TablePOS> tables = query.list();
        return tables;
    }
}
