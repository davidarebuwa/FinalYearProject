package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import bosunard.aston.com.finalyearproject.models.Journey;

public class PastJourneyListFragment extends Fragment {

    private static final String TAG = "PastJourneyListFragment";

    private RecyclerView journeyList;
    private ArrayList<Journey> journeys;

    private onPastJourneyListFragmentInteractionListener mListener;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private PastJourneysListAdapter mPastJourneysListAdapter;

    private DocumentSnapshot mLastQueriedDocument;

    public PastJourneyListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        journeys = new ArrayList<>();
        getJourneys();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past_journeys, container, false);

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        journeyList = (RecyclerView) view.findViewById(R.id.past_journeys_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        journeyList.setLayoutManager(layoutManager);

        mPastJourneysListAdapter = new PastJourneysListAdapter(journeys,getContext());
        journeyList.setAdapter(mPastJourneysListAdapter);





        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
       // getJourneys();
    }

    public void onRefresh() {
        getJourneys();
        mSwipeRefreshLayout.setRefreshing(false);
    }





    private void getJourneys(){

        FirebaseFirestore  db = FirebaseFirestore.getInstance();

        CollectionReference collectionReference = db.collection("Journeys");

        Query query = null;
        if(mLastQueriedDocument != null){

           query = collectionReference.whereEqualTo("userId",FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .orderBy(("timestamp"),Query.Direction.ASCENDING).startAfter(mLastQueriedDocument);

        }else{

           query = collectionReference.whereEqualTo("userId",FirebaseAuth.getInstance().getCurrentUser().getUid())
                   .orderBy(("timestamp"),Query.Direction.ASCENDING);
        }

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for(QueryDocumentSnapshot document:task.getResult()){

                        Journey journey = document.toObject(Journey.class);

                        journeys.add(journey);

                        Log.i(TAG,"Journeys retrieved: " + journeys.size());

                        if(task.getResult().size() != 0){
                            mLastQueriedDocument = task.getResult().getDocuments().get(task.getResult().size() - 1);
                        }

                    }
                    mPastJourneysListAdapter.notifyDataSetChanged();
                }else{

                    toastMessage("Query failed. Please check log messages");
                }

            }
        });


    }

    private void toastMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface onPastJourneyListFragmentInteractionListener{

    }
}
