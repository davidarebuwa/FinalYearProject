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
import android.widget.TextView;
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

import bosunard.aston.com.finalyearproject.models.Contact;
import bosunard.aston.com.finalyearproject.models.Journey;
import bosunard.aston.com.finalyearproject.models.LiveTimeTableItem;

public class ShareJourneyFragment extends Fragment {

    private List<Contact> contacts;

    private Journey journey;

//    private String departingStationCode;
//    private String arrivingStationCode;

    private TextView departingStation;
    private TextView arrivingStation;



    private onShareFragmentListInteractionListener mListener;

    private DocumentSnapshot mLastQueriedDocument;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  journey = (Journey) getArguments().getSerializable("JOURNEY");
//        departingStationCode = (String) getArguments().getSerializable("DEPARTING");
//        arrivingStationCode = (String) getArguments().getSerializable("DESTINATION");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share_journey, container, false);

         departingStation = view.findViewById(R.id.departing_station);
         arrivingStation = view.findViewById(R.id.destination_station);

//        departingStation.setText(journey.getDepartingStation());
//        arrivingStation.setText(journey.getArrivingStation());

        fillContactsList();

        RecyclerView contactsList = (RecyclerView)view.findViewById(R.id.contacts_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        ContactsAdapter adapter = new ContactsAdapter(getContext(),contacts);

        contactsList.setAdapter(adapter);

        contactsList.setLayoutManager(layoutManager);

        return view;
    }


    public static ShareJourneyFragment newInstance(Journey journey){

        Bundle args = new Bundle();
        args.putSerializable("JOURNEY", journey);
        //args.putSerializable("DESTINATION", arrivingStationCode);
        ShareJourneyFragment fragment = new ShareJourneyFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
         getJourneys();
    }

    private void getJourneys(){

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

                         departingStation.setText(journey.getDepartingStation());
                         arrivingStation.setText(journey.getArrivingStation());

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

    private void fillContactsList(){

        contacts = new ArrayList<>();
        contacts.add(new Contact("Ben","00022220"));
    }


    public interface onShareFragmentListInteractionListener{}
}
