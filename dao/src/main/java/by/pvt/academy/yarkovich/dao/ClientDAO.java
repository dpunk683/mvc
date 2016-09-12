package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.constants.HQLRequests;
import by.pvt.academy.yarkovich.entity.Client;
import by.pvt.academy.yarkovich.exceptions.DAOException;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class ClientDAO extends BaseDao {

    private static ClientDAO instance;

    public void addClient(Client client){
            HibernateUtil.getCurrentSession().saveOrUpdate(client);
    }


    public List<Client> getAll(Session session) {
        Query query = session.createQuery(HQLRequests.HQL_GET_CLIENTS);
        List<Client> clients = query.list();
        return clients;
    }

    public static ClientDAO getInstance() {
        if (instance == null) {
            instance = new ClientDAO();
        }
        return instance;
    }

    public synchronized List<Client> getClientbyCard(String cardNum) throws DAOException {
        Query query = HibernateUtil.getCurrentSession().createQuery(HQLRequests.HQL_GET_CLIENT_BY_CARD);
        query.setParameter("cardnum", cardNum);
        List<Client> clients = query.list();
        return clients;
    }

    public synchronized List<Client> getClientByEmail(String email) throws DAOException {
        Query query = HibernateUtil.getCurrentSession().createQuery(HQLRequests.HQL_GET_CLIENT_BY_EMAIL);
        query.setParameter("email", email);
        List<Client> clients = query.list();
        return clients;
    }

    public synchronized List<Client> getClientByPhone(String phone) throws DAOException {
        Query query = HibernateUtil.getCurrentSession().createQuery(HQLRequests.HQL_GET_CLIENT_BY_PHONE);
        query.setParameter("phone", phone);
        List<Client> clients = query.list();
        return clients;
    }

}
