package com.aunadi;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class GetContactActivity extends ActionBarActivity {

    private ListView listViewContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_contact);

        listViewContacts = (ListView) findViewById(R.id.listViewContacts);
    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Contact> contacts = fetchContacts();
        listViewContacts.setAdapter(new ContactListAdapter(this, contacts));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static final String[] PROJECTION = new String[] {
            ContactsContract.CommonDataKinds.Email.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Email.DATA
    };

    public List<Contact> fetchContacts() {
        List<Contact> contacts = new ArrayList<Contact>();
//        String phoneNumber = null;
//        String email = null;
//
//        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
//        String _ID = ContactsContract.Contacts._ID;
//        String DISPLAY_FIRST_NAME = ContactsContract.Contacts.DISPLAY_NAME;
//        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
//
//        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
//        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
//
//        Uri EmailCONTENT_URI =  ContactsContract.CommonDataKinds.Email.CONTENT_URI;
//        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
//        String DATA = ContactsContract.CommonDataKinds.Email.DATA;
//
//        ContentResolver contentResolver = getContentResolver();
//
//        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
//
//        // Loop for every contact in the phone
//        if (cursor.getCount() > 0) {
//
//            while (cursor.moveToNext()) {
//
//                String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
//                String name = cursor.getString(cursor.getColumnIndex( DISPLAY_FIRST_NAME ));
//
//                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));
//
//                if (hasPhoneNumber > 0) {
//
//                    Contact contact = new Contact(name, false);
//
//                    // Query and loop for every phone number of the contact
//                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);
//
//                    while (phoneCursor.moveToNext()) {
//                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
//                        //output.append("\n Phone number:" + phoneNumber);
//
//                    }
//
//                    phoneCursor.close();
//
//                    // Query and loop for every email of the contact
//                    Cursor emailCursor = contentResolver.query(EmailCONTENT_URI,	null, EmailCONTACT_ID+ " = ?", new String[] { contact_id }, null);
//
//                    while (emailCursor.moveToNext()) {
//
//                        email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
//
//                        //output.append("\nEmail:" + email);
//
//                    }
//
//                    emailCursor.close();
//                    contacts.add(contact);
//                }
//
//
//            }
//
//        }

//        ContentResolver cr = getContentResolver();
//        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, PROJECTION, null, null, null);
//        if (cursor != null) {
//            try {
//                final int contactIdIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID);
//                final int displayNameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
//                final int emailIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
//                long contactId;
//                String displayName, address;
//                while (cursor.moveToNext()) {
//                    contactId = cursor.getLong(contactIdIndex);
//                    displayName = cursor.getString(displayNameIndex);
//                    address = cursor.getString(emailIndex);
//
//                    contacts.add(new Contact(displayName, false));
//                }
//            } finally {
//                cursor.close();
//            }
//        }

        ContentResolver cResolver = getContentResolver();
        ContentProviderClient mCProviderClient = cResolver.acquireContentProviderClient(ContactsContract.Contacts.CONTENT_URI);

        try
        {
            Cursor mCursor = mCProviderClient.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (mCursor != null && mCursor.getCount() > 0)
            {
                mCursor.moveToFirst();
                while (!mCursor.isLast())
                {
                    String displayName = mCursor.getString(mCursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    contacts.add(new Contact(displayName, false));
                    mCursor.moveToNext();
                }
                if (mCursor.isLast())
                {
                    String displayName = mCursor.getString(mCursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    contacts.add(new Contact(displayName, false));
                }
            }

            mCursor.close();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return contacts;
    }


}
