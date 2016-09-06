package by.pvt.academy.yarkovich;

import by.pvt.academy.yarkovich.dao.ClientDAO;
import by.pvt.academy.yarkovich.entity.Client;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by dima on 03.09.2016.
 */
public class ClientsService {
    private static ClientsService instance;

    private ClientsService() {
        super();
    }

    public static ClientsService getInstance() {
        if (instance == null) {
            instance = new ClientsService();
        }
        return instance;
    }

    public List<Client> getAll() {
        Session session = HibernateUtil.getHibernateUtil().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List <Client> list = ClientDAO.getInstance().getAll(session);
        tx.commit();
        return list;
    }
}
