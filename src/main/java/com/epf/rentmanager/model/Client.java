package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Client {
	private int id;
	private String lastName;
	private String firstName;
	private String email;
	private LocalDate birthday;

	public Client() {
	}

	public Client(int id, String lastName, String firstName, String email, LocalDate birthday) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.birthday = birthday;
	}

	public Client(String lastName, String firstName, String email, LocalDate birthday) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.birthday = birthday;
	}
	
	public Client(String lastName, String firstName, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
	}

	public String toString() {
		return "\nid : " + id + "\nLast name : " + lastName + "\nFirst name : " + firstName + "\nemail : " + email
				+ "\nBirthday : " + birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
}
