package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Singleton enum
 * 
 * 
 *
 */
public enum HibernateConnection {
	INSTANCE;
	
	private static SessionFactory factory = new Configuration().configure().buildSessionFactory();
	private static Session session = factory.openSession();
	
	private HibernateConnection() {
//		factory = new Configuration().configure().buildSessionFactory();
//		session = factory.openSession();
	}
	
	public static Session getSession() {
		return session;
	}
	
	public static void shutdown() {
		session.close();
	}
}
