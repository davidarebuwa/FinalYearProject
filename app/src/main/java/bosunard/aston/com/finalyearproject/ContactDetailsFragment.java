package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import bosunard.aston.com.finalyearproject.models.Contact;

public class ContactDetailsFragment extends Fragment {


    private Contact contact;

    private onContactDetailsFragmentInteractionListener mListener;

    public ContactDetailsFragment() {super();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_details_fragment, container, false);


        Button saveButton = (Button)view.findViewById(R.id.save_button);

        return view;
    }

    public static ContactDetailsFragment newInstance(Contact contact){

        Bundle args = new Bundle();
        args.putSerializable("CONTACT",contact);
        ContactDetailsFragment fragment = new ContactDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  onContactDetailsFragmentInteractionListener){
            mListener = (onContactDetailsFragmentInteractionListener) context;

        }else{

            throw new RuntimeException(context.toString() + "must implement onMoreDetailsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    public void fillContactDetails(Contact contact){

        TextView contactName = (TextView)getView().findViewById(R.id.contact_name_detail);
        contactName.setText(contact.getName());

        TextView contactPhoneNumber = (TextView)getView().findViewById(R.id.contact_number_detail);
        contactPhoneNumber.setText(contact.getContactNumber());

    }

    private interface onContactDetailsFragmentInteractionListener{

        void showContactDetails(Contact contact);

    }
}
