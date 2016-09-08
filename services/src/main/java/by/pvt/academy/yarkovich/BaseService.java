package by.pvt.academy.yarkovich;

import by.pvt.academy.yarkovich.utils.HibernateUtil;
import org.hibernate.Transaction;


/**
 * Created by dima on 05.09.2016.
 */
public class BaseService {


    protected  Transaction getTransaction(){
        Transaction transaction = HibernateUtil.getHibernateUtil().getCurrentSession().getTransaction();
        return transaction;
    }
}
