package com.akshaykalyan.contactmanager;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ContactListActivity extends Activity {

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
		View header = View.inflate(this, R.layout.sort_options_header, null);
		mDrawerList.addHeaderView(header, null, false);
		
		// populate sort options list
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mSortOptions));
		
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
        selectItem(1);
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
	
	public static class ListFragment extends Fragment {
		public static final String ARG_SORT_TYPE_NUMBER = "sort_number";
		
		public ListFragment() {
			// required default constructor
		}
		
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.my_fragment, container, false);
            int i = getArguments().getInt(ARG_SORT_TYPE_NUMBER);
            
            
            // changing the fragment
            String sortType = getResources().getStringArray(R.array.sort_options_array)[i];
            TextView tv = (TextView) rootView.findViewById(R.id.testingTextView);
            tv.setText(sortType);
            
            return rootView;
		
		}
		
	}
	
	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		// Create a new fragment and specify the sort order to show based on position
		Fragment fragment = new ListFragment();
		Bundle args = new Bundle();
		args.putInt(ListFragment.ARG_SORT_TYPE_NUMBER, position-1);
		fragment.setArguments(args);
		
		// Insert the fragment by replacing any existing fragment
		
		FragmentManager fragmentManager = getFragmentManager();
	    fragmentManager.beginTransaction()
	                   .replace(R.id.content_frame, fragment)
	                   .commit();
		
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
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
}