package bosunard.aston.com.finalyearproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import bosunard.aston.com.finalyearproject.models.ComplaintFormFragment;

public class PoliceHelpContactPreferenceFragment  extends Fragment {

    private OnPoliceHelpContactPreferenceFragmentInteractionListener mListener;

    private String[] contactOptions = { "Phone call", "Complaint form"};



    public PoliceHelpContactPreferenceFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_contact_preference, container, false);

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                R.layout.help_list_item, contactOptions);

        ListView contactOptionsList = (ListView)view.findViewById(R.id.listView3);
        contactOptionsList.setAdapter(adapter);

        contactOptionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){

                    openEmergencyDialog();

                }else if (position == 1){

                    //Add complaint form fragment
                    ComplaintFormFragment nextFrag = new ComplaintFormFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_container,nextFrag).addToBackStack(null)
                            .commit();


                }


            }
        });


        return view;
    }

    public void  openEmergencyDialog(){

        HelpEmergencyDialogFragment dialogFragment = new HelpEmergencyDialogFragment();
        dialogFragment.show(getFragmentManager(),"Emergency Dialog");
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnPoliceHelpContactPreferenceFragmentInteractionListener){

            mListener = (OnPoliceHelpContactPreferenceFragmentInteractionListener) context;

        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentInteractionListener");
        }
    }

    public static HelpContactPreferenceFragment newInstance(){

        Bundle args = new Bundle();
        HelpContactPreferenceFragment fragment = new HelpContactPreferenceFragment();
        return fragment;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    private void dialPhoneNumber(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    public interface OnPoliceHelpContactPreferenceFragmentInteractionListener{

    }
}
