package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bosunard.aston.com.finalyearproject.models.Journey;

public class JourneyAccountListAdapter extends RecyclerView.Adapter<JourneyAccountListAdapter.JourneyListViewHolder> {

    private List<Journey> journeyList;
    private Context mContext;

    public JourneyAccountListAdapter(List<Journey> journeyList, Context mContext) {
        this.journeyList = journeyList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public JourneyListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.journey_item_list,viewGroup,false);

        return new JourneyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JourneyListViewHolder journeyListViewHolder, int i) {

        journeyListViewHolder.departingArrivingStation.setText(journeyList.get(i).getDepartingStation() + " → " + journeyList.get(i).getArrivingStation());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class JourneyListViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView departingArrivingStation;
        //TextView journeyTime;


        public JourneyListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            departingArrivingStation = itemView.findViewById(R.id.journey_departure_to_destination);


        }
    }
}
