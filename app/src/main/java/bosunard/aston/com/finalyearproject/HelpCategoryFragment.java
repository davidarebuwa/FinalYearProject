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

public class HelpCategoryFragment extends Fragment {

    private onHelpCategoryListFragmentInteractionListener mListener;

    public HelpCategoryFragment() { }

    private String[] categoryOptions ={"Anti-social Behaviour","Violence","Distress","Assault","Fatal Injury","Suspicious Artifact"};

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

                HelpContactPreferenceFragment trackFrag = new HelpContactPreferenceFragment();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,trackFrag).addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    public static HelpCategoryFragment newInstance(){

        Bundle args = new Bundle();
        HelpCategoryFragment fragment = new HelpCategoryFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof onHelpCategoryListFragmentInteractionListener){

            mListener = (onHelpCategoryListFragmentInteractionListener) context;

        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface onHelpCategoryListFragmentInteractionListener{

    }

}
