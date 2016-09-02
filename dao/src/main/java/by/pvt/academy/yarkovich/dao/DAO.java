package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.exceptions.DAOException;

import java.io.Serializable;

public interface DAO<T> {
    void saveOrUpdate(T t) throws DAOException;

    T get(Serializable id) throws DAOException;

    T load(Serializable id) throws DAOException;

    void delete(T t) throws DAOException;
}




