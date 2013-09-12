package com.akshaykalyan.contactmanager;



import java.util.Comparator;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


public class Contact implements Parcelable {

	
	private ContactName fName;
	private ContactAddress fAddress;
	private ContactEmail fEmail;
	private ContactPhone fPhone;
	private ContactBirthday fBirthday;
	private ContactPhoto fPhoto;
	
	public enum SortBy {
		FirstName(1), LastName(2), PhoneNumberMobile(3), PhoneNumberHome(4), PhoneNumberWork(5);
		
		Comparator<Contact> fComparator;
		
		private SortBy(int i) {
			switch (i) {
				case 1:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							return lhs.fName.getFirstName().compareTo(rhs.fName.getFirstName());
						};
					};
					break;
				case 2:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							return lhs.fName.getLastName().compareTo(rhs.fName.getLastName());
						};
					};
					break;
				case 3:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							return lhs.fPhone.getMobilePhone().compareTo(rhs.fPhone.getMobilePhone());
						};
					};
					break;
				case 4:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							return lhs.fPhone.getHomePhone().compareTo(rhs.fPhone.getHomePhone());
						};
					};
					break;
				case 5:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							return lhs.fPhone.getWorkPhone().compareTo(rhs.fPhone.getWorkPhone());
						};
					};
					break;
				default:
					break;
			}
		}
		
	}
	
	public Contact(String firstName, String lastName, 
			String contactAddressLine1, String contactAddressLine2, String contactAddressLine3, String contactAddressLine4, 
			String email, 
			String mobilePhone, String workPhone, String homePhone, 
			String birthday, 
			Bitmap photo) {
		this.fName = new ContactName(firstName, lastName);
		this.fAddress = new ContactAddress(contactAddressLine1, contactAddressLine2, contactAddressLine3, contactAddressLine4);
		this.fEmail = new ContactEmail(email);
		this.fPhone = new ContactPhone(mobilePhone, homePhone, workPhone);
		this.fBirthday = new ContactBirthday(birthday);
		this.fPhoto = new ContactPhoto(photo);
	}
	
	public Contact(ContactName name, ContactPhoto photo, ContactPhone phone, ContactEmail email, ContactBirthday birthday,
					ContactAddress address) {
		this.fName = name;
		this.fPhoto = photo;
		this.fPhone = phone;
		this.fEmail = email;
		this.fBirthday = birthday;
		this.fAddress = address;
	}
	
	//NAME
	public void setfName(String firstName, String lastName) {
		this.fName.setFirstName(firstName);
		this.fName.setLastName(lastName);
	}
	
	public ContactName getfName() {
		return fName;
	}
	
	//ADDRESS
	public void setfAddress(String addressLine1, String addressLine2, String addressLine3, String addressLine4) {
		this.fAddress.setAddressLine1(addressLine1);
		this.fAddress.setAddressLine2(addressLine2);
		this.fAddress.setAddressLine3(addressLine3);
		this.fAddress.setAddressLine4(addressLine4);
	}
	
	public ContactAddress getfAddress() {
		return fAddress;
	}
	
	//EMAIL
	public void setfEmail(String emailString) {
		this.fEmail.setEmailString(emailString);
	}
	
	public ContactEmail getfEmail() {
		return fEmail;
	}
	
	//PHONE	
	public void setfPhone(String mobilePhone, String homePhone, String workPhone) {
		this.fPhone.setMobilePhone(mobilePhone);
		this.fPhone.setHomePhone(homePhone);
		this.fPhone.setWorkPhone(workPhone);
	}
	
	public ContactPhone getfPhone() {
		return fPhone;
	}
	
	// BIRTHDAY
	public void setfBirthday(String birthday) {
		this.fBirthday.setfBirthday(birthday);
	}
	
	public ContactBirthday getfBirthday() {
		return fBirthday;
	}
	
	// PHOTO
	public void setfPhoto(Bitmap photoBitmap) {
		this.fPhoto.setPhotoBitmap(photoBitmap);
	}
	
	public ContactPhoto getfPhoto() {
		return fPhoto;
	}
	
	// Parcelable methods
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(fPhoto);
		dest.writeValue(fName);
		dest.writeValue(fPhone);
		dest.writeValue(fEmail);
		dest.writeValue(fBirthday);
		dest.writeValue(fAddress);
	}
	
	public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
		@Override
		public Contact createFromParcel(Parcel in) {
			ContactPhoto photo = (ContactPhoto)in.readValue(getClass().getClassLoader());
			ContactName name = (ContactName)in.readValue(getClass().getClassLoader());
			ContactPhone phone = (ContactPhone)in.readValue(getClass().getClassLoader());
			ContactEmail email = (ContactEmail)in.readValue(getClass().getClassLoader());
			ContactBirthday birthday = (ContactBirthday)in.readValue(getClass().getClassLoader());
			ContactAddress address = (ContactAddress)in.readValue(getClass().getClassLoader());
			
			
			return new Contact(name, photo, phone, email, birthday, address);
		}
		
		@Override
		public Contact[] newArray(int size) {
			return new Contact[size];
		}
	};
}
