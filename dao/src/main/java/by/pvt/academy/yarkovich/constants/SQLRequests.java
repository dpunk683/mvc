package by.pvt.academy.yarkovich.constants;

/**
 * Created by dima on 27.08.2016.
 */
public class SQLRequests {
    public static final String SQL_GET_DAYDISH = "SELECT * FROM menu where secondPrice <> 0";
    public static final String SQL_GET_WAITER = "SELECT * FROM waiters WHERE login= ? AND pass= ?";
    public static final String SQL_GET_PRODUCTBYID = "SELECT * FROM menu WHERE id = ?";
    public static final String SQL_GET_MENU = "SELECT * FROM menu";
    public static final String SQL_ADD_ORDER_TO_BASE = "INSERT INTO rcpbody (`productNo`, `price`,`starttime`, `remote_ip_id`)  SELECT ? , ?, ?, id from `ips` as a where a.`ip-address` = ?";
    public static final String SQL_QUERY_GET_ORDERS = "SELECT a.id, a.productNo, a.price, a.starttime,a.remote_ip_id , a.status ,  b.TableId, c.name from rcpbody a inner join ips b on a.remote_ip_id=b.id inner join menu c on a.productNo=c.id order by 1";
    public static final String SQL_ADD_RECEIPT_TO_BASE ="INSERT INTO rcpheaders (`sum`, `startTime`,`waiterId`)  ?, ?, ?";
    public static final String SQL_GET_USER_BY_UID = "SELECT * FROM waiters WHERE id= ?";
    public static final String SQL_GET_CLIENTS = "SELECT * FROM clients";
}
