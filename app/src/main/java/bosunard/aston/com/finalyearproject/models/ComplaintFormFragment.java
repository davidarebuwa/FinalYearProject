package bosunard.aston.com.finalyearproject.models;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import bosunard.aston.com.finalyearproject.R;

public class ComplaintFormFragment extends Fragment {

    EditText date,journeyPick;
    RadioButton addLocation;

    Button submitComplaint;


    private OnComplaintFormFragmentInteractionListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaint_form, container, false);

        date = view.findViewById(R.id.date_pick);
        journeyPick = view.findViewById(R.id.journey_select);

        addLocation = view.findViewById(R.id.share_location_button);

        submitComplaint =view.findViewById(R.id.submit_complaint);

        return view;
    }

    public static ComplaintFormFragment newInstance(){

        Bundle args = new Bundle();
        ComplaintFormFragment fragment = new ComplaintFormFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnComplaintFormFragmentInteractionListener){

            mListener = (OnComplaintFormFragmentInteractionListener) context;

        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    public interface OnComplaintFormFragmentInteractionListener{}


}
