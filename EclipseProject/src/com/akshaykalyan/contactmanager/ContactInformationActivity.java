package com.akshaykalyan.contactmanager;

import java.util.ArrayList;
import java.util.List;

import com.akshaykalyan.contactmanager.ContactListActivity.AboutDialog;

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
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ContactInformationActivity extends Activity {

	private List<TextView> labelList = new ArrayList<TextView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_information);
		// Show the Up button in the action bar.
		setupActionBar();


		labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_mobile));
		labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_home));
		labelList.add((TextView)findViewById(R.id.label_contactinfo_phone_work));
		labelList.add((TextView)findViewById(R.id.label_contactinfo_email));
		labelList.add((TextView)findViewById(R.id.label_contactinfo_birthday));
		labelList.add((TextView)findViewById(R.id.label_contactinfo_address));
		
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
		}
		return super.onOptionsItemSelected(item);
	}

	
	public void makePhoneCall(View v) {
		TextView numberTextView = (TextView)findViewById(R.id.textview_contactinfo_phone_mobile);
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+numberTextView.getText().toString().trim()));
		startActivity(callIntent);
	}
	
	public void makeEmail(View v) {
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
