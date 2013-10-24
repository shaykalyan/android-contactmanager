package com.akshaykalyan.contact;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class to represent a Contact Name object. 
 * @author Akshay Pravin Kalyan | akal881 | 5786866
 */
public class ContactName implements Parcelable {
	private String fFirstName, fLastName;
	
	/**
	 * Default constructor returns a Contact Name object with empty names
	 */
	public ContactName() {
		new ContactName("","");
	}
	
	/**
	 * Constructs and returns a Contact Name object with given names
	 */
	public ContactName(String firstName, String lastName) {
		this.fFirstName = firstName;
		this.fLastName = lastName;
	}
	
	// ====================================================================
    // 		Getters and Setters 
    // ====================================================================
	
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
	
	// ====================================================================
    // 		toString
    // ====================================================================
	
	@Override
	/**
	 * Formats first and last name fields delimited with a space
	 */
	public String toString() {
		String fullNameString = fFirstName + " " + fLastName;
		return fullNameString;
	}
	
	// ====================================================================
    // 		Parcelable Interface Methods
    // ====================================================================
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(fFirstName);
		dest.writeValue(fLastName);
	}
	
	public static final Parcelable.Creator<ContactName> CREATOR = new Parcelable.Creator<ContactName>() {
		
		public ContactName createFromParcel(Parcel in) {
			String firstName = (String)in.readValue(getClass().getClassLoader()); 
			String lastName = (String)in.readValue(getClass().getClassLoader()); 
			
			ContactName contactName = new ContactName(firstName, lastName);
			
			return contactName;
		}
			
		public ContactName[] newArray(int size) {
			return new ContactName[size];
		}
	};
}
