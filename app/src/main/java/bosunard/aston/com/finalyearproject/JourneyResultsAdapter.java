package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import bosunard.aston.com.finalyearproject.models.Departures;
import bosunard.aston.com.finalyearproject.models.Journey;
import bosunard.aston.com.finalyearproject.models.LiveTimeTableItem;
import bosunard.aston.com.finalyearproject.models.Timetable;

public class JourneyResultsAdapter extends RecyclerView.Adapter<JourneyResultsAdapter.ViewHolder> {

    private static final String TAG = "JourneyResultsAdapter";
    //private ArrayList<Journey> journeyList = new ArrayList<>();
    private Timetable sTimetable;
    //private ArrayList<Stops> stopsList = new ArrayList<>();
    private ArrayList<LiveTimeTableItem> journeys;

   // private ArrayList<Timetable> timetableList;

   // private ArrayList<JourneyPlanner> routePlan;


    private Context context;

    private JourneyResultsFragment.onFragmentListInteractionListener mListener;

    private String departingStationCode;
    private String arrivingStationCode;


    public JourneyResultsAdapter(String departingStationCode,String arrivingStationCode,ArrayList<LiveTimeTableItem> journeys, Context context, JourneyResultsFragment.onFragmentListInteractionListener mListener) {

        this.journeys = journeys;
        this.context = context;
        this.mListener = mListener;
        this.departingStationCode = departingStationCode;
        this.arrivingStationCode = arrivingStationCode;

    }

    @NonNull
    @Override
    public JourneyResultsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.journey_result_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final JourneyResultsAdapter.ViewHolder viewHolder, int i) {

        viewHolder.timetable = journeys.get(i);
        //viewHolder.stopsList = stopsList.get(i);
       // viewHolder.mTimetable = sTimetable;
       // viewHolder.timetableList = timetableList.get(i);
       // viewHolder.routePlan = routePlan.get(i);

        //viewHolder.journeyTime.setText(journeyList.get(i).getDepartingTime() + " → "+ journeyList.get(i).getArrivingTime());
       viewHolder.journeyTime.setText("Departing at: " + viewHolder.timetable.getAimed_arrival_time());



       // viewHolder.platformNumber.setText(journeyList.get(i).getPlatformNumber());
        viewHolder.platformNumber.setText(viewHolder.timetable.getPlatform());


       // viewHolder.journeyDuration.setText(journeyList.get(i).getJourneyDuration());
        //viewHolder.journeyDuration.setText((CharSequence) viewHolder.routePlan.routes.get(i).getDuration());

        //viewHolder.trainService.setText(journeyList.get(i).getTrainService());
        viewHolder.trainService.setText(viewHolder.timetable.getOperator());

       // viewHolder.trainDestination.setText(journeyList.get(i).getTrainDestination());
        viewHolder.trainDestination.setText(viewHolder.timetable.getDestination_name());



        //viewHolder.departingArrivingStation.setText(journeyList.get(i).getDepartingStation() + " → " + journeyList.get(i).getArrivingStation());
        //viewHolder.departingArrivingStation.setText(viewHolder.mTimetable.stops.get(0).getStation_name() + " → " + viewHolder.mTimetable.stops.get(viewHolder.mTimetable.stops.size()-1).getStation_name());


        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mListener != null){

                    mListener.addJourney(viewHolder.timetable,departingStationCode,arrivingStationCode);

                  //  mListener.journeyDetails(viewHolder.timetable,viewHolder.timetableList,viewHolder.routePlan);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return journeys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //LiveTimeTableItem journey;
        LiveTimeTableItem timetable;
        //Stops stopsList;
        //LiveTimetable timetable;
        //Timetable mTimetable;
        //JourneyPlanner routePlan;
        View view;

        TextView journeyTime;
        TextView platformNumber;
        TextView departingArrivingStation;
        TextView trainService;
        TextView trainDestination;
        TextView journeyDuration;

        //Button selectJourney;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            journeyTime = itemView.findViewById(R.id.journey_hour_minute_time);
            platformNumber = itemView.findViewById(R.id.journey_platform_number);
            departingArrivingStation = itemView.findViewById(R.id.departing_arriving_stations);
            trainService = itemView.findViewById(R.id.journey_train_service);
            trainDestination = itemView.findViewById(R.id.train_journey_destination);
            journeyDuration = itemView.findViewById(R.id.journey_duration);
            //selectJourney = itemView.findViewById(R.id.submit_button);
        }
    }
}
