package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.constants.HQLRequests;
import by.pvt.academy.yarkovich.entity.Product;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.sql.SQLException;
import java.util.List;

public class ProductDAO extends BaseDao {
    private static ProductDAO instance;

    private ProductDAO() {
        super();
    }

    public static ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }

    public List<Product> getDayDish(Session session) {
        Query query = session.createQuery(HQLRequests.HQL_GET_DAYDISH);
        List<Product> products = query.list();
        return products;
    }

    public List<Product> getWholeProd(Session session) {
        Query query = session.createQuery(HQLRequests.HQL_GET_MENU);
        List<Product> products = query.list();
        return products;
    }

    public Product getById(Long id, Session session) {
        Query query = session.createQuery(HQLRequests.HQL_GET_PRODUCTBYID);
        query.setParameter("id", id);
        Product product = (Product) query.uniqueResult();
        return product;
    }
}
