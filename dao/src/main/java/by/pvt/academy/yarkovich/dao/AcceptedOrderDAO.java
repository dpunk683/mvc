package by.pvt.academy.yarkovich.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

import by.pvt.academy.yarkovich.constants.SQLRequests;
import by.pvt.academy.yarkovich.entity.AcceptedOrder;

public class AcceptedOrderDAO extends BaseDao {

	private static AcceptedOrderDAO instance;

	public static AcceptedOrderDAO getInstance() {
		if (instance == null) {
			instance = new AcceptedOrderDAO();
		}
		return instance;
	}

	private List<AcceptedOrder> initAcceptedOrders(ResultSet resultSet) throws SQLException {
        List<AcceptedOrder> acc_orders = new ArrayList<AcceptedOrder>();
        while (resultSet.next()) {
        	//Order statuses: 0-new, 1- on kitchen,2-ready to serve, 3-closed, 99-canceled
        	if (resultSet.getInt(6)>2){continue;}
        	AcceptedOrder acc_order = new AcceptedOrder();
        	acc_order.setId((long) resultSet.getInt(1));
        	acc_order.setProductNo(resultSet.getInt(2));
        	acc_order.setPrice(resultSet.getDouble(3));
        	acc_order.setStarttime(resultSet.getString(4));
        	acc_order.setIp(resultSet.getInt(5));
        	acc_order.setStatus(resultSet.getInt(6));
        	acc_order.setTableNum(resultSet.getInt(7));
        	acc_order.setProdname(resultSet.getString(8));
        	acc_orders.add(acc_order);
        }
        return acc_orders;
    }
	
	public void addOrder(AcceptedOrder acceptedOrder, String ip) {
		PreparedStatement ps = null;
		Connection connection = null;
		String currTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			connection = poolInstance.getConnection();
			String query = SQLRequests.SQL_ADD_ORDER_TO_BASE;
			ps = poolInstance.getConnection().prepareStatement(query);
			ps.setInt(1, acceptedOrder.getProductNo());
			ps.setDouble(2, acceptedOrder.getPrice());
			ps.setString(3, currTime);
			ps.setString(4, ip);
			ps.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			poolInstance.freeConnection(connection);
		}
	}

	public List<AcceptedOrder> get() {
		List<AcceptedOrder> acc_orders= null;
		ResultSet resultSet = null;
		Statement statement = null;
		Connection connection = null;
		try {
			connection = poolInstance.getConnection();
			String query = SQLRequests.SQL_QUERY_GET_ORDERS;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			acc_orders = initAcceptedOrders(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		poolInstance.freeConnection(connection);
		return acc_orders;
	}

	
}
