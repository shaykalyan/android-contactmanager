package com.akshaykalyan.contact;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class to represent a Contact Photo object. 
 * @author Akshay Pravin Kalyan | akal881 | 5786866
 */
public class ContactPhoto implements Parcelable {
	private Bitmap fPhotoBitmap;
	
//	/**
//	 * Default constructor initialises photo Bitmap to null
//	 */
//	public ContactPhoto() {
//		this.fPhotoBitmap = null;
//	}
	/**
	 * Constructs Contact Photo object with given photo Bitmap
	 * @param photoBitmap
	 */
	ContactPhoto(Bitmap photoBitmap) {
		this.fPhotoBitmap = photoBitmap;
	}
	
	/**
	 * Getters and Setters
	 */
	public Bitmap getPhotoBitmap() {
		return fPhotoBitmap;
	}
	
	public void setPhotoBitmap(Bitmap photoBitmap) {
		this.fPhotoBitmap = photoBitmap;
	}
	
	public byte[] getPhotoByteArray() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		this.fPhotoBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		return stream.toByteArray();
	}
	
	public static Bitmap getPhotoBitmap(byte[] byteArray) {
		Bitmap photo = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
		return photo;
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
		dest.writeValue(fPhotoBitmap);
	}
	
	public static final Parcelable.Creator<ContactPhoto> CREATOR = new Parcelable.Creator<ContactPhoto>() {
		public ContactPhoto createFromParcel(Parcel in) {
			Bitmap photoBitmap = (Bitmap)in.readValue(getClass().getClassLoader());
			
			return new ContactPhoto(photoBitmap);
		};
		
		public ContactPhoto[] newArray(int size) {
			return new ContactPhoto[size];
		};
	};
}
