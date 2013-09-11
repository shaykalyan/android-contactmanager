package com.akshaykalyan.contactmanager;

import java.util.Comparator;



public class ContactName {
	private String fFirstName, fLastName;
	
	public enum SortBy {
		FirstName(1), LastName(2);
		Comparator<ContactName> fComparator;
		private SortBy(int i) {
			switch (i) {
			case 1:
				fComparator = new Comparator<ContactName>() {
					public int compare(ContactName lhs, ContactName rhs) {
						return lhs.fFirstName.compareTo(rhs.fFirstName);
					};
				};
				break;
			case 2:
				fComparator = new Comparator<ContactName>() {
					public int compare(ContactName lhs, ContactName rhs) {
						return lhs.fLastName.compareTo(rhs.fLastName);
					};
				};
				break;
			}
			
		}
	}
	
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
