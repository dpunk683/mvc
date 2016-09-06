package by.pvt.academy.yarkovich.utils;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    private static HibernateUtil util = null;
    private static Logger log = Logger.getLogger(HibernateUtil.class);
    private static final ThreadLocal threadSession = new ThreadLocal();
    private static SessionFactory sessionFactory = null;

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            log.error("Initial SessionFactory creation failed." + ex);
            System.exit(0);
        }
    }

    public Session getCurrentSession() {
        Session s = (Session) threadSession.get();
        try {
            if (s == null || !s.isOpen()) {
                s = sessionFactory.openSession();
                threadSession.set(s);
            }
        } catch (HibernateException ex) {
        }
        return s;
    }

    public void closeSession() {
        try {
            final Session s = (Session) threadSession.get();
            if (s != null && s.isOpen()) {
                s.close();
            }
        } catch (HibernateException ex) {
        } finally {
            threadSession.set(null);
        }
    }

    public static synchronized HibernateUtil getHibernateUtil() {
        if (util == null) {
            util = new HibernateUtil();
        }
        return util;
    }
}

//package by.pvt.academy.yarkovich.utils;
//
//import org.apache.log4j.Logger;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//
//
//public class HibernateUtil {
//    private static HibernateUtil util = null;
//
//    private static Logger log = Logger.getLogger(HibernateUtil.class);
//
//    private SessionFactory sessionFactory = null;
//
//    private static final ThreadLocal sessions = new ThreadLocal();
//
//    private HibernateUtil() {
//        try {
//            Configuration configuration = new Configuration().configure();
//            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//            sessionFactory = configuration.buildSessionFactory(builder.build());
//        } catch (Throwable ex) {
//            log.error("Initial SessionFactory creation failed." + ex);
//            System.exit(0);
//        }
//    }
//
//    public Session getSession() {
//        Session session = (Session) sessions.get();
//        if (session == null) {
//            session = sessionFactory.openSession();
//            sessions.set(session);
//        }
//        return session;
//    }
//
//    public static synchronized HibernateUtil getHibernateUtil() {
//        if (util == null) {
//            util = new HibernateUtil();
//        }
//        return util;
//    }
//
//    public void closeSession() {
//        getSession().close();
//        HibernateUtil.sessions.set(null);
//    }
//
//}
