package com.akshaykalyan.contactmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ContactName {
	private String firstName, lastName;
	
	public enum SortBy {
		FirstName(1), LastName(2);
		Comparator<ContactName> fComparator;
		private SortBy(int i) {
			switch (i) {
			case 1:
				fComparator = new Comparator<ContactName>() {
					public int compare(ContactName lhs, ContactName rhs) {
						return lhs.firstName.compareTo(rhs.firstName);
					};
				};
				break;
			case 2:
				fComparator = new Comparator<ContactName>() {
					public int compare(ContactName lhs, ContactName rhs) {
						return lhs.lastName.compareTo(rhs.lastName);
					};
				};
			}
			
		}
	}
	
	public ContactName() {
		new ContactName("","");
	}
	
	public ContactName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		String fullNameString = firstName + " " + lastName;
		return fullNameString;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
}
