package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import bosunard.aston.com.finalyearproject.models.Message;
import bosunard.aston.com.finalyearproject.models.User;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private String imageUrl;
    private Context mContext;
    private List<Message> mMessages;

    FirebaseUser fUser;

    public MessageAdapter(Context mContext, List<Message> mMessages, String imageUrl) {
        this.mContext = mContext;
        this.mMessages = mMessages;
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, viewGroup, false);

            return new MessageViewHolder(view);
        }else {

            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_items_left, viewGroup, false);

            return new MessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, int i) {

        Message msg = mMessages.get(i);

        messageViewHolder.show_message.setText(msg.getMessage());

        if(imageUrl.equals("default")){

            messageViewHolder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(mContext).load(imageUrl).into(messageViewHolder.profile_image);
        }

    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView show_message;
        public ImageView profile_image;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            show_message = (TextView)itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }


    @Override
    public int getItemViewType(int position) {

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mMessages.get(position).getSender().equals(fUser.getUid())){
            return MSG_TYPE_RIGHT;
        }else{
            return MSG_TYPE_LEFT;
        }
    }
}
