package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kofigyan.stateprogressbar.StateProgressBar;

import bosunard.aston.com.finalyearproject.models.Journey;
import bosunard.aston.com.finalyearproject.models.JourneyPlanner;
import bosunard.aston.com.finalyearproject.models.LiveTimetable;
import bosunard.aston.com.finalyearproject.models.LiveTimeTableItem;
import bosunard.aston.com.finalyearproject.models.Timetable;
import bosunard.aston.com.finalyearproject.models.TimetableUtility;

public class AddJourneyFragment extends Fragment {

    private static final String TAG  = "AddJourneyFragment";
    private StateProgressBar stateProgressBar;

    private LiveTimeTableItem timetable;
    //private Timetable stopsList;

    private onAddJourneyFragmentInteractionListener mListener;

    String[] stepsData = {"Find", "Select", "Track"};


    private LiveTimeTableItem journey;
    private  Timetable mTimetable;
    private JourneyPlanner routePlan;

    private String departingStationCode;
    private String arrivingStationCode;

    private View mParentLayout;

    //Database
//    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
//    private DatabaseReference mDatabaseReference;

    private Journey journeyDB;







    public AddJourneyFragment(){ super();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        journey = (LiveTimeTableItem) getArguments().getSerializable("JOURNEY");
        departingStationCode = (String) getArguments().getSerializable("DEPARTING");
        arrivingStationCode = (String) getArguments().getSerializable("DESTINATION");

        this.setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.add_journey_fragment,container,false);

       stateProgressBar = (StateProgressBar) view.findViewById(R.id.state_progress_bar);
       stateProgressBar.setStateDescriptionData(stepsData);



//       FirebaseUser user = mAuth.getCurrentUser();
  //                 final String userID = user.getUid();
        Button addBtn = view.findViewById(R.id.add_journey_button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TrackJourneyFragment trackFrag = new TrackJourneyFragment();
//                getActivity().getSupportFragmentManager().beginTransaction().
//                        replace(R.id.fragment_container,trackFrag).addToBackStack(null)
//                        .commit();

                mListener.trackJourney(journeyDB);



                FirebaseFirestore db = FirebaseFirestore.getInstance();

                DocumentReference newRef = db
                        .collection("Journeys")
                        .document();

                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                 journeyDB = new Journey();
                journeyDB.setDepartingStation(departingStationCode);
                journeyDB.setArrivingStation(arrivingStationCode);
                journeyDB.setDepartingTime(journey.getAimed_departure_time());
                journeyDB.setArrivingTime(journey.getAimed_arrival_time());
                journeyDB.setNoOfStops(mTimetable.stops.size());
                journeyDB.setTrainService(journey.getOperator());
                journeyDB.setPlatformNumber(journey.getPlatform());
                journeyDB.setUserId(userId);

                newRef.set(journeyDB).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            toastMessage("Journey uploaded");
                        }
                        else{
                            toastMessage("Journey upload failed. Check log.");
                        }
                    }
                });


           }
       });

        mParentLayout = view;

       return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //fillLayout(journey);
         // String appID = "2ed72192";
      //  String appKey = "8f82c455f34e9bc13a29571912d6920a";
                String stationRequest = "https://transportapi.com/v3/uk/train/service/" +
                        journey.getService()+"///timetable.json?app_id=2ed72192&app_key=8f82c455f34e9bc13a29571912d6920a" +
                        "&darwin=true&live=true";
        TrackReadFeed process = new TrackReadFeed();
        process.execute(new String[]{stationRequest});
    }

    public void fillLayout(LiveTimeTableItem journey,Timetable timetable) //Timetable timetable //Stops stops
    {

        TextView departingArrivingStation = (TextView)getView().findViewById(R.id.journey_departure_to_destination);
        departingArrivingStation.setText( departingStationCode + " → " + arrivingStationCode);

       // departingArrivingStation.setText(timetable.getOrigin_name() + " → " + timetable.getDestination_name());

        TextView journeyTime = (TextView)getView().findViewById(R.id.journey_hour_minute_time);
       // journeyTime.setText(journey.getDepartingTime() + " → " + journey.getArrivingTime());


        journeyTime.setText(timetable.stops.get(0).getAimed_arrival_time() + " → " + timetable.stops.get(timetable.stops.size() - 1).getAimed_arrival_time());
       // journeyTime.setText(timetable.getAimed_departure_time() +  " → " + timetable.getAimed_arrival_time());

        TextView trainService = (TextView)getView().findViewById(R.id.journey_train_service);
        trainService.setText(journey.getOperator());

        //trainService.setText(timetable.getOperator());

        TextView journeyDuration = (TextView)getView().findViewById(R.id.journey_duration);
        //journeyDuration.setText(journey.getJourneyDuration());

        //journeyDuration.setText((CharSequence) routePlan.routes.get(0).getDuration());

        TextView trainDestination = (TextView)getView().findViewById(R.id.journey_train_destination);
        trainDestination.setText(journey.getDestination_name());

       // trainDestination.setText(timetable.getDestination_name());

        TextView changesStops = (TextView)getView().findViewById(R.id.journey_stops_changes);
        //changesStops.setText(journey.getNoOfStops() + " stops, " + journey.getNoOfchanges() + " changes");

        changesStops.setText(timetable.stops.size() + " stops");

        TextView carraiges = (TextView)getView().findViewById(R.id.journey_train_carriages);
       // carraiges.setText(journey.getNoOfCarraiges() + "carraiges, " + journey.getNoOfQuietCarraiges() + " quiet");

        TextView platformNo = (TextView)getView().findViewById(R.id.journey_platform_number);
        platformNo.setText(journey.getPlatform());

        //platformNo.setText(timetable.getPlatform());


    }




    public static AddJourneyFragment newInstance(LiveTimeTableItem journey,String departingStationCode, String arrivingStationCode){

        Bundle args = new Bundle();
        args.putSerializable("JOURNEY",journey);
        args.putSerializable("DEPARTING", departingStationCode);
        args.putSerializable("DESTINATION", arrivingStationCode);
        AddJourneyFragment fragment = new AddJourneyFragment();
        fragment.setArguments(args);

        return fragment;
    }

