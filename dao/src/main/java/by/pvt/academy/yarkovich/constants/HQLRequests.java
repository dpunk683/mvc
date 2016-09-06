package by.pvt.academy.yarkovich.constants;

/**
 * Created by dima on 02.09.2016.
 */
public class HQLRequests {
    public static final String HQL_GET_CLIENTS = "FROM Client";
    public static final String HQL_GET_WAITER = "FROM Employee WHERE login=:login AND pass=:password";
    public static final String HQL_GET_USER_BY_UID = "FROM Employee WHERE id=:id";
    public static final String HQL_GET_DAYDISH = "FROM Product where secondPrice <> 0";
    public static final String HQL_GET_MENU = "FROM Product";
    public static final String HQL_GET_PRODUCTBYID = "FROM Product WHERE id=:id";
    public static final String HQL_ADD_RECEIPT_TO_BASE ="INSERT INTO rcpheaders (`sum`, `startTime`,`waiterId`)  ?, ?, ?";
    public static final String HQL_GET_WAITER_BY_CARD = "FROM Employee WHERE cardNum=:cardNum";
    public static final String HQL_GET_WAITER_BY_LOGIN ="FROM Employee WHERE login=:login";
    public static final String HQL_GET_POSES = "FROM TablePOS"; ;
}