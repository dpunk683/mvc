package  by.pvt.academy.yarkovich.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.pvt.academy.yarkovich.entity.Client;
import by.pvt.academy.yarkovich.managers.SQLReqManager;

public class ClientDAO extends DAO {
	
	private static ClientDAO instance;
	
	public void add(Client client) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SQLReqManager.SQL_ADD_CLIENT);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, client.getName());
		ps.setString(2, client.getDateOfBirth());
		ps.setString(3, client.getPhone());
		ps.setString(4, client.getEmail());
		ps.setString(5, client.getLoyalityCardNo());
		ps.setString(6, client.getOldLoyalityCardNo());
		ps.setDouble(7, client.getSpentMoney());
		ps.executeUpdate();
		poolInstance.freeConnection(connection);
	}

	public void update(Client client) throws SQLException {
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(SQLReqManager.SQL_UPDATE_CLIENT);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, client.getName());
		ps.setString(2, client.getDateOfBirth());
		ps.setString(3, client.getPhone());
		ps.setString(4, client.getEmail());
		ps.setString(5, client.getLoyalityCardNo());
		ps.setString(6, client.getOldLoyalityCardNo());
		ps.setDouble(7, client.getSpentMoney());
		ps.executeUpdate();
		poolInstance.freeConnection(connection);
	}

	private List<Client> initClients(ResultSet resultSet) throws SQLException {
		List<Client> clients = new ArrayList<Client>();
		while (resultSet.next()) {
			Client client = new Client();
			client.setName(resultSet.getString(1));
			client.setDateOfBirth(resultSet.getString(2));
			client.setPhone(resultSet.getString(3));
			client.setEmail(resultSet.getString(4));
			client.setLoyalityCardNo(resultSet.getString(5));
			client.setOldLoyalityCardNo(resultSet.getString(6));
			client.setSpentMoney(resultSet.getDouble(7));
			clients.add(client);
		}
		return clients;
	}

	public List<Client> get() {
		List<Client> clients = null;
		ResultSet resultSet = null;
		Statement statement = null;
		Connection connection = null;
		try {
			connection = poolInstance.getConnection();
			String query = sqlManager.getProperty(SQLReqManager.SQL_GET_CLIENTS);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			clients = initClients(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		poolInstance.freeConnection(connection);
		return clients;
	}

	public static ClientDAO getInstance() {
		if (instance == null) {
            instance = new ClientDAO();
        }
        return instance;
	}
	
}