//    public static AddJourneyFragment newInstance(LiveTimetable timetable, Timetable timetableList, JourneyPlanner routePlan){
//
//        Bundle args = new Bundle();
//        args.putSerializable("TIMETABLE",timetable);
//        args.putSerializable("STOPSLIST",timetableList);
//        args.putSerializable("ROUTEDATA", routePlan);
//        AddJourneyFragment fragment = new AddJourneyFragment();
//        fragment.setArguments(args);
//
//        return fragment;
//    }

    private void toastMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof onAddJourneyFragmentInteractionListener){
            mListener = (onAddJourneyFragmentInteractionListener) context;
       }else{

            throw new RuntimeException(context.toString() + "must implement onAddJourneyFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }


    @Override
    public void onStart() {
        super.onStart();
       // mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
    }



    public interface onAddJourneyFragmentInteractionListener{

        void trackJourney(Journey journey);
        void journeyDetails(String departingStationCode, String arrivingStationCode);


    }



    //------------------------------------TransportAPI-------------------------------------------//

    private class TrackReadFeed extends AsyncTask<String, Void, Timetable> {
        //private final ProgressDialog dialog = new ProgressDialog();

        private static final String TAG  = "TrackReadFeed";
        @Override
        protected Timetable doInBackground(String... urls) {
            try {
                String referer = null;
                //dialog.setMessage("Fetching Journey Data");
                if (urls.length == 1) {
                    referer = null;
                } else {
                    referer = urls[1];
                }
                Timetable timetable = TimetableUtility.findTimetable(urls[0], referer);

                Log.i(TAG, "Number of stations found " + timetable.getStops().size());
                return timetable;
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG, e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPreExecute() {

            //this.dialog.show();
            Log.i(TAG, "Getting journey details...");
        }

        @Override
        protected void onPostExecute(Timetable timetable) {
            //this.dialog.dismiss();
            Log.i(TAG, "Got journey details...");
            mTimetable = timetable;

            fillLayout(journey,timetable);
            //reportBack(timetable);
        }


    }


}
