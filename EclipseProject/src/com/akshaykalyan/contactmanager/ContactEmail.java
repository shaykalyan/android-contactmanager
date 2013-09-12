package com.akshaykalyan.contactmanager;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactEmail implements Parcelable {
	private String fEmailString;
	
	public ContactEmail() {
		new ContactEmail("");
	}
	
	public ContactEmail(String emailString) {
		this.fEmailString = emailString;
	}
	
	public String getEmailString() {
		return fEmailString;
	}
	
	public void setEmailString(String emailString) {
		this.fEmailString = emailString;
	}
	
	@Override
	public String toString() {
		return fEmailString;
	}
	
	//Parcelable methods
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(fEmailString);
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
		fEmailString = (String)in.readValue(getClass().getClassLoader());
	}
}
