package com.akshaykalyan.contactmanager;

import java.util.ArrayList;
import java.util.List;

import com.akshaykalyan.contact.*;
import com.akshaykalyan.contactmanager.ContactListActivity.ContactListFragment;

import android.content.Context;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<Contact> {
	private final LayoutInflater fInflater;
	
	private Filter fFilter;
	
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
		// -----------------------------------------------------------------------------------------add contact photo ImageView set here
		
		return view;
	}
	
	
	
//	// for custom filtering
//	@Override
//	public Filter getFilter() {
//		if (fFilter == null) {
//			fFilter = new ContactNameFilter();
//		}
//		return fFilter;
//	}
//
//	
//	private class ContactNameFilter extends Filter {
//		
//		@Override
//		protected FilterResults performFiltering(CharSequence constraint){
//			FilterResults results = new FilterResults();
//			String prefix =  constraint.toString().toLowerCase();
//			
//			if (prefix == null || prefix.length() == 0) {
//				ArrayList<Contact> list = new ArrayList<Contact>();
//				results.values = list;
//				results.count = list.size();
//			} else {
//				final ArrayList<Contact> list = new ArrayList<Contact>();
//			}
//		}
		
		
		
		
		
		
		
		
//	}
	
	
	
	
	
	
}
