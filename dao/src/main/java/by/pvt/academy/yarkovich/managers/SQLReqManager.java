package by.pvt.academy.yarkovich.managers;

import java.util.ResourceBundle;


public class SQLReqManager {
	    // TODO Rewrite sql 
	    private static SQLReqManager instance;
	    private static ResourceBundle bundle;
	    
	    public static final String BUNDLE_NAME = "sql";
	    
		public static final String SQL_ADD_CLIENT = "SQL_ADD_CLIENT";
		public static final String SQL_UPDATE_CLIENT = "SQL_UPDATE_CLIENT";
		public static final String SQL_GET_CLIENTS = "SQL_GET_CLIENTS";
		public static final String SQL_ADD_WAITER = "SQL_ADD_USER";
		public static final String SQL_GET_WAITER = "SQL_GET_WAITER";
		public static final String SQL_GET_USER_BY_UID = "SQL_GET_USER_BY_UID";
		public static final String SQL_CHECK_LOGIN = null;
		public static final String SQL_GET_DAYDISH = "SQL_GET_DAYDISH";
		public static final String SQL_GET_MENU = "SQL_GET_MENU";
		public static final String SQL_GET_PRODUCTBYID = "SQL_GET_PRODUCTBYID";
		public static final String SQL_ADD_ORDER_TO_BASE = "SQL_ADD_ORDER_TO_BASE";
		public static final String SQL_QUERY_GET_ORDERS = "SQL_QUERY_GET_ORDERS";
		public static final String SQL_ADD_RECEIPT_TO_BASE = "SQL_ADD_RECEIPT_TO_BASE";
	    
	    public static SQLReqManager getInstance() {
	        if(instance == null) {
	            instance = new SQLReqManager();
	            bundle = ResourceBundle.getBundle(BUNDLE_NAME);
	        }
	        return instance;
	    }
	    
	    public String getProperty(String key) {
	        return bundle.getString(key);
	    }
	    
	}
