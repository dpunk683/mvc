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

    public static void closeSession() {
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
