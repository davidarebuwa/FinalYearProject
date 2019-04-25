package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;
import java.util.List;

import bosunard.aston.com.finalyearproject.models.Departures;
import bosunard.aston.com.finalyearproject.models.Journey;
import bosunard.aston.com.finalyearproject.models.LiveTimeTableItem;
import bosunard.aston.com.finalyearproject.models.LiveTimetable;
import bosunard.aston.com.finalyearproject.models.LiveTimetableUtility;
import bosunard.aston.com.finalyearproject.models.StationShort;
import bosunard.aston.com.finalyearproject.models.Stops;
import bosunard.aston.com.finalyearproject.models.Timetable;

import static android.support.constraint.Constraints.TAG;

public class JourneyResultsFragment extends Fragment {

    private static final String TAG  = "JourneyResultsFragment";
    //private ArrayList<Journey> journeys;
    private ArrayList<LiveTimeTableItem> journeysList;
    //private ArrayList<Stops> stopsList;

    private Timetable timetable;

    private StateProgressBar progressBar;
    private JourneyResultsAdapter adapter;

    String[] stepsData = {"Find", "Select", "Track"};

    private onFragmentListInteractionListener mListener;

   // private StationShort departingStation;
   // private  StationShort destinationStation;

    private List<LiveTimeTableItem> resultList;

    private String query;
    private String stopsQuery;

    private String departingStationCode;
    private String arrivingStationCode;

    private TextView resultsCount;


    public JourneyResultsFragment() {
        journeysList = new ArrayList<>();
        //stopsList = new ArrayList<>();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Intent intent = getActivity().getIntent();
//        Bundle bundle = intent.getExtras();
//
//        departingStation = (StationShort) bundle.getSerializable("DEPARTING");
//        destinationStation = (StationShort) bundle.getSerializable("DESTINATION");

        departingStationCode = (String) getArguments().getSerializable("DEPARTING");
        arrivingStationCode = (String) getArguments().getSerializable("DESTINATION");

        query = "https://transportapi.com/v3/uk/train/station/"+departingStationCode+"/live.json?app_id=2ed72192&app_key=8f82c455f34e9bc13a29571912d6920a" +
                "&called_at="+ arrivingStationCode + "&darwin=true&train_status=passenger";
        JourneyReadFeed process = new JourneyReadFeed();
        Log.i(TAG,"query url:" + query);
        process.execute(new String[]{query});


//        stopsQuery = "https://transportapi.com/v3/uk/train/service/12272820/2019-04-08//timetable.json?app_id=2ed72192&app_key=8f82c455f34e9bc13a29571912d6920a" +
//                "&darwin=true&live=false&station_code=BHM";
//        TimetableReadFeed mProcess = new TimetableReadFeed();
//        mProcess.execute(new String[]{stopsQuery});
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //fillResultsList();

        View view = inflater.inflate(R.layout.select_journey_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.journey_results_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new JourneyResultsAdapter(departingStationCode,arrivingStationCode,journeysList,getContext(),mListener);
        //adapter = new JourneyResultsAdapter(journeysList,getContext(),mListener);
        recyclerView.setAdapter(adapter);

        progressBar = (StateProgressBar) view.findViewById(R.id.state_progress_bar);
        progressBar.setStateDescriptionData(stepsData);

        TextView resultsTitle = view.findViewById(R.id.journey_results_name);
        resultsTitle.setText("Trains From: " + departingStationCode + " → " + arrivingStationCode);

         resultsCount = view.findViewById(R.id.journey_results_count);







        return view;
    }


    public static JourneyResultsFragment newInstance(String departingStationCode,String arrivingStationCode){

        Bundle args = new Bundle();
        //args.putSerializable("JOURNEY",journey);
        args.putSerializable("DEPARTING",departingStationCode);
        args.putSerializable("DESTINATION",arrivingStationCode);
        JourneyResultsFragment fragment = new JourneyResultsFragment();
        fragment.setArguments(args);

        return fragment ;
    }




    //Toast Message Maker
    private void makeToast(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof onFragmentListInteractionListener){

            mListener = (onFragmentListInteractionListener) context;

        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void fillResultsList(){

        resultList = new ArrayList<>();

        Log.i(TAG,"filling results list with:" + journeysList.size() + "journeys");

        for(int i = 0; i < journeysList.size(); i++) {

            //resultList.add(new AutoCompleteItem(stations.get(i).getName()));

            resultList.add((journeysList.get(i)));
        }




    }


    public interface onFragmentListInteractionListener{

        void addJourney(LiveTimeTableItem journey,String departingStationCode, String arrivingStationCode);
        //void journeyDetails(LiveTimetable timetable, Timetable stopsList, JourneyPlanner routePlan);
    }

    protected void reportBack(LiveTimetable timetable) {

        this.journeysList.addAll(timetable.departures.getAll());

        resultsCount.setText(journeysList.size()  +" results");
        // tell adapter we have data.
        Log.i(TAG,  "reportBack(): " + timetable.departures.all.size() + " stations found.");
        adapter.notifyDataSetChanged();

       // resultsAdapter.notifyDataSetChanged();
    }

//    protected void reportBackStops(Timetable timetable) {
//
//          this.stopsList.addAll(timetable.getStops());
//
//        // tell adapter we have data.
//
//        Log.i(TAG,  timetable.getStops().size() + " stops found.");
//         adapter.notifyDataSetChanged();
//
//        // resultsAdapter.notifyDataSetChanged();
//    }



   // ------------------------------------TransportAPI-------------------------------------------//

    private class JourneyReadFeed extends AsyncTask<String, Void, LiveTimetable> {
        //private final ProgressDialog dialog = new ProgressDialog();
        private static final String TAG  = "JourneyReadFeed";

        @Override
        protected LiveTimetable doInBackground(String... urls) {
            try {
                String referer = null;
                //dialog.setMessage("Fetching Journey Data");
                if (urls.length == 1) {
                    referer = null;
                } else {
                    referer = urls[1];
                }
                //LiveTimetable timetable = LiveTimetableUtility.findResults(urls[0], referer);
                LiveTimetable timetable = LiveTimetableUtility.findResults(urls[0], referer);

                Log.i(TAG, "Number of journeys found " + timetable.departures.all.size());

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
            Log.i(TAG, "Getting journeys...");
        }

        @Override
        protected void onPostExecute(LiveTimetable timetable) {
            //this.dialog.dismiss();
            Log.i(TAG, "Got journeys...");
            reportBack(timetable);
        }


    }
//
//    private class TimetableReadFeed extends AsyncTask<String, Void, Timetable> {
//        //private final ProgressDialog dialog = new ProgressDialog();
//
//        @Override
//        protected Timetable doInBackground(String... urls) {
//            try {
//                String referer = null;
//                //dialog.setMessage("Fetching Journey Data");
//                if (urls.length == 1) {
//                    referer = null;
//                } else {
//                    referer = urls[1];
//                }
//                Timetable timetable = TimetableUtility.findTimetable(urls[0], referer);
//
//                Log.i(TAG, "We got timetable for: " + timetable.getOrigin_name() + " → " + timetable.getDestination_name());
//                return timetable;
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
//            Log.i(TAG, "Getting timetable...");
//        }
//
//        @Override
//        protected void onPostExecute(Timetable timetable) {
//            //this.dialog.dismiss();
//            Log.i(TAG, "Got timetable...");
//            reportBackStops(timetable);
//        }


//    }




}
