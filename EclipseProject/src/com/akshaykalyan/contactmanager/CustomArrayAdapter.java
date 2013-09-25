package com.akshaykalyan.contactmanager;

import java.util.List;
import com.akshaykalyan.contact.*;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Custom Array Adapter extending ArrayAdapter to manage and communicate a list of
 * Contact objects to/from ListView
 * 
 * @author Akshay Pravin Kalyan | akal881 | 5786866
 */
public class CustomArrayAdapter extends ArrayAdapter<Contact> {
	private final LayoutInflater fInflater;
	
	public CustomArrayAdapter(Context context) {
		super(context, android.R.layout.simple_list_item_2);
		fInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public CustomArrayAdapter(Context context, List<Contact> list) {
		super(context, android.R.layout.simple_list_item_2, list);
		fInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(List<Contact> data) {
		clear();
		if (data != null) {
			for (Contact appEntry : data){
				add(appEntry);
			}
		}
	}
	
	/**
	 * Populate new items in the list with the following method.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		
		if (convertView == null) {
			view = fInflater.inflate(R.layout.list_item_contact_card_single, parent, false);
		} else {
			view = convertView;
		}
		
		Contact itemContact = getItem(position);
		((TextView)view.findViewById(R.id.list_item_contact_card_textview)).setText(itemContact.getfName().toString());
		// TODO get ContactPhoto and fill image view
		// -----------------------------------------------------------------------------------------add contact photo ImageView set here
		
		return view;
	}
}
