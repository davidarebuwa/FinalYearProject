package bosunard.aston.com.finalyearproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bosunard.aston.com.finalyearproject.models.Updates;

public class UpdatesFragment extends Fragment {

    private List<Updates> updates;


    private onUpdatesFragmentListInteractionListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journey_updates, container, false);


        fillUpdates();
        RecyclerView updatesList = view.findViewById(R.id.updates_rv);

        UpdatesAdapter adapter = new UpdatesAdapter(getContext(),updates);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        updatesList.setAdapter(adapter);




        updatesList.setLayoutManager(layoutManager);

        return view;


    }


    public void fillUpdates(){

        updates = new ArrayList<>();
        updates.add(new Updates(R.drawable.virgin,"Virgin Trains","Train Delay"));
    }

    public interface onUpdatesFragmentListInteractionListener{

    }
}
