package main.java.model;

/**
 * Created by kezhenxu on 4/23/15.
 */
public class Student extends Person {

	private String school;

	public String getSchool () {
		return school;
	}

	public void setSchool ( String aSchool ) {
		school = aSchool;
	}

	@Override
	public String toString () {
		return "I am a student.";
	}
}
