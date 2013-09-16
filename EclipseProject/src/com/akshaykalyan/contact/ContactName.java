package com.akshaykalyan.contact;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactName implements Parcelable {
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
	
	
	// Parcelable interface methods
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		//marcshall(serialize) object
		dest.writeValue(fFirstName);
		dest.writeValue(fLastName);
	}
	
	
		// regenerate object using CREATOR
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
	
	//Private constructor used when demarshalling (unserializing the custom object)
    private ContactName(Parcel in) {
        fFirstName         = (String)in.readValue(getClass().getClassLoader());       
        fLastName          =  (String)in.readValue(getClass().getClassLoader());
    }
}
