package  by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.connection.DBConnectionPool;
import by.pvt.academy.yarkovich.managers.SQLReqManager;

public abstract class DAO {
	protected static DBConnectionPool poolInstance;
    protected static SQLReqManager sqlManager;
    
    protected DAO() {
        poolInstance = DBConnectionPool.getInstance();
        sqlManager = SQLReqManager.getInstance();
    }
	void add() {
	}
	void getById() {
	}
	void getAll() {
	}
}
