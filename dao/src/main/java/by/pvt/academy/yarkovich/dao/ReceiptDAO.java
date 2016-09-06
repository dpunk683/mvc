package  by.pvt.academy.yarkovich.dao;

import by.pvt.academy.yarkovich.constants.HQLRequests;
import by.pvt.academy.yarkovich.entity.Receipt;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ReceiptDAO extends BaseDao {
	private static ReceiptDAO instance;

	public static ReceiptDAO getInstance() {
		 if (instance == null) {
	            instance = new ReceiptDAO();
	        }
	        return instance;
	}

	public void add(Receipt receipt, Long waiter_id) {
		String currTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		Session session = HibernateUtil.getHibernateUtil().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(HQLRequests.HQL_ADD_RECEIPT_TO_BASE);
		query.setDouble(1, receipt.getSum());
        query.setString(2, currTime);
        query.setLong(3, waiter_id);
        query.executeUpdate();
        tx.commit();
        session.close();
	}
}
