package bosunard.aston.com.finalyearproject;

import android.content.Context;
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

public class HelpContactPreferenceFragment extends Fragment {

    private OnHelpContactPreferenceListFragmentInteractionListener mListener;

    private String[] contactOptions = {"Live chat","Complaint form"};

    public HelpContactPreferenceFragment() {
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

                    HelpLiveChatFragment helpFrag = new HelpLiveChatFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_container,helpFrag).addToBackStack(null)
                            .commit();
                }
                else if(position == 1){

                    ComplaintFormFragment nextFrag = new ComplaintFormFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_container,nextFrag).addToBackStack(null)
                            .commit();
                }
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnHelpContactPreferenceListFragmentInteractionListener){

            mListener = (OnHelpContactPreferenceListFragmentInteractionListener) context;

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

    public interface OnHelpContactPreferenceListFragmentInteractionListener{

    }
}
