package bosunard.aston.com.finalyearproject;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bosunard.aston.com.finalyearproject.models.Journey;
import bosunard.aston.com.finalyearproject.models.Station;
import bosunard.aston.com.finalyearproject.models.StationShort;
import bosunard.aston.com.finalyearproject.models.StationShortList;
import bosunard.aston.com.finalyearproject.models.StationShortUtility;

public class FindJourneyFragment extends Fragment {

    private static final String TAG ="FindJourneyFragment";

  //  private EditText departingTime;
  //  private TimePickerDialog timePickerDialog;

//    private TextInputLayout textInputDepartingStation;
//    private TextInputLayout textInputArrivingStation;
//
//    private AutoCompleteTextView departingStation;
//    private AutoCompleteTextView arrivingStation;

    //private AutocompleteResultAdapter resultAdapter;

    private Journey journey;


    private Button submitButton;

    private List<String> resultList;
    private List<String> destinationList;
    // private StateProgressBar progressBar;

    private String[] descriptionData = {"Find", "Select", "Track"};

    private onFragmentInteractionListener mListener;

    private List<StationShort> stations;
    private StationShortList allStations;

    private ArrayAdapter<String> resultsAdapter;
    private ArrayAdapter<String> destinationAdapter;


    private String departingStationCode;
    private String arrivingStationCode;

    public FindJourneyFragment(){
        //required empty constructor

        stations = new ArrayList<>();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() !=null){

        }
        resultList = new ArrayList<>();
        destinationList = new ArrayList<>();
        resultsAdapter = new ArrayAdapter<String>//(getContext(),android.R.layout.select_dialog_item,resultList);
                (getContext(), android.R.layout.simple_dropdown_item_1line, resultList);
        destinationAdapter = new ArrayAdapter<String>//(getContext(),android.R.layout.select_dialog_item,resultList);
                (getContext(), android.R.layout.simple_dropdown_item_1line, destinationList);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_journey,container,false);

         fillResultList();
         fillDestinationList();

        StateProgressBar progressBar = (StateProgressBar) view.findViewById(R.id.state_progress_bar);
        progressBar.setStateDescriptionData(descriptionData);


         //departingStation = (AutoCompleteTextView) view.findViewById(R.id.departing_station_entry);
         //arrivingStation = (AutoCompleteTextView) view.findViewById(R.id.arriving_station_entry);

        Spinner departingSpinner = view.findViewById(R.id.spinner1);
        Spinner arrivingSpinner = view.findViewById(R.id.spinner2);
        Spinner timeSpinner = view.findViewById(R.id.spinner3);
        // a list of stations for the spinners.
        final String[] stationOptions = new String[]{"Birmingham New Street", "Five Ways", "University","Selly Oak","Bournville","Kings Norton","Northfield","Longbridge"};
        final String[] stationCodes = new String[]{"BHM","FWY","UNI","SLY","BRV","KNN","NFD","LOB"};

        //list of times for spinners
        final String[] hoursOfDay = new String[]{"00:00","06:00","07:00","08:00","09:00","10:00","11:00",
                                                "12:00","13:00","14:00","15:00","16:00","17:00","18:00",
                                                "19:00","20:00","21:00","22:00","23:00"};

        //create an adapter to describe how the items are displayed
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, stationOptions);

        ArrayAdapter<String> tAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, hoursOfDay);
        //set the spinners adapter
            departingSpinner.setAdapter(adapter);
            arrivingSpinner.setAdapter(adapter);
            timeSpinner.setAdapter(tAdapter);

            departingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    for (int x = 0; x < stationOptions.length; x++) {
                        if (x == position) {
                            for (int j = 0; j < stationCodes.length; j++) {
                                departingStationCode = stationCodes[x];
                            }
                        }

                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            arrivingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    for (int x = 0; x < stationOptions.length; x++) {
                        if (x == position) {
                            for (int j = 0; j < stationCodes.length; j++) {
                                arrivingStationCode = stationCodes[x];
                            }
                        }

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


//                    String appID = "2ed72192";
//                    String appKey = "8f82c455f34e9bc13a29571912d6920a";
//
//        String stationRequest = "http://transportapi.com/v3/uk/places.json?query=" + "birmingham" +
//                "&type=train_station&app_id=" + appID + "&app_key=" + appKey;
//        Log.i(TAG, "request is " + stationRequest);
//        StationReadFeed process = new StationReadFeed();
//        process.execute(new String[]{stationRequest});




//
//        String destinationRequest = "http://transportapi.com/v3/uk/places.json?query=" + "selly" +
//                "&type=train_station&app_id=" + appID + "&app_key=" + appKey;
//        Log.i(TAG, "query is " + query);
//        Log.i(TAG, "request is " + destinationRequest);
//        DestinationReadFeed destinationProcess = new DestinationReadFeed();
//        destinationProcess.execute(new String[]{destinationRequest});


        submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                StationShort departing = new StationShort();
//                StationShort destination = new StationShort();
//
//                for(int i = 0; i < stations.size(); i++) {
//
//                   if(query == stations.get(i).getName()){
//
//                       departing = stations.get(i);
//                      Log.i(TAG,"Sending departing station: "+ departing.toString());
//                   }
//
//                   if(destinationQuery == stations.get(i).name){
//
//                       destination = stations.get(i);
//                       Log.i(TAG,"Sending destination station: "+ destination.toString());
//
//                   }
//                }
//
//                Intent intent = new Intent(getContext(), JourneyResultsFragment.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("DEPARTING", departing);
//                bundle.putSerializable("DESTINATION", destination);
//                intent.putExtras(bundle);


                makeToast("Journey Submitted");

//                JourneyResultsFragment journeyResultsFragment = new JourneyResultsFragment();
//                FragmentTransaction fragmentTransaction =
//                        getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container,journeyResultsFragment);
//                fragmentTransaction.addToBackStack(null);


                //addFragment(new JourneyResultsFragment());


               if(mListener != null) {

                    mListener.findJourney(departingStationCode,arrivingStationCode);
                }

            }
        });

        return view;
    }



    //Toast Message Maker
    private void makeToast(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    private void fillResultList(){

        Log.i(TAG,"filling results list with:" + stations.size() + "stations found");



        for(int i = 0; i < stations.size(); i++) {

            //resultList.add(new AutoCompleteItem(stations.get(i).getName()));

            resultList.add((stations.get(i).getName()));
        }

        Log.i(TAG, "results List: "+ resultList.toString());
        //resultList.add(new AutoCompleteItem("Birmingham New Street"));
//        resultList.add(new AutoCompleteItem("Birmingham International"));
//        resultList.add(new AutoCompleteItem("Coventry"));
//        resultList.add(new AutoCompleteItem("Rugby"));
//        resultList.add(new AutoCompleteItem("Milton Keynes"));
//        resultList.add(new AutoCompleteItem("London Euston"));
//        resultList.add(new AutoCompleteItem("Leighton Buzzzard"));


    }
    private void fillDestinationList(){

        Log.i(TAG,"filling destination list with:" + stations.size() + "stations found");



        for(int i = 0; i < stations.size(); i++) {

            //resultList.add(new AutoCompleteItem(stations.get(i).getName()));

            destinationList.add((stations.get(i).getName()));
        }

        Log.i(TAG, "destination List: "+ destinationList.toString());

    }



    public static FindJourneyFragment newInstance(){

        Bundle args = new Bundle();
        //args.putSerializable("JOURNEY",journey);
//        args.putSerializable("DEPARTING",);
//        args.putSerializable("DESTINATION",arrivalStation);
        FindJourneyFragment fragment = new FindJourneyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof onFragmentInteractionListener){

            mListener = (onFragmentInteractionListener) context;

        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentInteractionListener");
        }
    }

    public interface onFragmentInteractionListener{

        void findJourney(String departingStationCode, String arrivingStationCode); //Journey journey
    }


