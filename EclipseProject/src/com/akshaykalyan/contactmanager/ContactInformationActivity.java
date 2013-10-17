package com.akshaykalyan.contactmanager;

import java.util.ArrayList;
import java.util.List;

import com.akshaykalyan.contact.*;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity class representing the Contact Information screen of a contact. This activity is 
 * focused after selecting a contact item from the ContactListActivity activity. The 
 * corresponding Contact object is passed through an intent, unpacked and its content 
 * displayed to the respective views.
 * 
 * @author Akshay Pravin Kalyan | akal881 | 57886866
 */
public class ContactInformationActivity extends Activity {

	private TextView tvName, tvMobile, tvHome, tvWork, tvEmail, tvBirthday, tvAddressLine1,
					 tvAddressLine2, tvAddressLine3, tvAddressLine4;
	private ImageView ivPhoto;
	private static Contact fContact;
	private List<TextView> labelList = new ArrayList<TextView>();

	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_information);
		// Show the Up button in the action bar.
		setupActionBar();
		
		tvName = (TextView)findViewById(R.id.textview_contactinfo_name);
		tvMobile = (TextView)findViewById(R.id.textview_contactinfo_phone_mobile);
		tvHome = (TextView)findViewById(R.id.textview_contactinfo_phone_home);
		tvWork = (TextView)findViewById(R.id.textview_contactinfo_phone_work);
		tvEmail = (TextView)findViewById(R.id.textview_contactinfo_email);
		tvBirthday = (TextView)findViewById(R.id.textview_contactinfo_birthday);
		tvAddressLine1 = (TextView)findViewById(R.id.textview_contactinfo_addressline1);
		tvAddressLine2 = (TextView)findViewById(R.id.textview_contactinfo_addressline2);
		tvAddressLine3 = (TextView)findViewById(R.id.textview_contactinfo_addressline3);
		tvAddressLine4 = (TextView)findViewById(R.id.textview_contactinfo_addressline4);
		ivPhoto = (ImageView)findViewById(R.id.image_contactinfo_contact_image);
		
		Intent intent = getIntent();
		fContact = (Contact) intent.getExtras().get("CONTACT_OBJECT");
		
		// Photo
		ivPhoto.setImageBitmap(fContact.getPhoto().getPhotoBitmap());
		
		// for each non-empty field in Contact, populate view
		// if state exists
		//			set text
		//			add respective label to list for typeface change
		// else
		//			remove view group		
		
		// Name
		if (fContact.getName().toString().length() > 0) {
			tvName.setText(fContact.getName().toString());
		} else {
			tvName.setText("-");
		}
		
		// Mobile Phone
		if (fContact.getPhone().getMobilePhone().length() > 0) {
			tvMobile.setText(fContact.getPhone().getMobilePhone());
			labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_mobile));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_mobile);
			parent.removeView(child);
		}
		
		// Home Phone
		if (fContact.getPhone().getHomePhone().length() > 0) {
			tvHome.setText(fContact.getPhone().getHomePhone());
			labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_home));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_home);
			parent.removeView(child);
		}
		
		// Work Phone
		if (fContact.getPhone().getWorkPhone().length() > 0) {
			tvWork.setText(fContact.getPhone().getWorkPhone());
			labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_work));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_work);
			parent.removeView(child);
		}
		
		// Email
		if (fContact.getEmail().getEmail().length() > 0) {
			tvEmail.setText(fContact.getEmail().getEmail());
			labelList.add((TextView)findViewById(R.id.label_contactinfo_email));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_email);
			parent.removeView(child);
		}
		
		// Birthday
		if (fContact.getBirthday().toString().length() > 0) {
			tvBirthday.setText(fContact.getBirthday().toString());
			labelList.add((TextView)findViewById(R.id.label_contactinfo_birthday));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_birthday);
			parent.removeView(child);
		}
		
		// Address
		if (fContact.getAddress().getAddressLine1().length() > 0
				|| fContact.getAddress().getAddressLine2().length() > 0
				|| fContact.getAddress().getAddressLine3().length() > 0
				||fContact.getAddress().getAddressLine4().length() > 0) {

			labelList.add((TextView)findViewById(R.id.label_contactinfo_address));

			tvAddressLine1.setText(fContact.getAddress().getAddressLine1());
			tvAddressLine2.setText(fContact.getAddress().getAddressLine2());
			tvAddressLine3.setText(fContact.getAddress().getAddressLine3());
			tvAddressLine4.setText(fContact.getAddress().getAddressLine4());
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_address);
			parent.removeView(child);
		}
		
		// set font type face to all labels in list
		for (TextView textView : labelList) {
			Typeface tf = FontRobotoLight.getTypeface(this);
	    	textView.setTypeface(tf); 
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * @see android.app.Activity#onCreateOptionsMenu(Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contact_information, menu);
		return true;
	}

	@Override
	/**
	 * Override back to create new ListActivty as underlying database may have updated 
	 * via EditActivity
	 */
	public void onBackPressed() {
		Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    	startActivity(intent);
    	super.onBackPressed();
	}
	
	/**
	 * @see android.app.Activity#onOptionsItemSelected(MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        	startActivity(intent);
        	finish();
        	break;
		case R.id.action_delete_contact:
			DialogFragment confirmDeleteDialogFragment = new ConfirmDeleteDialog();
        	confirmDeleteDialogFragment.show(getFragmentManager(), "ConfirmDelete");
        	break;
		case R.id.action_edit_contact:
			startEditContactActivity();
        	break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Method to create intent containing the Contact object and reference to this class.
	 * Starts activity to Edit screen
	 */
	private void startEditContactActivity() {
		Intent intent = new Intent(getApplicationContext(), ContactEditActivity.class);
		intent.putExtra("PARENT_ACTIVITY", ContactInformationActivity.class);
		intent.putExtra("CONTACT_OBJECT", fContact);
		startActivity(intent);
	}
	
	// ====================================================================
    // 			Confirm Delete Dialog
    // ====================================================================
			
	/**
	 * Inner Class responsible for generating a confirmation dialog when a delete request has 
	 * been placed by the user. The response will be handled respective to the decision made.
	 * 
	 * Yes - removes contact and returns to list view
	 * Cancel - returns focus to activity
	 */
    public static class ConfirmDeleteDialog extends DialogFragment {
    	@Override
    	public Dialog onCreateDialog(Bundle saveInstanceState) {
    		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    		builder.setTitle(R.string.dialog_confirmdelete_title);
    		builder.setMessage(R.string.dialog_confirmdelete_message)
    			.setPositiveButton(R.string.action_yes, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {						
						// db remove contact
						DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());
						db.deleteContact(fContact.getId());
												
						// show toast
						LayoutInflater inflater = getActivity().getLayoutInflater();
				    	View view = inflater.inflate(R.layout.toast_contact_removed, (ViewGroup)getActivity().findViewById(R.id.toast_contact_removed));
				        Toast toast = new Toast(getActivity().getApplicationContext());
				        toast.setView(view);
				        toast.show();
				        
				        Intent intent = new Intent(getActivity().getApplicationContext(), ContactListActivity.class);
				        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    	startActivity(intent);
                    	getActivity().finish();
					}
				})
				.setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// returns focus to activity
						dismiss();
					}
				});
    		return builder.create();
    	}
    }
    
    // ====================================================================
    // 			Interactive onClick methods
    // ====================================================================
    
    /**
	 * onClick method which extracts the Mobile Phone field, creates call intent and fires.
	 */
	public void onClick_makePhoneCallMobile(View v) {
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+tvMobile.getText().toString().trim()));
		startActivity(callIntent);
	}
	
	/**
	 * onClick method which extracts the Home Phone field, creates call intent and fires.
	 */
	public void onClick_makePhoneCallHome(View v) {
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+tvHome.getText().toString().trim()));
		startActivity(callIntent);
	}
	
	/**
	 * onClick method which extracts the Work Phone field, creates call intent and fires.
	 */
	public void onClick_makePhoneCallWork(View v) {
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+tvWork.getText().toString().trim()));
		startActivity(callIntent);
	}
	
	/**
	 * onClick method which extracts the Mobile Phone field, creates SMS intent and fires.
	 */
	public void onClick_makeSMSMessage(View v) {
		Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + tvMobile.getText().toString().trim()));
		startActivity(smsIntent);
	}
	
	/**
	 * onClick method which extracts the Email field, creates email intent and fires.
	 */
	public void onClick_makeEmail(View v) {
		String emailString = tvEmail.getText().toString().trim();
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", emailString, null));
		startActivity(Intent.createChooser(emailIntent, "Please select Email Client"));
	}
	
	/**
	 * onClick method which extracts the Address field, creates map intent and fires.
	 */
	public void onClick_makeMapSearch(View v) {
		StringBuilder addressStringBuilder = new StringBuilder();
		List<TextView> addressList = new ArrayList<TextView>();
		
		addressList.add(tvAddressLine1);
		addressList.add(tvAddressLine2);
		addressList.add(tvAddressLine3);
		addressList.add(tvAddressLine4);
		for (TextView textView : addressList) {
			String addressLineString = textView.getText().toString().trim();
			if (!addressLineString.isEmpty()) {
				addressStringBuilder.append(addressLineString);
				addressStringBuilder.append("+");
			}
		}		
		Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + addressStringBuilder.toString()));
		startActivity(geoIntent);
	}
}
