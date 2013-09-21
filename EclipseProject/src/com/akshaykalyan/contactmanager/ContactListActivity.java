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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
		
		
		

		
		
		// enable listening
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
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
		
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.custom_view_search_edit_text, null);
        
        getActionBar().setCustomView(customView);
       
        
        
        // set to default fragment -- contact list sorted by first name
// 		selectItem(1);
        
//        FragmentManager fm = getSupportFragmentManager();
//  		ContactListFragment list = new ContactListFragment();
//  		fm.beginTransaction().add(R.id.content_frame, list).commit();
//   		
//        FragmentManager fm = getSupportFragmentManager();
//		ContactListFragment list = new ContactListFragment();
//		fm.beginTransaction().replace(R.id.content_frame, list).commit();
// 		

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
		mDrawerList.setItemChecked(itemCheckedPosition, true);

 		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_list, menu);
		
		// search menu item
		final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        // Get the edit text from the action view
        final EditText searchEditText = ( EditText ) searchMenuItem.getActionView().findViewById(R.id.list_edittext_search);
 
        // search text listener
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
        
        // search menu item expand listener. 
        // 			Clears text when collapsed
        //			Ensures soft keyboard pops up
		searchMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
			
			public boolean onMenuItemActionExpand(MenuItem item) {
				// only if drawer is closed
				if (!(mDrawerLayout.isDrawerOpen(Gravity.LEFT))) {
					searchEditText.requestFocus();
	                InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	                if(keyboard != null){
	                	keyboard.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
	                }
				}
			    return true;
			}
			public boolean onMenuItemActionCollapse(MenuItem item) {
				searchEditText.setText("");
				return true;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {		
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			selectItem(position);
		}
	}
	
	/**
	 * Sort items by the options in drawer
	 */
	private void selectItem(int position) {		
		
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
//        menu.findItem(R.id.action_add_contact).setVisible(!drawerOpen);
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
    
    public static class ContactListFragment extends android.support.v4.app.ListFragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<Contact>> {
		List<Contact> contactsList = new ArrayList<Contact>(); 
    	CustomArrayAdapter fAdapter;
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
    		View superView = super.onCreateView(inflater, container, savedInstanceState);
//            View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
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
    		
    		// ##### self print check
    		System.out.println("ContactListFragment.onActivityCreated");
    		
    		//Initially there is no data
    		setEmptyText("No Contacts Found");
    		
    		//Create an empty adapter we will use to display the loaded data
    		fAdapter = new CustomArrayAdapter(getActivity(), contactsList);
    		setListAdapter(fAdapter);
    		
    		//remove divider line
    		getListView().setDivider(null);
    		getListView().setDividerHeight(0);
    		
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
            
            
            
         // quick hack to select firstname default
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
    		contactsList.add(c);
    		fAdapter.notifyDataSetChanged();
    	}
    	
    	@Override
    	public Loader<List<Contact>> onCreateLoader(int arg0, Bundle arg1) {
    		System.out.println("ContactListFragment.onCreateLoader");
            return new ContactListLoader(getActivity());
    	}

    	@Override
    	public void onLoadFinished(Loader<List<Contact>> arg0, List<Contact> data) {
    		fAdapter.setData(data);
    		System.out.println("ContactListFragment.onLoadFinished");
            //the list should now be shown.
            if (isResumed()) {
                setListShown(true);
            } else {
                setListShownNoAnimation(true);
            }
    	}
    	
    	@Override
    	public void onLoaderReset(Loader<List<Contact>> arg0) {
    		fAdapter.setData(null);
    	}
	}
    
    public static class ContactListLoader extends android.support.v4.content.AsyncTaskLoader<List<Contact>> {
    	List<Contact> fContactsList;
    	
    	public ContactListLoader(Context context) {
    		super(context);
    	}
    	
    	@Override
    	public List<Contact> loadInBackground() {
    		System.out.println("ContactListLoader.loadInBackground");
            
            // You should perform the heavy task of getting data from 
            // Internet or database or other source 
            // Here, we are generating some Sample data

           // Create corresponding array of entries and load with data.
           List<Contact> entries = new ArrayList<Contact>(15);
           entries.add(new Contact("Tony's", "Tyres", 
   				"0211066077", "096008333", "094432731", 
   				"info@targetroadtyres.co.nz",
   				"25-12-1993",
   				"145 Target Road", "Wairau Valley", "North Shore", "Auckland",
        		null));
        		   
           entries.add(new Contact("Akshay", "Kalyan", "0277276866", "", "", "akal881@aucklanduni.ac.nz", "", "", "", "","" , null));
           entries.add(new Contact("Bob", "Quinn", "", "", "", "", "", "", "", "","" , null));
           entries.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "","" , null));
           entries.add(new Contact("Matthew", "Chiam", "02102926646", "", "", "mchiam1991@gmail.com", "", "", "", "","" , null));
           entries.add(new Contact("Bert", "Huang", "", "", "", "ihua164@aucklanduni.ac.nz", "", "", "", "","" , null));
           entries.add(new Contact("Steve", "Ivy", "", "", "", "", "", "", "", "","" , null));
           entries.add(new Contact("Steve", "Ivy", "", "", "", "", "", "", "", "","" , null));
           entries.add(new Contact("Steve", "Ivy", "", "", "", "", "", "", "", "","" , null));
           entries.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "","" , null));
           entries.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "","" , null));
           entries.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "","" , null));

           return entries;
    	}
    	
    	/**
         * Called when there is new data to deliver to the client.  The
         * super class will take care of delivering it; the implementation
         * here just adds a little more logic.
         */
    	@Override
    	public void deliverResult(List<Contact> listOfData) {
    		if (isReset()) {
    			// An async query came in while the loader is stopped.  We
                // don't need the result.
                if (listOfData != null) {
                    onReleaseResources(listOfData);
                }
    		}
    		List<Contact> oldApps = listOfData;
    		fContactsList = listOfData;
    		
    		if (isStarted()) {
    			// If the Loader is currently started, we can immediately
                // deliver its results.
                super.deliverResult(listOfData);
    		}
    		
    		// At this point we can release the resources associated with
            // 'oldApps' if needed; now that the new result is delivered we
            // know that it is no longer in use.
            if (oldApps != null) {
                onReleaseResources(oldApps);
            }    		
    	}
    	
    	/**
         * Handles a request to start the Loader.
         */
        @Override protected void onStartLoading() {
            if (fContactsList != null) {
                // If we currently have a result available, deliver it
                // immediately.
                deliverResult(fContactsList);
            }
            
            if (takeContentChanged() || fContactsList == null) {
                // If the data has changed since the last time it was loaded
                // or is not currently available, start a load.
                forceLoad();
            }
        }
        
        /**
         * Handles a request to stop the Loader.
         */
        @Override protected void onStopLoading() {
            // Attempt to cancel the current load task if possible.
            cancelLoad();
        }
        
        /**
         * Handles a request to cancel a load.
         */
        @Override 
        public void onCanceled(List<Contact> apps) {
            super.onCanceled(apps);
 
            // At this point we can release the resources associated with 'apps'
            // if needed.
            onReleaseResources(apps);
        }
        
        /**
         * Handles a request to completely reset the Loader.
         */
        @Override 
        protected void onReset() {
            super.onReset();
 
            // Ensure the loader is stopped
            onStopLoading();
 
            // At this point we can release the resources associated with 'apps'
            // if needed.
            if (fContactsList != null) {
                onReleaseResources(fContactsList);
                fContactsList = null;
            }
        }
        
        /**
         * Helper function to take care of releasing resources associated
         * with an actively loaded data set.
         */
        protected void onReleaseResources(List<Contact> apps) {}
        
        
        
        
    }
}

