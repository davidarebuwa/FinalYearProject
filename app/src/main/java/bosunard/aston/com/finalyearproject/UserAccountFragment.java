package bosunard.aston.com.finalyearproject;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bosunard.aston.com.finalyearproject.models.Contact;
import bosunard.aston.com.finalyearproject.models.Journey;
import bosunard.aston.com.finalyearproject.models.User;

import static android.app.Activity.RESULT_OK;

public class UserAccountFragment extends Fragment {

    private ImageView profileImage;
    private TextView username;

    private List<Contact> contacts;
    private List<Journey> journeys;

    private onUserAccountFragmentInteractionListener mListener;

    private FirebaseUser firebaseUser;
    private DatabaseReference reference;

    private ImageView settingsButton;





    StorageReference storageReference;
    private  static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.user_profile_fragment,container,false);

        profileImage = view.findViewById(R.id.profile_thumbnail);
        username = view.findViewById(R.id.profile_name);

      //  storageReference = FirebaseStorage.getInstance().getReference("uploads");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        username.setText(firebaseUser.getDisplayName());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                username.setText(user.getUsername());

                if(user.getImageURL().equals("default")){
                    profileImage.setImageResource(R.mipmap.ic_launcher);
                }else {

                    Glide.with(getActivity()).load(user.getImageURL()).into(profileImage);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        fillJourneysList();
        fillContactsList();


        RecyclerView journeysList = (RecyclerView) view.findViewById(R.id.journey_list);

        RecyclerView contactsList = (RecyclerView)view.findViewById(R.id.contacts_list);

        LinearLayoutManager jLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager cLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        journeysList.setLayoutManager(jLayoutManager);
        contactsList.setLayoutManager(cLayoutManager);

        JourneyAccountListAdapter jAdapter = new JourneyAccountListAdapter(journeys,getContext());
        journeysList.setAdapter(jAdapter);

        ContactsAccountListAdapter cAdapter = new ContactsAccountListAdapter(contacts,getContext());
        contactsList.setAdapter(cAdapter);

        TextView moreJourneys = (TextView) view.findViewById(R.id.more_journey);
        moreJourneys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JourneyListFragment trackFrag = new JourneyListFragment();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,trackFrag).addToBackStack(null)
                        .commit();

            }
        });

        TextView moreContacts = (TextView) view.findViewById(R.id.more_contacts);
        moreContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContactListFragment trackFrag = new ContactListFragment();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,trackFrag).addToBackStack(null)
                        .commit();

            }
        });



        ImageView helpButton = (ImageView)view.findViewById(R.id.help_icon);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HelpLiveChatFragment trackFrag = new HelpLiveChatFragment();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,trackFrag).addToBackStack(null)
                        .commit();

            }
        });

        settingsButton = (ImageView) view.findViewById(R.id.settings_icon);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(getContext(), settingsButton);

                popup.getMenuInflater().inflate(R.menu.account_popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        FirebaseAuth.getInstance().signOut();
                        getActivity().startActivity(new Intent(getActivity(),StartActivity.class));
                        Toast.makeText(getContext(),"Logging out", Toast.LENGTH_SHORT).show();
                        getActivity().finish();

                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });



//        profileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openImage();
//            }
//        });




        return view;
    }

//    private void openImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//
//
//        startActivityForResult(intent,IMAGE_REQUEST);
//    }
//
//    private String getFileExtension(Uri uri){
//
//        ContentResolver contentResolver = getContext().getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return  mimeTypeMap.getMimeTypeFromExtension(contentResolver.getType(uri));
//
//    }
//
//    private void uploadImage(){
//
//        final ProgressDialog pd  = new ProgressDialog(getContext());
//        pd.setMessage("Uploading...");
//        pd.show();
//
//        if(imageUri != null){
//            final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "."+getFileExtension(imageUri));
//
//            uploadTask = fileReference.putFile(imageUri);
//            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot,Task<Uri>>() {
//                @Override
//                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//
//
//                    if(!task.isSuccessful()){
//                        throw task.getException();
//                    }
//
//                    return fileReference.getDownloadUrl();
//
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                @Override
//                public void onComplete(@NonNull Task<Uri> task) {
//
//                    if(task.isSuccessful()){
//                        Uri downloadUri = task.getResult();
//                        String mUri = downloadUri.toString();
//
//                        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//
//                        HashMap<String, Object> map = new HashMap<>();
//                        map.put("imageURL",mUri);
//                        reference.updateChildren(map);
//
//                        pd.dismiss();
//                    }else {
//
//                        Toast.makeText(getContext(),"Profile photo upload failed",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
//                    pd.dismiss();
//                }
//            });
//        }else{
//
//            Toast.makeText(getContext(),"No image selected",Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
//
//            imageUri = data.getData();
//
//            if(uploadTask != null && uploadTask.isInProgress()){
//                Toast.makeText(getContext(),"Upload in progress",Toast.LENGTH_SHORT).show();
//            }else {
//
//                uploadImage();
//            }
//        }
//    }

    private void fillJourneysList(){

//        journeys = new ArrayList<>();
//        journeys.add(new Journey("12:00","13:00","Euston","Tile Hill","Crosscountry","Sheffield","3","2",3));

    }

    private void fillContactsList(){

        contacts = new ArrayList<>();
        contacts.add(new Contact("Ben","07453883275"));
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

    public interface onUserAccountFragmentInteractionListener{

        void viewContactDetails(Contact contact);
        void viewMoreJourneys();
        void showHelpList();

    }
}