//    //--------------------------TransportAPI REsULTS-------------------------------------------//

//    protected void reportBack(StationShortList stations) {
//
//        this.stations.addAll(stations.getMember());
//        fillResultList();
//        if (resultsAdapter != null) {
//            Log.i(TAG,  "notify adapter " + stations.getMember().size() + " stations found.");
//            resultsAdapter.notifyDataSetChanged();
//        }
//        // tell adapter we have data.
//        Log.i(TAG,  stations.getMember().size() + " stations found.");
//        //departingStation.getAdapter().notify();
//        //arrivingStation.getAdapter().notify();
//
//    }
//    protected void reportBackDestinations(StationShortList stations) {
//
//        this.stations.addAll(stations.getMember());
//        fillDestinationList();
//        if (destinationAdapter != null) {
//            Log.i(TAG,  "notify adapter " + stations.getMember().size() + " stations found.");
//            destinationAdapter.notifyDataSetChanged();
//        }
//        // tell adapter we have data.
//        Log.i(TAG,  stations.getMember().size() + " stations found.");
//        //departingStation.getAdapter().notify();
//        //arrivingStation.getAdapter().notify();
//
//    }




//
//    //------------------------------------TransportAPI-------------------------------------------//
//
//    private class StationReadFeed extends AsyncTask<String, Void, StationShortList> {
//        //private final ProgressDialog dialog = new ProgressDialog();
//        private static final String TAG ="StationReadFeed";
//
//        @Override
//        protected StationShortList doInBackground(String... urls) {
//            try {
//                String referer = null;
//
//                if (urls.length == 1) {
//                    referer = null;
//                } else {
//                    referer = urls[1];
//                }
//                StationShortList stations = StationShortUtility.findStation(urls[0], referer);
//
//                Log.i(TAG, "Number of stations found " + stations.getMember().size());
//                return stations;
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.i(TAG, e.getMessage());
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            //this.dialog.show();
//            Log.i(TAG, "Getting stations...");
//        }
//
//        @Override
//        protected void onPostExecute(StationShortList stations) {
//            //this.dialog.dismiss();
//            Log.i(TAG, "Got stations...");
//            reportBack(stations);
//        }
//
//
//    }
//
//    private class DestinationReadFeed extends AsyncTask<String, Void, StationShortList> {
//        //private final ProgressDialog dialog = new ProgressDialog();
//        private static final String TAG ="DestinationReadFeed";
//
//        @Override
//        protected StationShortList doInBackground(String... urls) {
//            try {
//                String referer = null;
//
//                if (urls.length == 1) {
//                    referer = null;
//                } else {
//                    referer = urls[1];
//                }
//                StationShortList stations = StationShortUtility.findStation(urls[0], referer);
//
//                Log.i(TAG, "Number of stations found " + stations.getMember().size());
//                return stations;
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.i(TAG, e.getMessage());
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            //this.dialog.show();
//            Log.i(TAG, "Getting stations...");
//        }
//
//        @Override
//        protected void onPostExecute(StationShortList stations) {
//            //this.dialog.dismiss();
//            Log.i(TAG, "Got stations...");
//            reportBackDestinations(stations);
//        }
//
//
//    }


}
