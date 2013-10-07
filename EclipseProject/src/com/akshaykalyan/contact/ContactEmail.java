package com.akshaykalyan.contact;

import java.util.regex.Pattern;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

/**
 * Class to represent a Contact Email object. 
 * @author Akshay Pravin Kalyan | akal881 | 5786866
 */
public class ContactEmail implements Parcelable {
	private String fEmail;
	
	/**
	 * Default constructor returns object containing empty email address
	 */
	public ContactEmail() {
		new ContactEmail("");
	}
	
	/**
	 * Constructor creates and returns object with given email adress
	 * @param emailString
	 */
	public ContactEmail(String emailString) {
		this.fEmail = emailString;
	}
	
	/**
	 * Getters and Setters
	 */
	public String getEmail() {
		return fEmail;
	}
	
	public void setEmail(String emailString) {
		this.fEmail = emailString;
	}
	
	@Override
	public String toString() {
		return fEmail;
	}
	
	/**
	 * Parcelable Interface Methods
	 */
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
	
	/**
	 * Inner class used to validate email address strings. 
	 * @author Akshay Pravin Kalyan | akal881 | 5786866
	 *
	 */
	public static class Validation {
		private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+" +
				"(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		private static final String MSG = "Invalid Email";
		
		public static boolean isValidEmailAddress(EditText editText) {
			
			String text = editText.getText().toString().trim();
			
			// if empty, field is valid
	        if (!hasText(editText) ) {
	        	return true;
	        }
	        // if pattern does not match, set error and return false
	        if (!Pattern.matches(EMAIL_REGEX, text)) {
	            editText.setError(MSG);
	            return false;
	        } else {
	        	return true;
	        }
	    }
		
		public static boolean hasText(EditText editText) {
	        String text = editText.getText().toString().trim();
	        // clear any error views
	        editText.setError(null);
	        if (text.length() == 0) {
	            return false;
	        }
	        return true;
	    }
	}
}
