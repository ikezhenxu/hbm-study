package main.java.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by kezhenxu on 4/22/15.
 */
public class HbmUtil {

	private static final SessionFactory SESSION_FACTORY;

	static {
		try {
			SESSION_FACTORY = new Configuration ()
					.configure ()
					.buildSessionFactory ();
		} catch ( Throwable aThrowable ) {
			System.err.println ( "Initial SessionFactory Creation failed: " + aThrowable);
			throw new ExceptionInInitializerError ( aThrowable );
		}
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
}
