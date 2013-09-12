package com.akshaykalyan.contactmanager;

import android.os.Parcel;
import android.os.Parcelable;


public class ContactPhone implements Parcelable {
	private String fMobilePhone, fHomePhone, fWorkPhone;
	
	
	public ContactPhone(String mobilePhone, String homePhone, String workPhone) {
		this.fMobilePhone = mobilePhone;
		this.fHomePhone = homePhone;
		this.fWorkPhone = workPhone;
	}
	
	public String getHomePhone() {
		return fHomePhone;
	}
	public String getMobilePhone() {
		return fMobilePhone;
	}
	public String getWorkPhone() {
		return fWorkPhone;
	}
	
	public void setHomePhone(String homePhone) {
		this.fHomePhone = homePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.fMobilePhone = mobilePhone;
	}
	public void setWorkPhone(String workPhone) {
		this.fWorkPhone = workPhone;
	}
	
	// Parcelable methods
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(fMobilePhone);
		dest.writeValue(fHomePhone);
		dest.writeValue(fWorkPhone);
	}
	
	public static final Parcelable.Creator<ContactPhone> CREATOR = new Parcelable.Creator<ContactPhone>() {
		
		public ContactPhone createFromParcel(Parcel in) {
			String mobilePhone = (String)in.readValue(getClass().getClassLoader());
			String homePhone = (String)in.readValue(getClass().getClassLoader());
			String workPhone = (String)in.readValue(getClass().getClassLoader());
			
			return new ContactPhone(mobilePhone, homePhone, workPhone);
		};
		
		public ContactPhone[] newArray(int size) {
			return new ContactPhone[size];
		};
	};
	
	private ContactPhone(Parcel in) {
		fMobilePhone = (String)in.readValue(getClass().getClassLoader());
		fHomePhone = (String)in.readValue(getClass().getClassLoader());
		fWorkPhone = (String)in.readValue(getClass().getClassLoader());
	}
}

