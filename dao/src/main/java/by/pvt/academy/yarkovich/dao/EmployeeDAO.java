package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.constants.HQLRequests;
import by.pvt.academy.yarkovich.entity.Employee;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAO extends BaseDao {

    private static EmployeeDAO instance;

    private EmployeeDAO() {
        super();
    }

    public static EmployeeDAO getInstance() {
        if (instance == null) {
            instance = new EmployeeDAO();
        }
        return instance;
    }

    public Employee getEmployee(Session session, String login, String password) {
        Query query = session.createQuery(HQLRequests.HQL_GET_WAITER);
        query.setParameter("login", login);
        query.setParameter("password", password);
        Employee employee = (Employee) query.uniqueResult();
        return employee;
    }

    /**
     * Implements #HQL_GET_USER_BY_UID
     */
    public Employee getWaiter(int id) {
        Session session = HibernateUtil.getHibernateUtil().getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery(HQLRequests.HQL_GET_USER_BY_UID);
        query.setParameter("id",id);
        Employee employee = (Employee) query.uniqueResult();
        tx.commit();
        session.close();
        return employee;
    }

    public List<Employee> getEmployee(Session session, String cardNum) {
        Query query = session.createQuery(HQLRequests.HQL_GET_WAITER_BY_CARD);
        query.setParameter("cardNum", cardNum);
        List<Employee> employee = query.list();
        return employee;
    }

    public List<Employee> getEmployeeByLogin(Session session, String login) {
        Query query = session.createQuery(HQLRequests.HQL_GET_WAITER_BY_LOGIN);
        query.setParameter("login", login);
        List<Employee> employee = query.list();
        return employee;
    }

    public void addEmployee(Employee employee, Session session) {
        session.saveOrUpdate(employee);
    }

    /**
     * implements #SQL_CHECK_LOGIN
     */
//    public boolean checkLogin(String login) throws SQLException {
//        PreparedStatement ps = null;
//        String query =  SQLRequests.SQL_CHECK_LOGIN;
//        ps = poolInstance.getConnection().prepareStatement(query);
//        ps.setString(1, login);
//        ResultSet result = ps.executeQuery();
//        if(result.next()) {
//            return false;
//        }
//
//        return true;
//    }

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
