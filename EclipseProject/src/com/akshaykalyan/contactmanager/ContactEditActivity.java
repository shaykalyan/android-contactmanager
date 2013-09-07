package com.akshaykalyan.contactmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;



public class ContactEditActivity extends Activity {
	

	private static final int IMAGE_REQUEST_CODE = 0;

	private static final int REQUEST_CODE_INTENT_CAMERA = 0;

	private static final int REQUEST_CODE_INTENT_GALLERY = 1;

	private Button acceptEditButton, discardEditButton;

	private Uri outputFileUri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_edit);
		// Show the Up button in the action bar.
		setupActionBar();
		

		
		acceptEditButton = (Button)findViewById(R.id.button_editactivity_acceptedit);
		discardEditButton = (Button)findViewById(R.id.button_editactivity_discardedit);
		
		discardEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	LayoutInflater inflater = getLayoutInflater();
            	View view = inflater.inflate(R.layout.toast_edits_discarded, (ViewGroup)findViewById(R.id.toast_edits_discarded));
                Toast toast = new Toast(getApplicationContext());
                toast.setView(view);
                toast.show();
            	
            	finish();
            }
		});
		
		acceptEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Coming soon... !", Toast.LENGTH_SHORT).show();
            }
		});
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
		getMenuInflater().inflate(R.menu.contact_edit, menu);
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

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}
	
	public static class DatePickerFragment extends DialogFragment
										implements DatePickerDialog.OnDateSetListener {
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Using current date as default
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			
			
			
			// create new instance of DatePickerDialog and return
			return new DatePickerDialog(getActivity(), this, year, month, day);
			
		}
		
		public void onDateSet(DatePicker view, int year, int month, int day) {
			TextView datePickerTextView = (TextView)getActivity().findViewById(R.id.textview_contactedit_birthday);
			datePickerTextView.setText(new StringBuilder()
									.append(day).append("-")
						            // Month is 0 based so add 1
						            .append(month + 1).append("-")
						            
						            .append(year).append(" "));
			
			
					
		}
	}
	
	public void showImageSourceDialog(View v) {
		DialogFragment selectImageSourceDialogFragment = new ImageSelectDialogFragment();
		selectImageSourceDialogFragment.show(getFragmentManager(), "image_source");
	}

	public static class ImageSelectDialogFragment extends DialogFragment {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState){
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle(R.string.dialog_selectimage_title);
			builder.setItems(R.array.image_sources, new DialogInterface.OnClickListener() {
				
				
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case 0:
						Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						getActivity().startActivityForResult(takePicture, REQUEST_CODE_INTENT_GALLERY);
						break;
					case 1:
						Intent pickPhoto = new Intent(Intent.ACTION_PICK,
						           android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						getActivity().startActivityForResult(pickPhoto , REQUEST_CODE_INTENT_CAMERA);
						break;
					default:
						break;
					}
				}
			});
			
			return builder.create();
		}
	}
		
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 
		ImageView contactPhotoImageView = (ImageView)findViewById(R.id.image_contactedit_contact_image);

		switch(requestCode) {
		case 0:
		    if(resultCode == RESULT_OK){  
		        Uri selectedImage = imageReturnedIntent.getData();
		        contactPhotoImageView.setImageURI(selectedImage);
		    }
		    break; 
		case 1:
		    if(resultCode == RESULT_OK){  
		        Uri selectedImage = imageReturnedIntent.getData();
		        contactPhotoImageView.setImageURI(selectedImage);
		    }
		    break;
		}
	}
	
	
	
	
	
}
