package com.akshaykalyan.contact;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactBirthday implements Parcelable {
	private String fBirthday;
	
	public ContactBirthday() {
		new ContactBirthday("");
	}
	
	public ContactBirthday(String birthday) {
		this.fBirthday = birthday;
	}
	
	public String getfBirthday() {
		return fBirthday;
	}
	
	public void setfBirthday(String fBirthday) {
		this.fBirthday = fBirthday;
	}
	
	@Override
	public String toString() {
		return fBirthday;
	}
	
	//Parcelable methods
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
	
	private ContactBirthday(Parcel in) {
		fBirthday = (String)in.readValue(getClass().getClassLoader());
	}
}

