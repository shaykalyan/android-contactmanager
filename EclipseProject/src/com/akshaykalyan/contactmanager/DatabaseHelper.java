package com.akshaykalyan.contactmanager;

import java.util.ArrayList;
import java.util.List;

import com.akshaykalyan.contact.Contact;

import android.R.integer;
import android.R.layout;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String LOG = "DatabaseHelper";
	
	// db name and version
	private static final String DATABASE_NAME = "ContactsManagerDatabase";
	private static final int DATABASE_VERSION = 1;
	
	// table name
	private static final String TABLE_CONTACTS = "contacts";
	
	// table column / contact fields
	private static final String KEY_ID = "id";
	private static final String KEY_NAME_FIRST = "firstname";
	private static final String KEY_NAME_LAST = "lastname";
	private static final String KEY_PHONE_MOBILE = "mobilephone";
	private static final String KEY_PHONE_HOME = "homephone";
	private static final String KEY_PHONE_WORK = "workphone";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_BIRTHDAY = "birthday";
	private static final String KEY_ADDRESS_LINE1 = "addressline1";
	private static final String KEY_ADDRESS_LINE2 = "addressline2";
	private static final String KEY_ADDRESS_LINE3 = "addressline3";
	private static final String KEY_ADDRESS_LINE4 = "addressline4";
	
	// table create statement 
	private static final String CREATE_TABLE_CONTACTS = "CREATE TABLE " + TABLE_CONTACTS + "("
			+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_NAME_FIRST + " TEXT,"
			+ KEY_NAME_LAST + " TEXT,"
			+ KEY_PHONE_MOBILE + " TEXT,"
			+ KEY_PHONE_HOME + " TEXT,"
			+ KEY_PHONE_WORK + " TEXT,"
			+ KEY_EMAIL + " TEXT,"
			+ KEY_BIRTHDAY + " TEXT,"
			+ KEY_ADDRESS_LINE1 + " TEXT,"
			+ KEY_ADDRESS_LINE2 + " TEXT,"
			+ KEY_ADDRESS_LINE3 + " TEXT,"
			+ KEY_ADDRESS_LINE4 + " TEXT"
			+ ")";
			
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_CONTACTS);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXITS " + TABLE_CONTACTS);
	}
	
	// CREATE | READ | UPDATE | DELETE
	
	/**
	 * Creating contact
	 */
	public long createContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME_FIRST, contact.getfName().getFirstName());
		values.put(KEY_NAME_LAST, contact.getfName().getLastName());
		values.put(KEY_PHONE_MOBILE, contact.getfPhone().getMobilePhone());
		values.put(KEY_PHONE_HOME, contact.getfPhone().getHomePhone());
		values.put(KEY_PHONE_WORK, contact.getfPhone().getWorkPhone());
		values.put(KEY_EMAIL, contact.getfEmail().getEmail());
		values.put(KEY_BIRTHDAY, contact.getfBirthday().getfBirthday());
		values.put(KEY_ADDRESS_LINE1, contact.getfAddress().getAddressLine1());
		values.put(KEY_ADDRESS_LINE2, contact.getfAddress().getAddressLine2());
		values.put(KEY_ADDRESS_LINE3, contact.getfAddress().getAddressLine3());
		values.put(KEY_ADDRESS_LINE4, contact.getfAddress().getAddressLine4());
		
		// insert row into table
		long contact_id = db.insert(TABLE_CONTACTS, null, values);
		
		return contact_id;		
	}
	
	/**
	 * Get single contact
	 */
	public Contact getContact(long contact_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " WHERE " 
				+ KEY_ID + " = " + contact_id;
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		Contact contact = new Contact(
				cursor.getString(cursor.getColumnIndex(KEY_NAME_FIRST)), 
				cursor.getString(cursor.getColumnIndex(KEY_NAME_LAST)),
				cursor.getString(cursor.getColumnIndex(KEY_PHONE_MOBILE)),
				cursor.getString(cursor.getColumnIndex(KEY_PHONE_HOME)), 
				cursor.getString(cursor.getColumnIndex(KEY_PHONE_WORK)),
				cursor.getString(cursor.getColumnIndex(KEY_EMAIL)), 
				cursor.getString(cursor.getColumnIndex(KEY_BIRTHDAY)),
				cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LINE1)), 
				cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LINE2)),
				cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LINE3)),
				cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LINE4)),
				null, //photo TODO
				cursor.getInt(cursor.getColumnIndex(KEY_ID)));
		
		return contact;
	}
	
	/**
	 * Get all contacts
	 */
	public List<Contact> getAllContacts() {
		List<Contact> contactsList = new ArrayList<Contact>();
	    String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

	 
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst( )) {
	        do {
	        	Contact contact = new Contact(
	    				cursor.getString(cursor.getColumnIndex(KEY_NAME_FIRST)), 
	    				cursor.getString(cursor.getColumnIndex(KEY_NAME_LAST)),
	    				cursor.getString(cursor.getColumnIndex(KEY_PHONE_MOBILE)),
	    				cursor.getString(cursor.getColumnIndex(KEY_PHONE_HOME)), 
	    				cursor.getString(cursor.getColumnIndex(KEY_PHONE_WORK)),
	    				cursor.getString(cursor.getColumnIndex(KEY_EMAIL)), 
	    				cursor.getString(cursor.getColumnIndex(KEY_BIRTHDAY)),
	    				cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LINE1)), 
	    				cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LINE2)),
	    				cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LINE3)),
	    				cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LINE4)),
	    				null, //photo TODO
	    				cursor.getInt(cursor.getColumnIndex(KEY_ID)));
	    		
	        	contactsList.add(contact);
	        } while (cursor.moveToNext());
	    }
	 
	    return contactsList;
	}
	
	/**
	 * Update a Contact
	 */
	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME_FIRST, contact.getfName().getFirstName());
		values.put(KEY_NAME_LAST, contact.getfName().getLastName());
		values.put(KEY_PHONE_MOBILE, contact.getfPhone().getMobilePhone());
		values.put(KEY_PHONE_HOME, contact.getfPhone().getHomePhone());
		values.put(KEY_PHONE_WORK, contact.getfPhone().getWorkPhone());
		values.put(KEY_EMAIL, contact.getfEmail().getEmail());
		values.put(KEY_BIRTHDAY, contact.getfBirthday().getfBirthday());
		values.put(KEY_ADDRESS_LINE1, contact.getfAddress().getAddressLine1());
		values.put(KEY_ADDRESS_LINE2, contact.getfAddress().getAddressLine2());
		values.put(KEY_ADDRESS_LINE3, contact.getfAddress().getAddressLine3());
		values.put(KEY_ADDRESS_LINE4, contact.getfAddress().getAddressLine4());
		
		// update the contact's row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " =  ?" , 
				new String[] { String.valueOf(contact.getfId()) });
	}
	
	/**
	 * Delete Contact
	 */
	public void deleteContact(int contact_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
				new String[] { String.valueOf(contact_id) });
	}
	
	
	
	
	
}
