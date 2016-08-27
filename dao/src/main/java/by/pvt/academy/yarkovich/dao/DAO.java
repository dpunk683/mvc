package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.connection.DBConnectionPool;

public abstract class DAO {
    protected static DBConnectionPool poolInstance;

    protected DAO() {
        poolInstance = DBConnectionPool.getInstance();
    }

    void add() {
    }

    ;

    void getById() {
    }

    ;

    void getAll() {
    }

    ;
}
