package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bosunard.aston.com.finalyearproject.models.Message;
import bosunard.aston.com.finalyearproject.models.User;

public class HelpLiveChatFragment extends Fragment {


    FirebaseUser user;
    DatabaseReference reference;

    ImageButton btnSend;
    ImageButton btnSendImage;
    EditText textSend;

    MessageAdapter mAdapter;
    List<Message> messages;

    RecyclerView messageRecyclerView;

    Intent intent;

    ImageView imageView;

    private onHelpLiveChatFragmentInteractionListener mListener;

    public HelpLiveChatFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.activity_message, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.cToolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        messageRecyclerView = view.findViewById(R.id.messages);
        messageRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        messageRecyclerView.setLayoutManager(linearLayoutManager);


        btnSend = view.findViewById(R.id.btnSend);
        textSend = view.findViewById(R.id.text_send);
        btnSendImage = view.findViewById(R.id.photo_btn);

        intent = getActivity().getIntent();

        //person message will be sent to -- in this case the operator
        final String userId = intent.getStringExtra("userid");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = textSend.getText().toString();
                if(!msg.equals("")){
                   // sendMessage(user.getUid(),userId,msg);
                }else{

                    Toast.makeText(getContext(),"You cannot send empty messages",Toast.LENGTH_SHORT).show();

                    }
                    textSend.setText("");
            }
        });

        //user = FirebaseAuth.getInstance().getCurrentUser();
        //reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                User user = dataSnapshot.getValue(User.class);
//
//
//
//                readMessages(user.getId(),userId,user.getImageURL());
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        btnSendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getActivity().startActivityForResult(intent,0);
            }
        });

       return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }

    private void sendMessage(String sender, String receiver, String message){

       // final String userId = intent.getStringExtra("userid");

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//
//        HashMap<String,Object> hashMap = new HashMap<>();
//        hashMap.put("sender",sender);
//        hashMap.put("receiver",receiver);
//        hashMap.put("message",message);
//
//
//        reference.child("Chats").push().setValue(hashMap);
////
//        //add user to chat list fragment
//        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chatlist").child(user.getUid()).child(userId);
//        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(!dataSnapshot.exists()){
//                    chatRef.child("id").setValue(userId);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    private void readMessages(final String myId, final String userId, final String imageUrl){

        messages = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                messages.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Message message = snapshot.getValue(Message.class);

                    if(message.getSender().equals(userId)
                            ){

                        messages.add(message);
                    }

                    mAdapter = new MessageAdapter(getContext(),messages,imageUrl);
                    messageRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof onHelpLiveChatFragmentInteractionListener){

            mListener = (onHelpLiveChatFragmentInteractionListener) context;

        }else{

            throw new RuntimeException(context.toString() + "must implement onFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    public static HelpLiveChatFragment newInstance(){

        Bundle args = new Bundle();
        HelpLiveChatFragment fragment = new HelpLiveChatFragment();
        return fragment;
    }

    public interface onHelpLiveChatFragmentInteractionListener{

    }


}
