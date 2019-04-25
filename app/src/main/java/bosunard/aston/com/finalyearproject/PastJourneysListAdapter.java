package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bosunard.aston.com.finalyearproject.models.Journey;

public class PastJourneysListAdapter extends RecyclerView.Adapter<PastJourneysListAdapter.PastJourneysListViewHolder> {

    private ArrayList<Journey> journeyList;
    private Context mContext;

    public PastJourneysListAdapter(ArrayList<Journey> journeyList, Context context){

        this.mContext = context;
        this.journeyList = journeyList;
    }

    @NonNull
    @Override
    public PastJourneysListAdapter.PastJourneysListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.past_journeys_list_item,viewGroup,false);


        return new PastJourneysListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PastJourneysListAdapter.PastJourneysListViewHolder pastJourneysListViewHolder, int i) {

        pastJourneysListViewHolder.journey = journeyList.get(i);

         pastJourneysListViewHolder.departingDestination.setText(pastJourneysListViewHolder.journey.getDepartingStation()  + " → "+ pastJourneysListViewHolder.journey.getArrivingStation());

         pastJourneysListViewHolder.departingArrivingTime.setText(pastJourneysListViewHolder.journey.getDepartingTime() + " → " + pastJourneysListViewHolder.journey.getArrivingTime());

         pastJourneysListViewHolder.trainDestination.setText(pastJourneysListViewHolder.journey.getTrainDestination());

         pastJourneysListViewHolder.trainService.setText(pastJourneysListViewHolder.journey.getTrainService());

    }

    @Override
    public int getItemCount() {
        return journeyList.size();
    }

    public class PastJourneysListViewHolder extends RecyclerView.ViewHolder {

        View view;
        Journey journey;
        TextView departingDestination;
        TextView departingArrivingTime;
        TextView trainDestination;
        TextView trainService;


        public PastJourneysListViewHolder(@NonNull View itemView) {
            super(itemView);

            view  = itemView;
            departingDestination = itemView.findViewById(R.id.departing_destination);
            departingArrivingTime = itemView.findViewById(R.id.journey_time);
            trainDestination = itemView.findViewById(R.id.train_destination);
            trainService = itemView.findViewById(R.id.train_service_name);
        }
    }
}
