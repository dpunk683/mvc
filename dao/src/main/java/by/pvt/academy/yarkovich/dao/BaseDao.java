package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.exceptions.DAOException;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;


/**
 * Created by dima on 27.08.2016.
 */
public class BaseDao<T> implements DAO<T> {
    private static Logger log = Logger.getLogger(BaseDao.class);
    protected Session session = HibernateUtil.getHibernateUtil().getCurrentSession();


    public BaseDao() {

    }

    public void saveOrUpdate(T t) throws DAOException {
        try {
            session.saveOrUpdate(t);
            log.info("saveOrUpdate(t):" + t);
            log.info("Save or update (commit):" + t);
        } catch (HibernateException e) {
            log.error("Error save or update PERSON in Dao" + e);
            throw new DAOException(e);
        }

    }

    public T get(Serializable id) throws DAOException{
        log.info("Get class by id:" + id);
        T t = null;
            t = (T) session.get(getPersistentClass(), id);
            log.info("get clazz:" + t);
        return t;
    }

    public T load(Serializable id) throws DAOException {
        log.info("Load class by id:" + id);
        T t = null;
        try {
            t = (T) session.load(getPersistentClass(), id);
            log.info("load() clazz:" + t);
            session.isDirty();
        } catch (HibernateException e) {
            log.error("Error load() " + getPersistentClass() + " in Dao" + e);
            throw new DAOException(e);
        }
        return t;
    }

    public void delete(T t) throws DAOException {
        try {
            session.delete(t);
            log.info("Delete:" + t);
        } catch (HibernateException e) {
            log.error("Error save or update PERSON in Dao" + e);
            throw new DAOException(e);
        }
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
