package  by.pvt.academy.yarkovich.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import by.pvt.academy.yarkovich.entity.Receipt;
import by.pvt.academy.yarkovich.managers.SQLReqManager;

public class ReceiptDAO extends DAO {
	private static ReceiptDAO instance;

	public static ReceiptDAO getInstance() {
		 if (instance == null) {
	            instance = new ReceiptDAO();
	        }
	        return instance;
	}

	public void add(Receipt receipt, int waiter_id) {
		PreparedStatement ps = null;
		Connection connection = null;
		String currTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			connection = poolInstance.getConnection();
			String query = sqlManager.getProperty(SQLReqManager.SQL_ADD_RECEIPT_TO_BASE);
			ps = poolInstance.getConnection().prepareStatement(query);
			ps.setDouble(1, receipt.getSum());
			ps.setString(2, currTime);
			ps.setInt(3, waiter_id);
			ps.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			poolInstance.freeConnection(connection);
		}
	}



}
