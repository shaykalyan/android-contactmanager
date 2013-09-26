package com.akshaykalyan.contactmanager;

import java.util.Calendar;

import com.akshaykalyan.contact.Contact;
import com.akshaykalyan.contact.ContactEmail;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Activity class representing the edit screen of a contact. This activity is used for two cases and reacts
 * differently depending on the intent and the parent class (which this activity is requested from)
 * 
 * If parent activity is ContactListActivity, it is treated as a new contact situation
 * and the views are not populated
 * 
 * If parent activity is ContactInformationActivity, it is treated as a contact edit situation 
 * and the views are populated with the contact's respective information
 * 
 * @author Akshay Pravin Kalyan | akal881 | 57886866
 */
public class ContactEditActivity extends Activity {
	
	private Class fParentClass;
	private static final int REQUEST_CODE_INTENT_CAMERA = 0;
	private static final int REQUEST_CODE_INTENT_GALLERY = 1;
	private Button acceptEditButton, discardEditButton;
	
	private EditText etFirstName, etLastName, etMobile, etHome, etWork, etEmail, etAddressLine1,
						etAddressLine2, etAddressLine3, etAddressLine4;
	private TextView tvBirthday;
	
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_edit);
		// Show the Up button in the action bar.
		setupActionBar();
		
		etFirstName = (EditText)findViewById(R.id.edittext_contactedit_firstname);
		etLastName = (EditText)findViewById(R.id.edittext_contactedit_lastname);
		etMobile = (EditText)findViewById(R.id.edittext_contactedit_phone_mobile);
		etHome = (EditText)findViewById(R.id.edittext_contactedit_phone_home);
		etWork = (EditText)findViewById(R.id.edittext_contactedit_phone_work);
		etEmail = (EditText)findViewById(R.id.edittext_contactedit_email);
		etAddressLine1 = (EditText)findViewById(R.id.edittext_contactedit_addressline1);
		etAddressLine2 = (EditText)findViewById(R.id.edittext_contactedit_addressline2);
		etAddressLine3 = (EditText)findViewById(R.id.edittext_contactedit_addressline3);
		etAddressLine4 = (EditText)findViewById(R.id.edittext_contactedit_addressline4);
		tvBirthday = (TextView)findViewById(R.id.textview_contactedit_birthday); 

		// save cancel button and listeners
		acceptEditButton = (Button)findViewById(R.id.button_editactivity_acceptedit);
		discardEditButton = (Button)findViewById(R.id.button_editactivity_discardedit);
		
		discardEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	showEditsDiscardToast();
            	finish();
            }
		});
		acceptEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// only if email is validated, further actions can occur
            	if (ContactEmail.Validation.isValidEmailAddress(etEmail)) {
            		
            		if (fParentClass == ContactInformationActivity.class) {
            			//TODO Logic for updating contact info
            			finish();
            		} else { //fParentClass is ContactListActivity
            			//TODO Logic for adding new contact
            			finish();
            		}
            		showContactSavedToast();
            	} else {
            		// TODO custom toast?
            		Toast.makeText(v.getContext(), "Invalid Email!", Toast.LENGTH_SHORT).show();
            	}  
            }
		});
		
		// email edit text field listener
		etEmail.addTextChangedListener(new TextWatcher() {	
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// clear error as soon as an edit is made to text view
				etEmail.setError(null);
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {}
			@Override
			public void afterTextChanged(Editable arg0) {}
		});
		
		// TODO: Populate all fields + Photo field
		Intent intent = getIntent();
		fParentClass = (Class) intent.getExtras().get("PARENT_ACTIVITY");

		
		
		if (fParentClass == ContactListActivity.class) { // new contact
			getActionBar().setTitle("New Contact");
			// Do not populate views
		} else { // fParent is ContactInformationActivity
			// Populate views
			Contact contact = (Contact) intent.getExtras().get("CONTACT_OBJECT");
			//TODO populate views
			etFirstName.setText(contact.getfName().getFirstName());
		}
	}

	@Override
	/**
	 * Override back press to show toast
	 */
	public void onBackPressed() {
		showEditsDiscardToast();
    	finish();
		super.onBackPressed();
	}
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * @see android.app.Activity#onCreateOptionsMenu(Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_edit, menu);
		return true;
	}

	/**
	 * @see android.app.Activity#onOptionsItemSelected(MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (fParentClass == ContactInformationActivity.class){
				finish();
			} else { // ListActivity
				finish();
			}
        	showEditsDiscardToast();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * onClick method which launches a dialog allowing the user to pick a date for the 
	 * Contact's birthday text field.
	 */
	public void onClick_showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}
	
	/**
	 * Inner Class responsible for generating a Date Picker dialog when a request has 
	 * been placed by the user. 
	 * 
	 * If the birthday field was already set, it will be parsed and the picker
	 * will be set to that date. Otherwise the current date will be set as default
	 */
	public static class DatePickerFragment extends DialogFragment
										implements DatePickerDialog.OnDateSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			int year, month, day;
			
			TextView dateTextView = (TextView)getActivity().findViewById(R.id.textview_contactedit_birthday);
			String[] birthdayText = dateTextView.getText().toString().split("-");
			if (birthdayText.length > 1) {
				day = Integer.parseInt(birthdayText[0].trim());
				month = Integer.parseInt(birthdayText[1].trim()) - 1; // month starts from 0
				year = Integer.parseInt(birthdayText[2].trim());
			} else {
				final Calendar c = Calendar.getInstance();
				day = c.get(Calendar.DAY_OF_MONTH);
				month = c.get(Calendar.MONTH);
				year = c.get(Calendar.YEAR);	
			}
			
			// create new instance of DatePickerDialog and return
			Dialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
			return datePickerDialog;
		}
		
		/**
		 * Formats and sets the birthday text to the date selected by user 
		 */
		public void onDateSet(DatePicker view, int year, int month, int day) {
			TextView datePickerTextView = (TextView)getActivity().findViewById(R.id.textview_contactedit_birthday);
			datePickerTextView.setText(new StringBuilder()
									.append(day).append("-")
						            // Month is 0 based so add 1
						            .append(month + 1).append("-")
						            
						            .append(year).append(" "));			
		}
	}
	
	/**
	 * onClick method which resets the birthday text view field.
	 */
	public void onClick_deleteBirthdayText(View v) {
		tvBirthday.setText("");
	}
	
	/**
	 * onClick method to enable the user to select a replacement contact photo.
	 * This method launches a dialog allowing the user to pick a source for the image from either
	 * 		Camera
	 * 		Gallery
	 * and fires an intent depending on the decision made.
	 */
	public void onClick_showImageSourceDialog(View v) {
		DialogFragment selectImageSourceDialogFragment = new ImageSelectDialogFragment();
		selectImageSourceDialogFragment.show(getFragmentManager(), "image_source");
	}

	/**
	 * Inner Class responsible for generating an image source Picker dialog when a request has 
	 * been placed by the user. 
	 * 
	 * The user is presented with a list of sources to select a contact image from.
	 * The options are:
	 * 		Camera
	 * 		Gallery
	 * 
	 * The option select fires its respective intent and is then presented with the chosen
	 * activity.
	 */
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
	
	/**
	 * Reacts to intents fired. In this case, reacts to request codes suggesting and sets
	 * the contact's ImageView to the selected image.
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 
		ImageView contactPhotoImageView = (ImageView)findViewById(R.id.image_contactedit_contact_image);

		switch(requestCode) {
		case REQUEST_CODE_INTENT_CAMERA:
		    if(resultCode == RESULT_OK){  
		        Uri selectedImage = imageReturnedIntent.getData();
		        contactPhotoImageView.setImageURI(selectedImage);
		    }
		    break; 
		case REQUEST_CODE_INTENT_GALLERY:
		    if(resultCode == RESULT_OK){  
		        Uri selectedImage = imageReturnedIntent.getData();
		        contactPhotoImageView.setImageURI(selectedImage);
		    }
		    break;
		}
	}


	/**
	 * Inflates a custom toast, Edits Discarded Toast, and shows to the currect Application Context
	 */
	private void showEditsDiscardToast() {
		LayoutInflater inflater = getLayoutInflater();
    	View view = inflater.inflate(R.layout.toast_edits_discarded, (ViewGroup)findViewById(R.id.toast_edits_discarded));
        Toast toast = new Toast(getApplicationContext());
        toast.setView(view);
        toast.show();
	}
	
	/**
	 * Inflates a custom toast, Contact Saved Toast, and shows to the currect Application Context
	 */
	private void showContactSavedToast() {
		LayoutInflater inflater = getLayoutInflater();
    	View view = inflater.inflate(R.layout.toast_contact_saved, (ViewGroup)findViewById(R.id.toast_contact_saved));
        Toast toast = new Toast(getApplicationContext());
        toast.setView(view);
        toast.show();
	}
}
