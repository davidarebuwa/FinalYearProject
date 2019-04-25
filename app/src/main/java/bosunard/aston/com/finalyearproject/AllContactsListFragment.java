package bosunard.aston.com.finalyearproject;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bosunard.aston.com.finalyearproject.models.Contact;

public class AllContactsListFragment extends Fragment {


    private static final String TAG = "AllContactsListFragment";

   // private String[] contacts = {};

   // private ArrayList<Contact> contactList = new ArrayList<Contact>();

    private onAllContactsListFragmentInteractionListener mListener;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

   // private ArrayAdapter adapter;

    //private ListView  emergencyList;

    private ListView lstNames;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_contacts, container, false);



          lstNames = (ListView)view.findViewById(R.id.all_contact_list);
          //readContacts();
        showContacts();

//         adapter = new ArrayAdapter<String>(getContext(),
//                R.layout.help_list_item, contacts);

        //emergencyList.setAdapter(adapter);

        return view;
    }

//    public void readContacts(){
//
//        Cursor cursor = null;
//        ContentResolver contentResolver = getActivity().getContentResolver();
//
//        String contactID = " ";
//
//        String contactName = " ";
//        Contact contact = new Contact();
//
//        try{
//
//            cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
//
//        }catch (Exception e){
//            Log.e(TAG,"Error retrieving contacts: " + e.getMessage());
//        }
//
//        if(cursor.getCount() > 0) {
//
//
//
//            while (cursor.moveToNext()) {
//
//
//                contactID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//
//
//                contact.name = contactName;
//
//
//                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
//                if (hasPhoneNumber > 0) {
//
//                    Cursor phoneCursor = contentResolver.query(
//                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                            null,
//                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                            new String[]{contactID},
//                            null);
//
//                    while (phoneCursor.moveToNext()) {
//
//                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//
//                        contact.contactNumber = phoneNumber;
//                    }
//                    phoneCursor.close();
//
//                }
//
//
//                contactList.add(contact);
//            }
//
//
//            ArrayAdapter adapter = new ArrayAdapter<Contact>(getContext(), R.layout.custom_contacts_list_item, contactList);
//           // emergencyList.setAdapter(adapter);
//        }
//
//
//
//
//
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                showContacts();
            } else {
                Toast.makeText(getContext(), "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            List<String> contacts = getContactNames();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.help_list_item, contacts);
            lstNames.setAdapter(adapter);
        }
    }

    /**
     * Read the name of all the contacts.
     *
     * @return a list of names.
     */
    private List<String> getContactNames() {
        List<String> contacts = new ArrayList<>();
        // Get the ContentResolver
        ContentResolver cr = getActivity().getContentResolver();
        // Get the Cursor of all the contacts
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        // Move the cursor to first. Also check whether the cursor is empty or not.
        if (cursor.moveToFirst()) {
            // Iterate through the cursor
            do {
                // Get the contacts name
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contacts.add(name);
            } while (cursor.moveToNext());
        }
        // Close cursor
        cursor.close();

        Log.i(TAG,"Contacts: "+ contacts.toString());

        return contacts;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof onAllContactsListFragmentInteractionListener){

            mListener = (onAllContactsListFragmentInteractionListener) context;

        }else{

            throw new RuntimeException(context.toString() + "must implement onEmergencyContactsListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }


    public interface onAllContactsListFragmentInteractionListener{


    }
}
