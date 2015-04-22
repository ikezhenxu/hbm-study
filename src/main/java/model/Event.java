package main.java.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kezhenxu on 4/22/15.
 */
public class Event {

	private Long   id;
	private String title;
	private Date   date;
	private Set<Person> participants = new HashSet<Person> ();

	public Event () {
	}

	public Long getId () {
		return id;
	}

	/**
	 * Because we use id as primary key,
	 * we make setId private
	 * not to allow setting id
	 */
	private void setId ( Long aId ) {
		id = aId;
	}

	public String getTitle () {
		return title;
	}

	public void setTitle ( String aTitle ) {
		title = aTitle;
	}

	public Date getDate () {
		return date;
	}

	public void setDate ( Date aDate ) {
		date = aDate;
	}

	public Set<Person> getParticipants () {
		return participants;
	}

	public void setParticipants ( Set<Person> aParticipants ) {
		participants = aParticipants;
	}
}
