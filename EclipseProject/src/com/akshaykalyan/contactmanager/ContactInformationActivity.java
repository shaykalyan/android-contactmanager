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

import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

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
	private Contact fContact;
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
		
		Intent intent = getIntent();
		fContact = (Contact) intent.getExtras().get("CONTACT_OBJECT");
		
		// for each field in Contact, populate views respectively
		// if state exists
		//			set text
		//			add respective label to list for typeface change
		// else
		//			remove respective view group		
		
		// Name
		if (fContact.getfName().toString().length() > 0) {
			tvName.setText(fContact.getfName().toString());
		} else {
			tvName.setText("-");
		}
		
		// Mobile Phone
		if (fContact.getfPhone().getMobilePhone().length() > 0) {
			tvMobile.setText(fContact.getfPhone().getMobilePhone());
			labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_mobile));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_mobile);
			parent.removeView(child);
		}
		
		// Home Phone
		if (fContact.getfPhone().getHomePhone().length() > 0) {
			tvHome.setText(fContact.getfPhone().getHomePhone());
			labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_home));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_home);
			parent.removeView(child);
		}
		
		// Work Phone
		if (fContact.getfPhone().getWorkPhone().length() > 0) {
			tvWork.setText(fContact.getfPhone().getWorkPhone());
			labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_work));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_work);
			parent.removeView(child);
		}
		
		// Email
		if (fContact.getfEmail().getEmailString().length() > 0) {
			tvEmail.setText(fContact.getfEmail().getEmailString());
			labelList.add((TextView)findViewById(R.id.label_contactinfo_email));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_email);
			parent.removeView(child);
		}
		
		// Birthday
		if (fContact.getfBirthday().toString().length() > 0) {
			tvBirthday.setText(fContact.getfBirthday().toString());
			labelList.add((TextView)findViewById(R.id.label_contactinfo_birthday));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_birthday);
			parent.removeView(child);
		}
		
		// Address
		if (fContact.getfAddress().getAddressLine1().length() > 0 ||
				fContact.getfAddress().getAddressLine2().length() > 0 ||
				fContact.getfAddress().getAddressLine3().length() > 0 ||
				fContact.getfAddress().getAddressLine4().length() > 0) {

			labelList.add((TextView)findViewById(R.id.label_contactinfo_address));
			
			tvAddressLine1.setText(fContact.getfAddress().getAddressLine1());
			tvAddressLine2.setText(fContact.getfAddress().getAddressLine2());
			tvAddressLine3.setText(fContact.getfAddress().getAddressLine3());
			tvAddressLine4.setText(fContact.getfAddress().getAddressLine4());
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_information, menu);
		return true;
	}

	/**
	 * @see android.app.Activity#onOptionsItemSelected(MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
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
	
	/**
	 * onClick method which extracts the Mobile Phone field, creates call intent and fires.
	 */
	public void onClick_makePhoneCallMobile(View v) {
		TextView numberTextView = (TextView)findViewById(R.id.textview_contactinfo_phone_mobile);
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+numberTextView.getText().toString().trim()));
		startActivity(callIntent);
	}
	
	/**
	 * onClick method which extracts the Home Phone field, creates call intent and fires.
	 */
	public void onClick_makePhoneCallHome(View v) {
		TextView numberTextView = (TextView)findViewById(R.id.textview_contactinfo_phone_home);
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+numberTextView.getText().toString().trim()));
		startActivity(callIntent);
	}
	
	/**
	 * onClick method which extracts the Work Phone field, creates call intent and fires.
	 */
	public void onClick_makePhoneCallWork(View v) {
		TextView numberTextView = (TextView)findViewById(R.id.textview_contactinfo_phone_work);
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+numberTextView.getText().toString().trim()));
		startActivity(callIntent);
	}
	
	/**
	 * onClick method which extracts the Mobile Phone field, creates SMS intent and fires.
	 */
	public void onClick_makeSMSMessage(View v) {
		TextView numberTextView = (TextView)findViewById(R.id.textview_contactinfo_phone_mobile);
		Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + numberTextView.getText().toString().trim()));
		startActivity(smsIntent);
	}
	
	/**
	 * onClick method which extracts the Email field, creates email intent and fires.
	 */
	public void onClick_makeEmail(View v) {
		TextView emailTextView = (TextView)findViewById(R.id.textview_contactinfo_email);
		String emailString = emailTextView.getText().toString().trim();
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", emailString, null));
		startActivity(Intent.createChooser(emailIntent, "Please select Email Client"));
		
	}
	
	/**
	 * onClick method which extracts the Address field, creates map intent and fires.
	 */
	public void onClick_makeMapSearch(View v) {
		StringBuilder addressStringBuilder = new StringBuilder();
		List<TextView> addressList = new ArrayList<TextView>();
		
		addressList.add((TextView)findViewById(R.id.textview_contactinfo_addressline1));
		addressList.add((TextView)findViewById(R.id.textview_contactinfo_addressline2));
		addressList.add((TextView)findViewById(R.id.textview_contactinfo_addressline3));
		addressList.add((TextView)findViewById(R.id.textview_contactinfo_addressline4));
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
						// TODO removes contact and returns to list view	
						LayoutInflater inflater = getActivity().getLayoutInflater();
				    	View view = inflater.inflate(R.layout.toast_contact_removed, (ViewGroup)getActivity().findViewById(R.id.toast_contact_removed));
				        Toast toast = new Toast(getActivity().getApplicationContext());
				        toast.setView(view);
				        toast.show();
				        getActivity().finish();
					}
				})
				.setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO returns focus to activity
						dismiss();
					}
				});
    		return builder.create();
    	}
    }
}
