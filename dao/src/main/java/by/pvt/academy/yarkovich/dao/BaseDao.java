package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.exceptions.DAOException;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.lang.reflect.ParameterizedType;

import java.io.Serializable;

/**
 * Created by dima on 27.08.2016.
 */
public class BaseDao<T> implements DAO<T> {
    private static Logger log = Logger.getLogger(BaseDao.class);
    private Transaction transaction = null;


    public BaseDao() {

    }

    public void saveOrUpdate(T t) throws DAOException {
        try {
            Session session = HibernateUtil.getHibernateUtil().getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            log.info("saveOrUpdate(t):" + t);
            transaction.commit();
            log.info("Save or update (commit):" + t);
        } catch (HibernateException e) {
            log.error("Error save or update PERSON in Dao" + e);
            transaction.rollback();
            throw new DAOException(e);
        }

    }

    public T get(Serializable id) throws DAOException {
        log.info("Get class by id:" + id);
        T t = null;
        try {
            Session session = HibernateUtil.getHibernateUtil().getSession();
            transaction = session.beginTransaction();
            t = (T) session.get(getPersistentClass(), id);
            transaction.commit();
            log.info("get clazz:" + t);
        } catch (HibernateException e) {
            transaction.rollback();
            log.error("Error get " + getPersistentClass() + " in Dao" + e);
            throw new DAOException(e);
        }
        return t;
    }

    public T load(Serializable id) throws DAOException {
        log.info("Load class by id:" + id);
        T t = null;
        try {
            Session session = HibernateUtil.getHibernateUtil().getSession();
            transaction = session.beginTransaction();
            t = (T) session.load(getPersistentClass(), id);
            log.info("load() clazz:" + t);
            session.isDirty();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error load() " + getPersistentClass() + " in Dao" + e);
            transaction.rollback();
            throw new DAOException(e);
        }
        return t;
    }

    public void delete(T t) throws DAOException {
        try {
            Session session = HibernateUtil.getHibernateUtil().getSession();
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            log.info("Delete:" + t);
        } catch (HibernateException e) {
            log.error("Error save or update PERSON in Dao" + e);
            transaction.rollback();
            throw new DAOException(e);
        }
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
