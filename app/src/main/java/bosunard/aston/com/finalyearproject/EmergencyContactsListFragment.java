package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import bosunard.aston.com.finalyearproject.models.Contact;

public class EmergencyContactsListFragment extends Fragment {


    private String[] contacts = {};

    private List<Contact>contactList;

    private onEmergencyContactsListFragmentInteractionListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergency_contacts, container, false);



        final ListView emergencyList = (ListView)view.findViewById(R.id.emergency_contact_list);

        final ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                R.layout.help_list_item, contacts);

        emergencyList.setAdapter(adapter);

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof onEmergencyContactsListFragmentInteractionListener){

            mListener = (onEmergencyContactsListFragmentInteractionListener) context;

        }else{

            throw new RuntimeException(context.toString() + "must implement onEmergencyContactsListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }


    public interface onEmergencyContactsListFragmentInteractionListener{


    }
}
