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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ContactInformationActivity extends Activity {

	private TextView tvName, tvMobile, tvHome, tvWork, tvEmail, tvBirthday, tvAddressLine1,
						tvAddressLine2, tvAddressLine3, tvAddressLine4;
	private Contact fContact;
	private List<TextView> labelList = new ArrayList<TextView>();

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
		
		
//		if (savedInstanceState == null) {
//			Toast.makeText(getApplicationContext(), "Bundle is NULL", Toast.LENGTH_SHORT).show();
//		}


		Intent intent = getIntent();
		
		//B
		fContact = (Contact) intent.getExtras().get("CONTACT_OBJECT");
		
		//Name
		if (fContact.getfName().toString().length() > 0) {
			tvName.setText(fContact.getfName().toString());
		} else {
			tvName.setText("-");
		}
		
		// Mobile Phone
		if (fContact.getfPhone().getMobilePhone().length() > 0) {
			tvMobile.setText(fContact.getfPhone().getMobilePhone());
			// add to label list for typeface change
			labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_mobile));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_mobile);
			parent.removeView(child);
		}
		
		// Home Phone
		if (fContact.getfPhone().getHomePhone().length() > 0) {
			tvHome.setText(fContact.getfPhone().getHomePhone());
			// add to label list for typeface change
			labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_home));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_home);
			parent.removeView(child);
		}
		
		// Work Phone
		if (fContact.getfPhone().getWorkPhone().length() > 0) {
			tvWork.setText(fContact.getfPhone().getWorkPhone());
			// add to label list for typeface change
			labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_work));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_work);
			parent.removeView(child);
		}
		
		// Email
		if (fContact.getfEmail().getEmailString().length() > 0) {
			tvEmail.setText(fContact.getfEmail().getEmailString());
			// add to label list for typeface change
			labelList.add((TextView)findViewById(R.id.label_contactinfo_email));
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_email);
			parent.removeView(child);
		}
		
		// Birthday
		if (fContact.getfBirthday().toString().length() > 0) {
			tvBirthday.setText(fContact.getfBirthday().toString());
			// add to label list for typeface change
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
			// add to label list for typeface change
			labelList.add((TextView)findViewById(R.id.label_contactinfo_address));
			
			// update fields
			tvAddressLine1.setText(fContact.getfAddress().getAddressLine1());
			tvAddressLine2.setText(fContact.getfAddress().getAddressLine2());
			tvAddressLine3.setText(fContact.getfAddress().getAddressLine3());
			tvAddressLine4.setText(fContact.getfAddress().getAddressLine4());
			
		} else {
			ViewGroup parent = (ViewGroup)findViewById(R.id.viewgroupparent_contactinfo);
			View child = (View)findViewById(R.id.viewgroup_contactinfo_address);
			parent.removeView(child);
		}
		
		// set font typeface of labels		
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_information, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_delete_contact:
			DialogFragment confirmDeleteDialogFragment = new ConfirmDeleteDialog();
        	confirmDeleteDialogFragment.show(getFragmentManager(), "ConfirmDelete");
        	break;
		case R.id.action_edit_contact:
//			Intent intent = new Intent(getApplicationContext(), ContactEditActivity.class);
//			
//        	startActivity(intent);
			startEditContactActivity();
        	break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void startEditContactActivity() {
		Intent intent = new Intent(getApplicationContext(), ContactEditActivity.class);
		intent.putExtra("PARENT_ACTIVITY", ContactInformationActivity.class);
		intent.putExtra("CONTACT_OBJECT", fContact);
		startActivity(intent);
	}
	
	
	public void onClick_makePhoneCallMobile(View v) {
		TextView numberTextView = (TextView)findViewById(R.id.textview_contactinfo_phone_mobile);
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+numberTextView.getText().toString().trim()));
		startActivity(callIntent);
	}
	
	public void onClick_makePhoneCallHome(View v) {
		TextView numberTextView = (TextView)findViewById(R.id.textview_contactinfo_phone_home);
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+numberTextView.getText().toString().trim()));
		startActivity(callIntent);
	}
	
	public void onClick_makePhoneCallWork(View v) {
		TextView numberTextView = (TextView)findViewById(R.id.textview_contactinfo_phone_work);
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+numberTextView.getText().toString().trim()));
		startActivity(callIntent);
	}
	
	public void onClick_makeSMSMessage(View v) {
		TextView numberTextView = (TextView)findViewById(R.id.textview_contactinfo_phone_mobile);
		Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + numberTextView.getText().toString().trim()));
		startActivity(smsIntent);
	}
	
	public void onClick_makeEmail(View v) {
		TextView emailTextView = (TextView)findViewById(R.id.textview_contactinfo_email);
		String emailString = emailTextView.getText().toString().trim();
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", emailString, null));
		startActivity(Intent.createChooser(emailIntent, "Please select Email Client"));
		
	}
	
	public void makeMapSearch(View v) {
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
	
    public static class ConfirmDeleteDialog extends DialogFragment {
    	@Override
    	public Dialog onCreateDialog(Bundle saveInstanceState) {
    		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    		builder.setTitle(R.string.dialog_confirmdelete_title);
    		builder.setMessage(R.string.dialog_confirmdelete_message)
    			.setPositiveButton(R.string.action_yes, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub	
						dismiss();
					}
				})
				.setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dismiss();
					}
				});
    		return builder.create();
    		
    		
    		
    	}
    }
}
