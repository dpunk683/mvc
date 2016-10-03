package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.constants.HQLRequests;
import by.pvt.academy.yarkovich.entity.Dish;
import by.pvt.academy.yarkovich.entity.Employee;
import by.pvt.academy.yarkovich.exceptions.DAOException;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;


import java.util.List;

public class DishDAO extends BaseDao {
    private static DishDAO instance;
    private static int pageSize = 3;

    private DishDAO() {
        super();
    }

    public static DishDAO getInstance() {
        if (instance == null) {
            instance = new DishDAO();
        }
        return instance;
    }

    public synchronized void addDish(Dish dish)throws DAOException {
        HibernateUtil.getCurrentSession().saveOrUpdate(dish);
    }

    public synchronized List<Dish> getDayDish() {
        Query query = HibernateUtil.getCurrentSession().createQuery(HQLRequests.HQL_GET_DAYDISH);
        List<Dish> dishes = query.list();
        return dishes;
    }

    public synchronized List<Dish> getWholeProd(int pageNumber) {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Dish.class);
        criteria.setFirstResult(pageSize * (pageNumber - 1));
        criteria.setMaxResults(pageSize);
        List<Dish> dishes = criteria.list();
        return dishes;
    }

    public synchronized Dish getById(Long id) {
        Query query = HibernateUtil.getCurrentSession().createQuery(HQLRequests.HQL_GET_PRODUCTBYID);
        query.setParameter("id", id);
        Dish dish = (Dish) query.uniqueResult();
        return dish;
    }

    public synchronized List<Dish> getByName(String name) {
        Query query = HibernateUtil.getCurrentSession().createQuery(HQLRequests.HQL_GET_PRODUCTBYNAME);
        query.setParameter("name", name);
        List<Dish> dish = query.list();
        return dish;
    }
}
