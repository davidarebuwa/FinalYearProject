package bosunard.aston.com.finalyearproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyachi.stepview.VerticalStepView;
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

public class CurrentJourneyFragment extends Fragment {

    VerticalStepView trainStatus;

    private Journey journey;

    private TextView departingArrivingStation;
    private TextView journeyTime;
    private TextView trainDestination;
    private TextView trainService;


    private DocumentSnapshot mLastQueriedDocument;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_journey, container, false);

        departingArrivingStation  = view.findViewById(R.id.departing_arriving_stations);
        journeyTime = view.findViewById(R.id.journey_time);
        trainDestination = view.findViewById(R.id.train_journey_destination);
        trainService = view.findViewById(R.id.train_journey_service);



        List<String> trainStops = new ArrayList<>();
        trainStops.add("Birmingham New Street");
        trainStops.add("Birmingham Moor Street");

//        trainStatus.setStepsViewIndicatorComplectingPosition(trainStops.size() - 1)
//                .reverseDraw(false)
//                .setStepViewTexts(trainStops)
//                .setLinePaddingProportion(0.85f)
//                .setStepsViewIndicatorCompletedLineColor(Color.parseColor("#f26644"))
//                .setStepViewComplectedTextColor(ContextCompat.getColor(getContext(),R.color.uncompleted_text_color))
//                .setStepViewUnComplectedTextColor(Color.parseColor("#FFFFFF"))
//                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getContext(),R.drawable.ic_train_black_24dp))
//                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getContext(),R.drawable.ic_error_outline_black_24dp))
//                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getContext(),R.drawable.ic_train_purp_24dp));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getJourney();
    }


    private void getJourney(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference collectionReference = db.collection("Journeys");

        Query query = null;
        if(mLastQueriedDocument != null){

            collectionReference.whereEqualTo("userId",FirebaseAuth.getInstance().getCurrentUser().getUid())
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

                        journey = document.toObject(Journey.class);

                        departingArrivingStation.setText(journey.getDepartingStation() + " → "  + journey.getArrivingStation());
                        journeyTime.setText(journey.getDepartingTime() + " - " + journey.getArrivingTime());
                        trainDestination.setText(journey.getTrainDestination());
                        trainService.setText(journey.getTrainService());


                        // journeys.add(journey);

                        if(task.getResult().size() != 0){
                            mLastQueriedDocument = task.getResult().getDocuments().get(0);
                        }

                    }
                }else{

                    toastMessage("Query failed. Please check log messages");
                }

            }
        });


    }

    private void toastMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }



}
