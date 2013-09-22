package com.akshaykalyan.contactmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.akshaykalyan.contact.*;
import com.akshaykalyan.contact.Contact.SortBy;

import android.os.Bundle;
import android.provider.Contacts;
import android.R.anim;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.app.FragmentActivity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.text.AndroidCharacter;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Activity class responsible in managing a list of Contact objects and populating
 * a ListView via a adapter.
 * 
 * This is the launcher activity.
 *
 * This activity is based on the navigation drawer layout scheme.
 * 
 * @author Akshay Pravin Kalyan | akal881 | 57886866
 */
public class ContactListActivity extends FragmentActivity {

	private String[] mSortOptions;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	
	private static SortBy currentSortBy = SortBy.FirstName;
	
	private ActionBarDrawerToggle mDrawerToggle;
	private ContactListFragment mContactListFragment = new ContactListFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_list);
		
		mSortOptions = getResources().getStringArray(R.array.sort_options_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// create sort options header
		View header = View.inflate(this, R.layout.listview_header_sort_options, null);
		mDrawerList.addHeaderView(header, null, false);
		
		// sets header font to roboto
		TextView tView = (TextView)findViewById(R.id.sort_options_header);
		Typeface tf = FontRobotoRegular.getTypeface(this);
    	tView.setTypeface(tf);    
		
		// populate sort options list
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.list_item_drawer, mSortOptions));
		
		// set sort options list listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
				
		mDrawerToggle = new ActionBarDrawerToggle(this,  mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
			
			/** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
		};

		// enable launcher icon as button to toggle drawer
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		// enable up button functionality
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
 
        FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().add(R.id.content_frame, mContactListFragment).commit();
 		

		// Highlight the selected item and close the drawer
		// highlight the current sort by type
		int itemCheckedPosition = 1;
		switch (currentSortBy) {
		case FirstName:
			itemCheckedPosition = 1;
			break;
		case LastName:
			itemCheckedPosition = 2;
			break;
		case PhoneNumberMobile:
			itemCheckedPosition = 3;
			break;
		case PhoneNumberHome: 
			itemCheckedPosition = 4;
			break;
		case PhoneNumberWork:
			itemCheckedPosition = 5;
			break;
		}
		// NOTE: actual sorting of list is not done now, as list is not yet populated
		mDrawerList.setItemChecked(itemCheckedPosition, true);
	}
	
	@Override
	/**
	 * If sort options drawer is open, it is closed. Otherwise application exits.
	 */
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
    		mDrawerLayout.closeDrawer(Gravity.LEFT);
    	} else {
    		finish();
    		super.onBackPressed();
    	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_list, menu);
		
		// search menu action layout and edit text views
		final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final EditText searchEditText = ( EditText ) searchMenuItem.getActionView().findViewById(R.id.list_edittext_search);
 
        // EditText listener for filtering of contacts based on search. List is filtered at every key input
        searchEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
					mContactListFragment.filterContacts(s);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			@Override
			public void afterTextChanged(Editable s) {}
		});
        
        // EditText listener for when Search is pressed on keyboard which hides the keyboard.
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					keyboard.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0); 
					searchEditText.clearFocus();
				}
				return false;
			}
		});
        
        // search menu action layout listener to
        //		when expand view
        //				set focus on edit text and enable keyboard
        //		when collapse view
        //				clear edit text
        //				hide keyboard
		searchMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
			
			InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			
			public boolean onMenuItemActionExpand(MenuItem item) {
				// only if drawer is closed trigger keyboard open
				if (!(mDrawerLayout.isDrawerOpen(Gravity.LEFT))) {
					searchEditText.requestFocus();
					keyboard.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
				}
			    return true;
			}
			public boolean onMenuItemActionCollapse(MenuItem item) {
				// clear text
				searchEditText.setText("");
				// hide keyboard on collapse
				keyboard.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0); 
				return true;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {		
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			setListSort(position);
		}
	}
	
	/**
	 * Sort items by the options in drawer
	 */
	private void setListSort(int position) {		
		
		switch (position) {
		case 1: 
			mContactListFragment.sortContactsList(Contact.SortBy.FirstName.fComparator);
			currentSortBy = SortBy.FirstName;
			break;
		case 2:
			mContactListFragment.sortContactsList(Contact.SortBy.LastName.fComparator);
			currentSortBy = SortBy.LastName;
			break;
		case 3:
			mContactListFragment.sortContactsList(Contact.SortBy.PhoneNumberMobile.fComparator);
			currentSortBy = SortBy.PhoneNumberMobile;
			break;
		case 4:
			mContactListFragment.sortContactsList(Contact.SortBy.PhoneNumberHome.fComparator);
			currentSortBy = SortBy.PhoneNumberHome;
			break;
		case 5:
			mContactListFragment.sortContactsList(Contact.SortBy.PhoneNumberWork.fComparator);
			currentSortBy = SortBy.PhoneNumberWork;
			break;
		}

	    // Highlight the selected item and close the drawer
	    mDrawerList.setItemChecked(position, true);
	    mDrawerLayout.closeDrawer(mDrawerList);  
	    
	}	
	
	/* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        
        // to hide the menus items etc
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
    	super.onPostCreate(savedInstanceState);
    	// Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }

        switch (item.getItemId()) {
        case R.id.action_about:
        	// check if drawer is open, if so close first
        	if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
        		mDrawerLayout.closeDrawer(Gravity.LEFT);
        	}
        	DialogFragment aboutDialogFragment = new AboutDialog();
        	aboutDialogFragment.show(getFragmentManager(), "About");
        	break;
        case R.id.action_add_contact:
        	Intent intent = new Intent(getApplicationContext(), ContactEditActivity.class);
        	intent.putExtra("PARENT_ACTIVITY", ContactListActivity.class);
        	startActivity(intent);
        	break;
        case R.id.action_sortoptions:
        	if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
        		mDrawerLayout.closeDrawer(Gravity.LEFT);
        	} else {
        		mDrawerLayout.openDrawer(Gravity.LEFT);
        	}
        	break;
       	default:
       		break;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    /**
	 * Inner Class responsible for generating a custom About dialog when a request has 
	 * been placed by the user. 
	 * 
	 * About dialog contains the Application name, version and Author.
	 */
    public static class AboutDialog extends DialogFragment {
    	@Override
    	public Dialog onCreateDialog(Bundle savedInstanceState) {
    		
    		// Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();
            
            builder.setView(inflater.inflate(R.layout.diaglog_about, null))
                   .setPositiveButton(R.string.action_okay, new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           dismiss();
                       }
                   });
            // Create the AlertDialog object and return it
            return builder.create();
    		
    	}
    }    
    
