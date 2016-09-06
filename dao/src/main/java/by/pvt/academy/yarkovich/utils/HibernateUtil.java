package by.pvt.academy.yarkovich.utils;


import by.pvt.academy.yarkovich.logger.RestLogger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    private static HibernateUtil util;
    private static final ThreadLocal<Session> threadSession =
            new ThreadLocal();
    private static SessionFactory mSessionFactory;
    static {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        mSessionFactory = configuration.buildSessionFactory(builder.build());
    }

//    private HibernateUtil() {
//        try {
//            Configuration configuration = new Configuration().configure();
//            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//            mSessionFactory = configuration.buildSessionFactory(builder.build());
//        } catch (Throwable ex) {
//            RestLogger.getInstance(this.getClass()).writeError("Initial SessionFactory creation failed." + ex);
//            System.exit(0);
//        }
//    }

    public static synchronized HibernateUtil getHibernateUtil() {
        if (util == null) {
            util = new HibernateUtil();
        }
        return util;
    }

    public static Session getCurrentSession() {
        Session s = threadSession.get();
        try {
            if (s == null || !s.isOpen()) {
                s = mSessionFactory.openSession();
                threadSession.set(s);
            }
        } catch (HibernateException ex) {
            RestLogger.getInstance(HibernateUtil.class).writeError("Error opening session." + ex);
        }
        return s;
    }

    private static void closeSession() {
        try {
            final Session s = threadSession.get();
            if (s != null && s.isOpen()) {
                s.close();
                threadSession.set(null);
            }
        } catch (HibernateException ex) {
            RestLogger.getInstance(HibernateUtil.class).writeError("Error closing session." + ex);
        }
    }

    public static void beginTransaction() {
        if (threadSession.get() != null && threadSession.get().isOpen()) {
            threadSession.get().close();
            threadSession.set(null);
        }
        Transaction tx = getCurrentSession().beginTransaction();
        if (threadSession.get() == null || !threadSession.get().isOpen()) {
            getCurrentSession();
        } else {
            threadSession.get().clear();
        }
    }

    public static void commitTransaction() {
        try {
            Session s = getCurrentSession();
            s.flush();
            RestLogger.getInstance(HibernateUtil.class).writeError("Flushing session and committing transaction of this thread.");
            getCurrentSession().getTransaction().commit();
        } catch (
                HibernateException ex) {
            rollbackTransaction();
            RestLogger.getInstance(HibernateUtil.class).writeError("ERROR Flushing session and committing transaction of this thread.");
        } finally {
            closeSession();
        }
    }

    public static void rollbackTransaction() {
        try {
            getCurrentSession().getTransaction().rollback();
        } catch (HibernateException ex) {
            RestLogger.getInstance(HibernateUtil.class).writeError("ERROR rollback transaction of this thread.");
        } finally {
            closeSession();
        }
    }

}
//    private static HibernateUtil util = null;
//    private static Logger log = Logger.getLogger(HibernateUtil.class);
//    private static final ThreadLocal threadSession = new ThreadLocal();
//    private static SessionFactory sessionFactory = null;
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
//    public Session getCurrentSession() {
//        Session s = (Session) threadSession.get();
//        try {
//            if (s == null || !s.isOpen()) {
//                s = sessionFactory.openSession();
//                threadSession.set(s);
//            }
//        } catch (HibernateException ex) {
//        }
//        return s;
//    }
//
//    public void closeSession() {
//        try {
//            final Session s = (Session) threadSession.get();
//            if (s != null && s.isOpen()) {
//                s.close();
//            }
//        } catch (HibernateException ex) {
//        } finally {
//            threadSession.set(null);
//        }
//    }
//
//    public static synchronized HibernateUtil getHibernateUtil() {
//        if (util == null) {
//            util = new HibernateUtil();
//        }
//        return util;
//    }
//}

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
