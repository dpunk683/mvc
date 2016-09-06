package by.pvt.academy.yarkovich;

import by.pvt.academy.yarkovich.dao.ProductDAO;
import by.pvt.academy.yarkovich.entity.Product;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by dima on 03.09.2016.
 */
public class ProductsService {
    private static ProductsService instance;

    private ProductsService() {
        super();
    }

    public static ProductsService getInstance() {
        if (instance == null) {
            instance = new ProductsService();
        }
        return instance;
    }

    public List<Product> getNewMenu() {
        Session session = HibernateUtil.getHibernateUtil().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List <Product> list = ProductDAO.getInstance().getWholeProd(session);
        tx.commit();
        return list;
    }

    public List<Product> getDayDish() {
        Session session = HibernateUtil.getHibernateUtil().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List <Product> list = ProductDAO.getInstance().getDayDish(session);
        tx.commit();
        return list;
    }

    public Product getById(long l) {
        Session session = HibernateUtil.getHibernateUtil().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Product product = ProductDAO.getInstance().getById(l, session);
        tx.commit();
        return product;
    }
}
