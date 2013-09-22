package com.akshaykalyan.contact;

import java.util.Comparator;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class to represent a Contact object. 
 * @author Akshay Pravin Kalyan | akal881 | 5786866
 */
public class Contact implements Parcelable {

	private ContactName fName;
	private ContactAddress fAddress;
	private ContactEmail fEmail;
	private ContactPhone fPhone;
	private ContactBirthday fBirthday;
	private ContactPhoto fPhoto;
	
	/**
	 * SortBy enumeration types returns a Comparator object allowing Contact objects to be sorted in various ways.
	 * 
	 * FirstName - Sorts contacts by first name attribute
	 * LastName - Sorts contacts by last name attribute  
	 * PhoneNumberMobile - Sorts contacts by the Mobile number attribute of the Phone object composed within a Contact
	 * PhoneNumberHome - Sorts contacts by the Home number attribute of the Phone object composed within a Contact
	 * PhoneNumberWork - Sorts contacts by the Work number attribute of the Phone object composed within a Contact
	 */
	public enum SortBy {
		FirstName(1), LastName(2), PhoneNumberMobile(3), PhoneNumberHome(4), PhoneNumberWork(5);
		
		public Comparator<Contact> fComparator;
		
		private SortBy(int i) {
			switch (i) {
				case 1:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							if (lhs.fName.getFirstName().isEmpty()) {
								return 1;
								
							} else if (rhs.fName.getFirstName().isEmpty()) {
								return -1;
							}
							return lhs.fName.getFirstName().compareTo(rhs.fName.getFirstName());
						};
					};
					break;
				case 2:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							if (lhs.fName.getLastName().isEmpty()) {
								return 1;
								
							} else if (rhs.fName.getLastName().isEmpty()) {
								return -1;
							}
							return lhs.fName.getLastName().compareTo(rhs.fName.getLastName());
						};
					};
					break;
				case 3:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							if (lhs.fPhone.getMobilePhone().isEmpty()) {
								return 1;
								
							} else if (rhs.fPhone.getMobilePhone().isEmpty()) {
								return -1;
							}
							return lhs.fPhone.getMobilePhone().compareTo(rhs.fPhone.getMobilePhone());
						};
					};
					break;
				case 4:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							// empty string is lower in order
							if (lhs.fPhone.getHomePhone().isEmpty()) {
								return 1;
								
							} else if (rhs.fPhone.getHomePhone().isEmpty()) {
								return -1;
							}
							return lhs.fPhone.getHomePhone().compareTo(rhs.fPhone.getHomePhone());
						};
					};
					break;
				case 5:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							// empty string is lower in order
							if (lhs.fPhone.getWorkPhone().isEmpty()) {
								return 1;
								
							} else if (rhs.fPhone.getWorkPhone().isEmpty()) {
								return -1;
							}
							return lhs.fPhone.getWorkPhone().compareTo(rhs.fPhone.getWorkPhone());
						};
					};
					break;
				default:
					break;
			}
		}
		
	}
	
	/**
	 * Constructor which creates a new Contact object with respective fields instantiated. 
	 * Strings and a bitmap object is expected
	 * 
	 * @param firstName - contact's first name
	 * @param lastName - contact's last name
	 * @param mobilePhone - contact's mobile phone number
	 * @param workPhone - contact's work phone number
	 * @param homePhone - contact's home phone number
	 * @param email - email address 
	 * @param birthday - date of birthday (Input to be formatted as D(D)-M(M)-YYYY
	 * @param contactAddressLine1 - address line one 
	 * @param contactAddressLine2 - address line two
	 * @param contactAddressLine3 - address line three
	 * @param contactAddressLine4 - address line four
	 * @param photo - Bitmap object denoting the contact's photo attribute
	 */
	public Contact(String firstName, String lastName, 
			String mobilePhone, String workPhone, String homePhone, 
			String email, 
			String birthday,
			String contactAddressLine1, String contactAddressLine2, String contactAddressLine3, String contactAddressLine4,  
			Bitmap photo) {
		this.fName = new ContactName(firstName, lastName);
		this.fAddress = new ContactAddress(contactAddressLine1, contactAddressLine2, contactAddressLine3, contactAddressLine4);
		this.fEmail = new ContactEmail(email);
		this.fPhone = new ContactPhone(mobilePhone, homePhone, workPhone);
		this.fBirthday = new ContactBirthday(birthday);
		this.fPhoto = new ContactPhoto(photo);
	}
	
	/**
	 * Constructor which creates a contact object from the various associating Contact attribute objects
	 * @param name
	 * @param phone
	 * @param email
	 * @param birthday
	 * @param address
	 * @param photo
	 */
	public Contact(ContactName name, ContactPhone phone, ContactEmail email, ContactBirthday birthday,
					ContactAddress address, ContactPhoto photo) {
		this.fName = name;
		this.fPhone = phone;
		this.fEmail = email;
		this.fBirthday = birthday;
		this.fAddress = address;
		this.fPhoto = photo;
	}
	
	/**
	 * Name - Setter 
	 */
	public void setfName(String firstName, String lastName) {
		this.fName.setFirstName(firstName);
		this.fName.setLastName(lastName);
	}
	
	/**
	 * Name - Getter 
	 */
	public ContactName getfName() {
		return fName;
	}
	
	/**
	 * Address - Setter
	 */
	public void setfAddress(String addressLine1, String addressLine2, String addressLine3, String addressLine4) {
		this.fAddress.setAddressLine1(addressLine1);
		this.fAddress.setAddressLine2(addressLine2);
		this.fAddress.setAddressLine3(addressLine3);
		this.fAddress.setAddressLine4(addressLine4);
	}
	
	/**
	 * Address - Getter
	 */
	public ContactAddress getfAddress() {
		return fAddress;
	}
	
	/**
	 * Email - Setter
	 */
	public void setfEmail(String emailString) {
		this.fEmail.setEmailString(emailString);
	}
	
	/**
	 * Email - Getter
	 */
	public ContactEmail getfEmail() {
		return fEmail;
	}
	
	/**
	 * Phone - Setter
	 */
	public void setfPhone(String mobilePhone, String homePhone, String workPhone) {
		this.fPhone.setMobilePhone(mobilePhone);
		this.fPhone.setHomePhone(homePhone);
		this.fPhone.setWorkPhone(workPhone);
	}
	/**
	 * Phone - Getter
	 */
	public ContactPhone getfPhone() {
		return fPhone;
	}
	
	/**
	 * Birthday - Setter
	 */
	public void setfBirthday(String birthday) {
		this.fBirthday.setfBirthday(birthday);
	}
	
	/**
	 * Birthday - Getter
	 */
	public ContactBirthday getfBirthday() {
		return fBirthday;
	}
	
	/**
	 * Photo - Setter
	 */
	public void setfPhoto(Bitmap photoBitmap) {
		this.fPhoto.setPhotoBitmap(photoBitmap);
	}
	
	/**
	 * Photo - Getter
	 */
	public ContactPhoto getfPhoto() {
		return fPhoto;
	}
	
	
	
	/** Parcelable Interface Methods */
	
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
			
			
			return new Contact(name, phone, email, birthday, address, photo);
		}
		
		@Override
		public Contact[] newArray(int size) {
			return new Contact[size];
		}
	};
	
	

	@Override
	/**
	 * Override to String to return contact name in lower-case for a list adapter's filter
	 * function to work with this Contact implementation.
	 */
	public String toString() {
		return getfName().toString().toLowerCase();
	}
}
