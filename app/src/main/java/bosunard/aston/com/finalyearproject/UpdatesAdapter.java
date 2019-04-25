package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bosunard.aston.com.finalyearproject.models.Updates;

public class UpdatesAdapter extends RecyclerView.Adapter<UpdatesAdapter.UpdatesViewHolder> {

    private List<Updates> mUpdates;

    private Context mContext;

    public UpdatesAdapter(Context mContext,List<Updates> mUpdates) {
        this.mUpdates = mUpdates;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UpdatesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.update_list_item,viewGroup,false);


        return new UpdatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdatesViewHolder updatesViewHolder, int i) {

        updatesViewHolder.thumbnail.setImageResource(mUpdates.get(i).getThumbnail());
        updatesViewHolder.trainServiceName.setText(mUpdates.get(i).getTrainServiceName());
        updatesViewHolder.comment.setText(mUpdates.get(i).getComment());

    }

    @Override
    public int getItemCount() {
        return mUpdates.size();
    }

    public class UpdatesViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView thumbnail;
        TextView trainServiceName;
        TextView comment;

        public UpdatesViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            thumbnail = itemView.findViewById(R.id.train_service_thumbnail);
            trainServiceName = itemView.findViewById(R.id.train_service_name);
            comment = itemView.findViewById(R.id.update_comment);
        }
    }
}
