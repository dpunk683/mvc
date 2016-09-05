package by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.constants.HQLRequests;
import by.pvt.academy.yarkovich.entity.Client;
import by.pvt.academy.yarkovich.exceptions.DAOException;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class ClientDAO extends BaseDao {

    private static ClientDAO instance;

    public void add(Client client){
        try {
            ClientDAO.getInstance().saveOrUpdate(client);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

//	public void update(Client client) throws SQLException {
//		Connection connection = poolInstance.getConnection();
//		String query = SQLRequests.SQL_UPDATE_CLIENT;
//		PreparedStatement ps = null;
//
//		ps = connection.prepareStatement(query);
//		ps.setString(1, client.getName());
//		ps.setString(2, client.getDateOfBirth());
//		ps.setString(3, client.getPhone());
//		ps.setString(4, client.getEmail());
//		ps.setString(5, client.getLoyalityCardNo());
//		ps.setString(6, client.getOldLoyalityCardNo());
//		ps.setDouble(7, client.getSpentMoney());
//		ps.executeUpdate();
//		poolInstance.freeConnection(connection);
//	}

    public List<Client> getAll(Session session) {
        Query query = session.createQuery(HQLRequests.HQL_GET_CLIENTS);
        List<Client> clients = query.list();
        return clients;
    }

    public static ClientDAO getInstance() {
        if (instance == null) {
            instance = new ClientDAO();
        }
        return instance;
    }

}
