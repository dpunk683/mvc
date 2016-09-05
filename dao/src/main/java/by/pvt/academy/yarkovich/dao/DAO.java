package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.exceptions.DAOException;
import org.hibernate.Session;

import java.io.Serializable;

public interface DAO<T> {
    void saveOrUpdate(T t) throws DAOException;

    T get(Serializable id, Session session);

    T load(Serializable id) throws DAOException;

    void delete(T t) throws DAOException;
}




