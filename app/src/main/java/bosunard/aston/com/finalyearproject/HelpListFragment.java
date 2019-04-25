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

public class HelpListFragment extends Fragment {

    private String[] helpListOptions ={"Train Inspector", "Police","Ambulance"};
    private onHelpListFragmentInteractionListener mListener;


    public HelpListFragment() {
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_list_fragment, container, false);


        final ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                R.layout.help_list_item, helpListOptions);

        final ListView helpList = (ListView)view.findViewById(R.id.listView);
        helpList.setAdapter(adapter);

        helpList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){

                    HelpCategoryFragment trackFrag = new HelpCategoryFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_container,trackFrag).addToBackStack(null)
                            .commit();
                }else if(position == 1){

                    PoliceHelpCategoryFragment trackFrag = new PoliceHelpCategoryFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_container,trackFrag).addToBackStack(null)
                            .commit();

                }else if(position == 2){

                    AmbulanceHelpCategoryFragment trackFrag = new AmbulanceHelpCategoryFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_container,trackFrag).addToBackStack(null)
                            .commit();

                }



            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof onHelpListFragmentInteractionListener){

            mListener = (onHelpListFragmentInteractionListener) context;

        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    public static HelpListFragment newInstance(){

        Bundle args = new Bundle();
        HelpListFragment fragment = new HelpListFragment();
        return fragment;
    }

    public interface onHelpListFragmentInteractionListener{

        void showHelpCategory();
    }
}
