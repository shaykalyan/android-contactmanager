package com.akshaykalyan.contact;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class to represent a Contact Address object. 
 * @author Akshay Pravin Kalyan | akal881 | 5786866
 */
public class ContactAddress implements Parcelable {
	private String fAddressLine1, fAddressLine2, fAddressLine3, fAddressLine4;
	
	/**
	 * Default constructor creates Address object with empty address
	 */
	public ContactAddress() {
		new ContactAddress("","","","");
	}
	
	/**
	 * Address constructor creates Address object with given address strings
	 */
	ContactAddress(String addressLine1, String addressLine2, String addressLine3, String addressLine4) {
		this.fAddressLine1 = addressLine1;
		this.fAddressLine2 = addressLine2;
		this.fAddressLine3 = addressLine3;
		this.fAddressLine4 = addressLine4;
	}
	
	// ====================================================================
    // 		Getters and Setters 
    // ====================================================================
	
	public String getAddressLine1() {
		return fAddressLine1;
	}
	public String getAddressLine2() {
		return fAddressLine2;
	}
	public String getAddressLine3() {
		return fAddressLine3;
	}
	public String getAddressLine4() {
		return fAddressLine4;
	}
	
	public void setAddressLine1(String addressLine1) {
		this.fAddressLine1 = addressLine1;
	}
	
	public void setAddressLine2(String addressLine2) {
		this.fAddressLine2 = addressLine2;
	}
	
	public void setAddressLine3(String addressLine3) {
		this.fAddressLine3 = addressLine3;
	}
	
	public void setAddressLine4(String addressLine4) {
		this.fAddressLine4 = addressLine4;
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
		dest.writeValue(fAddressLine1);
		dest.writeValue(fAddressLine2);
		dest.writeValue(fAddressLine3);
		dest.writeValue(fAddressLine4);
	}
	
	public static final Parcelable.Creator<ContactAddress> CREATOR = new Parcelable.Creator<ContactAddress>() {
		public ContactAddress createFromParcel(Parcel in) {
			String addressLine1 = (String)in.readValue(getClass().getClassLoader());
			String addressLine2 = (String)in.readValue(getClass().getClassLoader());
			String addressLine3 = (String)in.readValue(getClass().getClassLoader());
			String addressLine4 = (String)in.readValue(getClass().getClassLoader());
			
			return new ContactAddress(addressLine1, addressLine2, addressLine3, addressLine4);
		}
		
		public ContactAddress[] newArray(int size) {
			return new ContactAddress[size];
		}
	};
}
