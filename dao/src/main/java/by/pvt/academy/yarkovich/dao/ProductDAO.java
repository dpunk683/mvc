package  by.pvt.academy.yarkovich.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.pvt.academy.yarkovich.entity.Product;
import by.pvt.academy.yarkovich.managers.SQLReqManager;

public class ProductDAO extends DAO {
	private static ProductDAO instance;
	
	private final String COLUMN_NAME_ID = "id";
    private final String COLUMN_NAME_NAME = "name";
    private final String COLUMN_NAME_PRICE = "price";
    private final String COLUMN_NAME_SECONDPRICE = "secondPrice";
    private final String COLUMN_NAME_ABOUT = "about";
    private final String COLUMN_NAME_PICT = "picture";
    
    private ProductDAO() {
        super();
    }

    public static ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }
    
    private List<Product> initProducts(ResultSet resultSet) throws SQLException {
		List<Product> products = new ArrayList<Product>();
		while (resultSet.next()) {
			Product product = new Product();
			product.setId(resultSet.getInt(COLUMN_NAME_ID));
			product.setName(resultSet.getString(COLUMN_NAME_NAME));
			product.setPrice(resultSet.getDouble(COLUMN_NAME_PRICE));
			product.setSecondPrice(resultSet.getDouble(COLUMN_NAME_SECONDPRICE));
			product.setAbout(resultSet.getString(COLUMN_NAME_ABOUT));
			product.setPicture(resultSet.getString(COLUMN_NAME_PICT));
			products.add(product);
		}
		return products;
	}
    
    
    
   	
    public List<Product> getDayDish() {
    	List<Product> products = null;
		ResultSet resultSet = null;
		Statement statement = null;
		Connection connection = null;
		try {
			connection = poolInstance.getConnection();
			String query = sqlManager.getProperty(SQLReqManager.SQL_GET_DAYDISH);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			products = initProducts(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		poolInstance.freeConnection(connection);
		return products;		
	}

	public List<Product> getWholeProd() {
		List<Product> products = null;
		ResultSet resultSet = null;
		Statement statement = null;
		Connection connection = null;
		try {
			connection = poolInstance.getConnection();
			String query = sqlManager.getProperty(SQLReqManager.SQL_GET_MENU);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			products = initProducts(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		poolInstance.freeConnection(connection);
		return products;
	}

	public Product getById(int id) throws SQLException {
		Product product = null;
		PreparedStatement ps = null;
		Connection connection = null;
			connection = poolInstance.getConnection();
			String query = sqlManager.getProperty(SQLReqManager.SQL_GET_PRODUCTBYID);
			ps = poolInstance.getConnection().prepareStatement(query);
	        ps.setInt(1,id);
	        ResultSet result = ps.executeQuery();
			if (result.next()) {
			product = new Product();
			product.setId(result.getInt(COLUMN_NAME_ID));
			product.setName(result.getString(COLUMN_NAME_NAME));
			product.setPrice(result.getDouble(COLUMN_NAME_PRICE));
			product.setSecondPrice(result.getDouble(COLUMN_NAME_SECONDPRICE));
			product.setAbout(result.getString(COLUMN_NAME_ABOUT));
			product.setPicture(result.getString(COLUMN_NAME_PICT));
			}
			poolInstance.freeConnection(connection);
			return product;
	}
}
