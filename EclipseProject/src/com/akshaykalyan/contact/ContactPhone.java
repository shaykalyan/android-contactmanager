package com.akshaykalyan.contact;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class to represent a Contact Phone object. 
 * @author Akshay Pravin Kalyan | akal881 | 5786866
 */
public class ContactPhone implements Parcelable {
	private String fMobilePhone, fHomePhone, fWorkPhone;
	
	/**
	 * Default constructors creates Contact Phone object with empty phone numbers 
	 */
	public ContactPhone() {
		this.fMobilePhone = "";
		this.fHomePhone = "";
		this.fWorkPhone = "";
	}
	
	/**
	 * Constructs and returns Contact Phone object with given phone numbers 
	 */
	public ContactPhone(String mobilePhone, String homePhone, String workPhone) {
		this.fMobilePhone = mobilePhone;
		this.fHomePhone = homePhone;
		this.fWorkPhone = workPhone;
	}
	
	/**
	 * Getters and Setters
	 */
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
	
	/**
	 * Parcelable Interace Methods
	 */

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

