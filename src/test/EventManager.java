package test;

import main.java.model.Event;
import main.java.model.Person;
import main.java.model.Student;
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
		else if ( args[ 0 ].equals ( "listEvent" ) ) {
			List<Event> events = manager.listEvent ();
			for ( Event event : events ) {
				System.out.println ( "Event:" + event.getTitle ()
						                     + "\tDate:" + event.getDate ()
						                     + "\tParticipants:" + event.getParticipants () );
			}
		}
		else if ( args[ 0 ].equals ( "listPerson" ) ) {
			List<Person> persons = manager.listPerson ();
			for ( Person person : persons ) {
				System.out.println ( person);
			}
		}
		else if ( args[ 0 ].equals ( "addStu" ) ) {
			String firstName = "MyName";
			String lastName = "MyLastName";
			Student person = new Student ();
			person.setFirstName ( firstName );
			person.setLastName ( lastName );
			manager.createAndStorePerson ( person );
		}
		else if ( args[ 0 ].equals ( "addPersonToEvent" ) ) {
			Long eventId = manager.createAndStoreEvent ( "MyEvent", new Date () );
			Person person = new Person ();
			person.setFirstName ( "Foo" );
			person.setLastName ( "Bar" );
			Long personId = manager.createAndStorePerson ( person );
			manager.addPersonToEvent ( personId, eventId );
		}
		else if ( args[ 0 ].equals ( "delete" ) ) {
			Person person = new Person ();
			person.setFirstName ( "Foo" );
			person.setLastName ( "Bar" );
			Long personId = manager.createAndStorePerson ( person );
			Person personDeleted = manager.delete ( personId );
			System.out.println ( personDeleted.getId () );
		}
		else if ( args[ 0 ].equals ( "update" ) ) {
			Long eventId = manager.createAndStoreEvent ( "MyEvent", new Date () );
			System.out.println ( manager.update ( eventId ).getTitle () );
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
		session.getTransaction ()
		       .commit ();
		return event.getId ();
	}

	private Long createAndStorePerson ( Person aPerson ) {
		Session session = HbmUtil
				.getSessionFactory ()
				.getCurrentSession ();
		session.beginTransaction ();
		if ( aPerson instanceof Student )
			session.save ( ( Student ) aPerson );
		else {
			session.save ( aPerson );
		}
		session.getTransaction ()
		       .commit ();
		return aPerson.getId ();
	}

	private List<Event> listEvent () {
		Session session = HbmUtil
				.getSessionFactory ()
				.getCurrentSession ();
		session.beginTransaction ();
		List<Event> events = session
				.createQuery ( "from Event " )
				.list ();
		session.getTransaction ()
		       .commit ();
		return events;
	}

	private List<Person> listPerson () {
		Session session = HbmUtil
				.getSessionFactory ()
				.getCurrentSession ();
		session.beginTransaction ();
		List<Person> events = session
				.createQuery ( "from Person " )
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
		Person person = ( Person ) session.get ( Person.class, personId );
		Event  event  = ( Event ) session.get ( Event.class, eventId );
		person.getEvents ().add ( event );
		session.getTransaction ().commit ();
	}

	private Person delete ( Long personId ) {
		Session session = HbmUtil
				.getSessionFactory ()
				.getCurrentSession ();
		session.beginTransaction ();
		Person person = ( Person ) session.get ( Person.class, personId );
		session.delete ( person );
		session.getTransaction ()
		       .commit ();
		return person;
	}

	private Event update ( Long eventId ) {
		Session session = HbmUtil
				.getSessionFactory ()
				.getCurrentSession ();
		session.beginTransaction ();
		Event event = ( Event ) session.get ( Event.class, eventId );
		event.setTitle ( "New Title" );
		session.update ( event );
		session.getTransaction ()
		       .commit ();
		return event;
	}
}
