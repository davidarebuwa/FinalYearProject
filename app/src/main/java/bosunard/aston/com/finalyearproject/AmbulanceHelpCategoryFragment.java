package bosunard.aston.com.finalyearproject;

import android.content.Context;
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

public class AmbulanceHelpCategoryFragment extends Fragment {

    private onAmbulanceHelpCategoryFragmentInteractionListener mListener;

    public AmbulanceHelpCategoryFragment() { }

    private String[] categoryOptions ={"Fatal Injury","Accident"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_category, container, false);


        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                R.layout.help_list_item, categoryOptions);

        ListView listView = (ListView) view.findViewById(R.id.listView2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dialPhoneNumber("999");
            }
        });

        return view;
    }

    public static AmbulanceHelpCategoryFragment newInstance(){

        Bundle args = new Bundle();
        AmbulanceHelpCategoryFragment fragment = new AmbulanceHelpCategoryFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof onAmbulanceHelpCategoryFragmentInteractionListener){

            mListener = (onAmbulanceHelpCategoryFragmentInteractionListener) context;

        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void dialPhoneNumber(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    public interface onAmbulanceHelpCategoryFragmentInteractionListener{

    }
}
