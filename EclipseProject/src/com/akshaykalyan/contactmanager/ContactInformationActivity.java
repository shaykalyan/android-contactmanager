package com.akshaykalyan.contactmanager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
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
		}
		return super.onOptionsItemSelected(item);
	}

}