//    public static class ContactListFragment extends android.support.v4.app.ListFragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<Contact>> {
    public static class ContactListFragment extends android.support.v4.app.ListFragment {
		List<Contact> contactsList = new ArrayList<Contact>(); 
    	CustomArrayAdapter fAdapter;
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
    		View superView = super.onCreateView(inflater, container, savedInstanceState);
            ViewGroup parent = (ViewGroup)inflater.inflate(R.layout.fragment_contact_list, container, false);
            parent.addView(superView,0);
            
            
            return parent;
    	}

    	public void sortContactsList(Comparator<Contact> cmp) {
    		fAdapter.sort(cmp);
    	}
    	
    	public void filterContacts(CharSequence cs) {
    		fAdapter.getFilter().filter(cs.toString().toLowerCase());
    	}
    	
    	@Override
    	public void onActivityCreated(Bundle savedInstanceState) {
    		super.onActivityCreated(savedInstanceState);
    		
    		//remove divider line between list items
    		getListView().setDivider(null);
    		getListView().setDividerHeight(0);
    		// Set no data text
    		setEmptyText("No Contacts Found");
    		
    		// TODO remove self print check
    		System.out.println("ContactListFragment.onActivityCreated");
    		
    		//Create an adapter with list
    		fAdapter = new CustomArrayAdapter(getActivity(), contactsList);
    		setListAdapter(fAdapter);
    		
    		// TODO remove hard-coded Contacts!
    		contactsList.add(new Contact("Tony's", "Tyres", 
    				"0211066077", "096008333", "094432731", 
    				"info@targetroadtyres.co.nz",
    				"25-12-1993", 
    				"145 Target Road", "Wairau Valley", "North Shore", "Auckland",
         		   null));   
    		contactsList.add(new Contact("Akshay", "Kalyan", "0277276866", "", "", "akal881@aucklanduni.ac.nz", "", "", "", "","" , null));
    		contactsList.add(new Contact("Bob", "Quinn", "", "", "", "", "", "", "", "","" , null));
    		contactsList.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "","" , null));
    		contactsList.add(new Contact("Matthew", "Chiam", "02102926646", "", "", "mchiam1991@gmail.com", "", "", "", "","" , null));
    		contactsList.add(new Contact("Bert", "Huang", "", "", "", "ihua164@aucklanduni.ac.nz", "", "", "", "","" , null));
            contactsList.add(new Contact("Beeve", "Zivy", "", "", "", "", "", "", "", "","" , null));
            contactsList.add(new Contact("Steve", "Ivy", "", "", "", "", "", "", "", "","" , null));
            contactsList.add(new Contact("Steve", "Ivy", "", "", "", "", "", "", "", "","" , null));
            contactsList.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "","" , null));
            contactsList.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "","" , null));
            contactsList.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "","" , null));  
            
            // TODO find replacement for this quick hack to select first name default
            Collections.sort(contactsList, currentSortBy.fComparator);
            fAdapter.notifyDataSetChanged();
    	}
    	
    	@Override
    	public void onListItemClick(ListView l, View v, int position, long id) {
    		// pass contact to info activity via intent
    		Intent intent = new Intent(v.getContext(), ContactInformationActivity.class);
    		Contact contact = fAdapter.getItem(position);
    		intent.putExtra("CONTACT_OBJECT", contact);
    		startActivity(intent);
    	}
    
    	public void AddItem (View v, Contact c) {
    		fAdapter.add(c);
    		fAdapter.notifyDataSetChanged();
    	}
	}
}

