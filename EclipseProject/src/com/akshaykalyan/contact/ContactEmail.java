package com.akshaykalyan.contact;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactEmail implements Parcelable {
	private String fEmail;
	
	public ContactEmail() {
		new ContactEmail("");
	}
	
	public ContactEmail(String emailString) {
		this.fEmail = emailString;
	}
	
	public String getEmailString() {
		return fEmail;
	}
	
	public void setEmailString(String emailString) {
		this.fEmail = emailString;
	}
	
	@Override
	public String toString() {
		return fEmail;
	}
	
	//Parcelable methods
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(fEmail);
	}
	
	public static final Parcelable.Creator<ContactEmail> CREATOR = new Parcelable.Creator<ContactEmail>() {
		public ContactEmail createFromParcel(Parcel in) {
			String emailString = (String)in.readValue(getClass().getClassLoader());
			return new ContactEmail(emailString);
		};
		
		public ContactEmail[] newArray(int size) {
			return new ContactEmail[size];
		};
	};
	
	private ContactEmail(Parcel in) {
		fEmail = (String)in.readValue(getClass().getClassLoader());
	}
}
