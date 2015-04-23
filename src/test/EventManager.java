package test;

import main.java.model.Event;
import main.java.model.Person;
import main.java.util.HbmUtil;
import org.hibernate.classic.Session;

import java.util.Date;
import java.util.List;

/**
 * Created by kezhenxu on 4/22/15.
 */
public class EventManager {

	public static void main ( String[] args ) {
		EventManager manager = new EventManager ();
		if ( args[ 0 ].equals ( "store" ) ) {
			manager.createAndStoreEvent ( "MyEvent", new Date () );
		}
		else if ( args[ 0 ].equals ( "list" ) ) {
			List<Event> events = manager.listEvent ();
			for ( Event event : events ) {
				System.out.println ( "Event:" + event.getTitle ()
						                     + "\tDate:" + event.getDate ()
											+ "\tParticipants:"+event.getParticipants());
			}
		}
		else if ( args[ 0 ].equals ( "addPersonToEvent" ) ) {
			Long eventId = manager.createAndStoreEvent ( "MyEvent", new Date () );
			Long personId = manager.createAndStorePerson ( "Foo", "Bar" );
			manager.addPersonToEvent ( personId, eventId );
		}
		else if (args[0].equals("delete")) {
			Long personId = manager.createAndStorePerson("FOO1", "BAR1");
			Person person = manager.delete(personId);
			System.out.println(person.getId());
		}

		HbmUtil.getSessionFactory ().close ();
	}

	private Long createAndStoreEvent ( String aTitle, Date aDate ) {
		Session session = HbmUtil
				.getSessionFactory ()
				.getCurrentSession ();
		session.beginTransaction ();
		Event event = new Event ();
		event.setTitle ( aTitle );
		event.setDate ( aDate );
		session.save ( event );
		session.getTransaction()
		       .commit();
		return event.getId ();
	}

	private Long createAndStorePerson ( String firstName, String lastName ) {
		Session session = HbmUtil
				.getSessionFactory ()
				.getCurrentSession ();
		session.beginTransaction ();
		Person person = new Person ();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		session.save ( person );
		session.getTransaction ()
		       .commit ();
		return person.getId ();
	}

	private List<Event> listEvent () {
		Session session = HbmUtil
				.getSessionFactory ()
				.getCurrentSession ();
		session.beginTransaction();
		List<Event> events = session
				.createQuery ( "from Event" )
				.list ();
		session.getTransaction ()
		       .commit ();
		return events;
	}

	private void addPersonToEvent ( Long personId, Long eventId ) {
		Session session = HbmUtil
				.getSessionFactory ()
				.getCurrentSession ();
		session.beginTransaction ();
//		Person person = ( Person ) session.load(Person.class, personId);
//		Event  event  = ( Event ) session.load ( Event.class, eventId );
		Person person = ( Person ) session.get(Person.class, personId);
		Event  event  = ( Event ) session.get ( Event.class, eventId );
		person.getEvents ().add ( event );
		session.getTransaction ().commit();
	}

	private Person delete(Long personId) {
		Session session = HbmUtil
				.getSessionFactory ()
				.getCurrentSession ();
		session.beginTransaction();
		Person person = ( Person ) session.get(Person.class, personId);
		session.delete(person);
		session.getTransaction()
				.commit();
		return person;
	}
}
