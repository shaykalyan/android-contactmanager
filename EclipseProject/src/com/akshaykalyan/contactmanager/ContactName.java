package com.akshaykalyan.contactmanager;

import java.util.Comparator;



public class ContactName {
	private String fFirstName, fLastName;
	
	
	public ContactName() {
		new ContactName("","");
	}
	
	public ContactName(String firstName, String lastName) {
		this.fFirstName = firstName;
		this.fLastName = lastName;
	}
	
	@Override
	public String toString() {
		String fullNameString = fFirstName + " " + fLastName;
		return fullNameString;
	}
	
	
	public String getFirstName() {
		return fFirstName;
	}
	
	public String getLastName() {
		return fLastName;
	}
	
	public void setFirstName(String firstName) {
		this.fFirstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.fLastName = lastName;
	}
}
