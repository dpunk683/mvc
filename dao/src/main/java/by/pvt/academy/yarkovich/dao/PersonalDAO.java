package  by.pvt.academy.yarkovich.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.pvt.academy.yarkovich.entity.Personal;
import by.pvt.academy.yarkovich.managers.SQLReqManager;
import by.pvt.academy.yarkovich.utils.PassCoder;

public class PersonalDAO extends DAO {
    
    private static PersonalDAO instance;
    
    private final String COLUMN_NAME_ID = "id";
    private final String COLUMN_NAME_LOGIN = "login";
    private final String COLUMN_NAME_NAME = "name";
    private final String COLUMN_NAME_TYPE = "type";
    private final String COLUMN_NAME_PASS = "pass";
    private final String COLUMN_NAME_CARDNUM = "cardnum";
    
    private PersonalDAO() {
        super();
    }

    public static PersonalDAO getInstance() {
        if (instance == null) {
            instance = new PersonalDAO();
        }
        return instance;
    }

    public void register(Personal personal) throws SQLException {
        Connection connection = poolInstance.getConnection();
        String query = sqlManager.getProperty(SQLReqManager.SQL_ADD_WAITER);
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, personal.getName());
        ps.setString(2, personal.getLogin());
        ps.setInt(3, personal.getType());
        ps.setString(4, PassCoder.getHashCode(personal.getPass()));
        ps.setString(4, personal.getCardNum());
        ps.executeUpdate();
            
        poolInstance.freeConnection(connection);
    }

    public Personal getWaiter(String login, String password) throws SQLException {
    	Personal personal = null;
        String pass = PassCoder.getHashCode(password);
        String query =  sqlManager.getProperty(SQLReqManager.SQL_GET_WAITER);
        Connection connection = poolInstance.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,login);
        ps.setString(2,pass);
        ResultSet result = ps.executeQuery();
        if (result.next()) {
        	personal = new Personal();
        	personal.setId(result.getInt(COLUMN_NAME_ID));
        	personal.setLogin(result.getString(COLUMN_NAME_LOGIN));
        	personal.setName(result.getString(COLUMN_NAME_NAME));
        	personal.setType(result.getInt(COLUMN_NAME_TYPE));
        	personal.setPass(result.getString(COLUMN_NAME_PASS));
        	personal.setCardNum(result.getString(COLUMN_NAME_CARDNUM));
        }
        poolInstance.freeConnection(connection);
        return personal;
    }
    
    /**
     * Implements #SQL_GET_USER_BY_UID
     */
    public Personal getWaiter(int id) throws SQLException {
    	Personal personal = null;
        PreparedStatement ps = null;
        String query =  sqlManager.getProperty(SQLReqManager.SQL_GET_USER_BY_UID);
        ps = poolInstance.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();
        if (result.next()) {
        	personal = new Personal();
        	personal.setId(result.getInt(COLUMN_NAME_ID));
        	personal.setLogin(result.getString(COLUMN_NAME_LOGIN));
        	personal.setName(result.getString(COLUMN_NAME_NAME));
        	personal.setType(result.getInt(COLUMN_NAME_TYPE));
        	personal.setPass(result.getString(COLUMN_NAME_PASS));
        	personal.setCardNum(result.getString(COLUMN_NAME_CARDNUM));
        }
        return personal;
    }

    /**
     * implements #SQL_CHECK_LOGIN
     */
    public boolean checkLogin(String login) throws SQLException {
        PreparedStatement ps = null;
        String query =  sqlManager.getProperty(SQLReqManager.SQL_CHECK_LOGIN);
        ps = poolInstance.getConnection().prepareStatement(query);
        ps.setString(1, login);
        ResultSet result = ps.executeQuery();
        if(result.next()) {
            return false;
        }
        
        return true;
    }
    
//    /**
//     * implements #SQL_GET_ACCESS_LEVEL
//     */
//    public int getAccessLevel(String uid) throws SQLException {
//        int accessLevel = -1;
//        
//        PreparedStatement ps = null;
//        String query =  sqlManager.getProperty(SqlManager.SQL_GET_ACCESS_LEVEL);
//        ps = poolInstance.getConnection().prepareStatement(query);
//        ps.setInt(1, Integer.parseInt(uid));
//        ResultSet result = ps.executeQuery();
//        
//        if(result.next()) {
//            accessLevel = result.getInt(COLUMN_NAME_ACCESSLEVEL);
//        } else {
//            throw new RuntimeException("UserDAO: no such user");
//        }
//        
//        return accessLevel;
//    }
//    
//    /**
//     * implements #SQL_SET_ACCESS_LEVEL
//     */
//    public void setAccessLevel(int uid, int accessLevel) throws SQLException {
//        if(accessLevel < 0 || accessLevel > 2) {
//            throw new IllegalArgumentException("Unknown Access Level");
//        }
//        
//        PreparedStatement ps = null;
//        String query =  sqlManager.getProperty(SqlManager.SQL_SET_ACCESS_LEVEL);
//        ps = poolInstance.getConnection().prepareStatement(query);
//        ps.setInt(1, accessLevel);
//        ps.setInt(2, uid);
//        ps.executeUpdate();
//    }
//    
//    /**
//     * implements #SQL_GET_ALL_USERS
//     */
//    public ArrayList<User> getAllUsers() throws SQLException {
//        User user = null;
//        PreparedStatement ps = null;
//        ArrayList<User> list = new ArrayList<User>();
//        String query =  sqlManager.getProperty(SqlManager.SQL_GET_ALL_USERS);
//        ps = poolInstance.getConnection().prepareStatement(query);
//        ResultSet result = ps.executeQuery();
//        while(result.next()) {
//            user = new User();
//            user.setLogin(result.getString(COLUMN_NAME_LOGIN));
//            user.setName(result.getString(COLUMN_NAME_NAME));
//            user.setSurname(result.getString(COLUMN_NAME_SURNAME));
//            user.setEmail(result.getString(COLUMN_NAME_EMAIL));
//            user.setAccessLevel(result.getInt(COLUMN_NAME_ACCESSLEVEL));
//            user.setUid(result.getInt(COLUMN_NAME_UID));
//            user.setPhone(result.getString(COLUMN_NAME_PHONE));
//            user.setDate(result.getDate(COLUMN_NAME_DATE));
//            list.add(user);
//        }
//        return list;
//    }
}
