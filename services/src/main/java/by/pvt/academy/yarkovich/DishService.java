package by.pvt.academy.yarkovich;

import by.pvt.academy.yarkovich.dao.DishDAO;
import by.pvt.academy.yarkovich.entity.Dish;
import by.pvt.academy.yarkovich.exceptions.DAOException;
import by.pvt.academy.yarkovich.exceptions.DishNameErrorException;
import by.pvt.academy.yarkovich.logger.RestLogger;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static by.pvt.academy.yarkovich.utils.HibernateUtil.beginTransaction;
import static by.pvt.academy.yarkovich.utils.HibernateUtil.commitTransaction;
import static by.pvt.academy.yarkovich.utils.HibernateUtil.rollbackTransaction;

/**
 * Created by dima on 03.09.2016.
 */
public class DishService {
    private static DishService instance;
    private DishDAO dishDAO_inst = DishDAO.getInstance();

    private DishService() {
        super();
    }

    public static DishService getInstance() {
        if (instance == null) {
            instance = new DishService();
        }
        return instance;
    }

    public synchronized void addDish(Dish dish) {
        try {
            beginTransaction();
            dishDAO_inst.addDish(dish);
            commitTransaction();
        } catch (DAOException e) {
            RestLogger.getInstance(this.getClass()).writeError("addDish Service: " + e);
            rollbackTransaction();
        }
    }

    public synchronized void validation(Dish dish) throws DishNameErrorException {
            beginTransaction();
            List<Dish> dishesSameName = dishDAO_inst.getByName(dish.getName());
            if (dishesSameName.size() != 0) {
                rollbackTransaction();
                throw new DishNameErrorException();
            }
            commitTransaction();
    }

    public List<Dish> getNewMenu() {
        try {
            beginTransaction();
            List<Dish> list = dishDAO_inst.getWholeProd();
            commitTransaction();
            return list;
        } catch (HibernateException e) {
            rollbackTransaction();
            return null;
        }
    }

    public List<Dish> getDayDish() {
        try {
            beginTransaction();
            List<Dish> list = dishDAO_inst.getDayDish();
            commitTransaction();
            return list;
        } catch (HibernateException e) {
            rollbackTransaction();
            return null;
        }
    }

    public Dish getById(long id) {
        try {
            beginTransaction();
            Dish dish = dishDAO_inst.getById(id);
            commitTransaction();
            return dish;
        } catch (HibernateException e) {
            rollbackTransaction();
            return null;
        }
    }
}
