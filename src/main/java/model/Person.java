package main.java.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kezhenxu on 4/22/15.
 */
public class Person {

	private Long    id;
	private String flag;
	private Integer age;
	private String  firstName;
	private String  lastName;
	private Set<Event>  events         = new HashSet<Event> ();
	private Set<String> emailAddresses = new HashSet<String> ();

	public Person () {
	}

	public Long getId () {
		return id;
	}

	private void setId ( Long aId ) {
		id = aId;
	}

	public String getFlag () {
		return flag;
	}

	public void setFlag ( String aFlag ) {
		flag = aFlag;
	}

	public Integer getAge () {
		return age;
	}

	public void setAge ( Integer aAge ) {
		age = aAge;
	}

	public String getFirstName () {
		return firstName;
	}

	public void setFirstName ( String aFirstName ) {
		firstName = aFirstName;
	}

	public String getLastName () {
		return lastName;
	}

	public void setLastName ( String aLastName ) {
		lastName = aLastName;
	}

	public Set<Event> getEvents () {
		return events;
	}

	public void setEvents ( Set<Event> aEvents ) {
		events = aEvents;
	}

	public Set<String> getEmailAddresses () {
		return emailAddresses;
	}

	public void setEmailAddresses ( Set<String> aEmailAddresses ) {
		emailAddresses = aEmailAddresses;
	}

	@Override
	public String toString () {
		return "I am a person.";
	}
}
