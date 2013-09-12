package com.akshaykalyan.contactmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class ContactListActivity extends FragmentActivity {

	private String[] mSortOptions;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	
	private ActionBarDrawerToggle mDrawerToggle;
	

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
		ContactListFragment list = new ContactListFragment();
		fm.beginTransaction().add(R.id.content_frame, list).commit();
 		
 		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_list, menu);
		return true;
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {		
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			selectItem(position);
		}
	}
	
//	public static class ListFragment extends Fragment {
//		public static final String ARG_SORT_TYPE_NUMBER = "sort_number";
//		
//		public ListFragment() {
//			// required default constructor
//		}
//		
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.my_fragment, container, false);
//            int i = getArguments().getInt(ARG_SORT_TYPE_NUMBER);
//            
//            
//            // changing the fragment
//            String sortType = getResources().getStringArray(R.array.sort_options_array)[i];
//            TextView tv = (TextView) rootView.findViewById(R.id.testingTextView);
//            tv.setText(sortType);
//            
//     		// BUTTON --------------
//     		Button editButton = (Button)rootView.findViewById(R.id.my_fragment_editbutton);
//            editButton.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), ContactEditActivity.class);
//                	startActivity(intent);
//
//                }
//    		});
//            
//         // BUTTON --------------
//     		Button contactInfoButton = (Button)rootView.findViewById(R.id.my_fragment_contactinfobutton);
//     		contactInfoButton.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), ContactInformationActivity.class);
//                	startActivity(intent);
//
//                }
//    		});
//            
//            return rootView;
//		
//		}
//		
//	}
	
	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
//		// Create a new fragment and specify the sort order to show based on position
//		Fragment fragment = new ListFragment();
//		Bundle args = new Bundle();
//		args.putInt(ListFragment.ARG_SORT_TYPE_NUMBER, position-1);
//		fragment.setArguments(args);
//		
//		// Insert the fragment by replacing any existing fragment
//		
//		FragmentManager fragmentManager = getFragmentManager();
//	    fragmentManager.beginTransaction()
//	                   .replace(R.id.content_frame, fragment)
//	                   .commit();
		
//		FragmentManager fragmentManager = getFragmentManager();
//		ContactListFragment listFragment = new ContactListFragment();
//		fragmentManager.beginTransaction().add(R.id.content_frame, listFragment);
		
		
		
		
		
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
        	DialogFragment aboutDialogFragment = new AboutDialog();
        	aboutDialogFragment.show(getFragmentManager(), "About");
        	break;
        case R.id.action_add_contact:
        	Toast.makeText(getApplicationContext(), "Add Contact -- Coming Soon!", Toast.LENGTH_SHORT).show();
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
    
    /** on click of first list item */
    public void goToContactInfo(View v) {
    	Intent intent = new Intent(v.getContext(), ContactInformationActivity.class);
    	startActivity(intent);
    }
    
    
    public static class ContactListFragment extends android.support.v4.app.ListFragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<Contact>> {
		
    	CustomArrayAdapter fAdapter;
    	
		// testing stuff 
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
    		View superView = super.onCreateView(inflater, container, savedInstanceState);
//            View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
            ViewGroup parent = (ViewGroup)inflater.inflate(R.layout.fragment_contact_list, container, false);
            parent.addView(superView,0);
            return parent;
    	}
        // /test
    	
    	
    	@Override
    	public void onActivityCreated(Bundle savedInstanceState) {
    		super.onActivityCreated(savedInstanceState);
    		
    		// ##### self print check
    		System.out.println("ContactListFragment.onActivityCreated");
    		
    		//Initially there is no data
    		setEmptyText("No Data Here");
    		
    		//Create an empty adapter we will use to display the loaded data
    		fAdapter = new CustomArrayAdapter(getActivity());
    		setListAdapter(fAdapter);
    		
    		//remove divider line
    		getListView().setDivider(null);
    		getListView().setDividerHeight(0);
    		
    		//start out with a progress indicator
    		setListShown(false);
    		
    		getLoaderManager().initLoader(0, null, this);
    	}
    	
    	@Override
    	public void onListItemClick(ListView l, View v, int position, long id) {
    		// insert desired behaviour here.
    		Log.i("ContactListFragment", "Item clicked: " + id);
    		
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
           entries.add(new Contact("Akshay", "Kalyan", "", "", "", "", "", "", "", "", null));
           entries.add(new Contact("Bob", "Quinn", "", "", "", "", "", "", "", "", null));
           entries.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "", null));
           entries.add(new Contact("Steve", "Ivy", "", "", "", "", "", "", "", "", null));
           entries.add(new Contact("Steve", "Ivy", "", "", "", "", "", "", "", "", null));
           entries.add(new Contact("Steve", "Ivy", "", "", "", "", "", "", "", "", null));
           entries.add(new Contact("Steve", "Ivy", "", "", "", "", "", "", "", "", null));
           entries.add(new Contact("Steve", "Ivy", "", "", "", "", "", "", "", "", null));
           entries.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "", null));
           entries.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "", null));
           entries.add(new Contact("Ewan", "Weber", "", "", "", "", "", "", "", "", null));

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

