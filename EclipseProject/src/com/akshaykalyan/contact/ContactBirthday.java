package com.akshaykalyan.contact;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class to represent a Contact Birthday object. 
 * @author Akshay Pravin Kalyan | akal881 | 5786866
 */
public class ContactBirthday implements Parcelable {
	private String fBirthday;
	
	/**
	 * Default constructor returns object with empty fBirthday field
	 */
	public ContactBirthday() {
		new ContactBirthday("");
	}
	
	/**
	 * Constructor returns object with given birthday. Format required:
	 * D(D)-M(M)-YYYY
	 * @param birthday
	 */
	public ContactBirthday(String birthday) {
		this.fBirthday = birthday;
	}
	
	// ====================================================================
    // 		Getters and Setters 
    // ====================================================================
	
	public String getBirthday() {
		return fBirthday;
	}
	
	public void setBirthday(String fBirthday) {
		this.fBirthday = fBirthday;
	}
	
	// ====================================================================
    // 		toString
    // ====================================================================
	
	@Override
	public String toString() {
		return fBirthday;
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
		dest.writeValue(fBirthday);
	}
	
	public static final Parcelable.Creator<ContactBirthday> CREATOR = new Parcelable.Creator<ContactBirthday>() {
		public ContactBirthday createFromParcel(Parcel in) {
			String birthday = (String)in.readValue(getClass().getClassLoader());
			
			return new ContactBirthday(birthday);
		};
		@Override
		public ContactBirthday[] newArray(int size) {
			return new ContactBirthday[size];
		}
	}; 
}

