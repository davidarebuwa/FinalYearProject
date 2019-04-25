package bosunard.aston.com.finalyearproject;

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

public class PoliceHelpCategoryFragment extends Fragment {

    private onPoliceHelpCategoryListFragmentInteractionListener mListener;


    private String[] categoryOptions ={"Anti-social Behaviour","Violence","Distress","Assault","Suspicious Artefact","Theft"};

    public PoliceHelpCategoryFragment() { }

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

                PoliceHelpContactPreferenceFragment trackFrag = new PoliceHelpContactPreferenceFragment();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,trackFrag).addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }





    public interface onPoliceHelpCategoryListFragmentInteractionListener{

    }
}
