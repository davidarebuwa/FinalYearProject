package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import bosunard.aston.com.finalyearproject.models.Contact;

public class ContactsAccountListAdapter extends RecyclerView.Adapter<ContactsAccountListAdapter.ContactsListViewHolder> {

    private List<Contact> contactsList;
    private Context mContext;


    public ContactsAccountListAdapter(List<Contact> contactsList, Context mContext) {
        this.contactsList = contactsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ContactsAccountListAdapter.ContactsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.account_contacts_list_item,viewGroup,false);


        return new ContactsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAccountListAdapter.ContactsListViewHolder contactsListViewHolder, int i) {

        contactsListViewHolder.contactName .setText(contactsList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ContactsListViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView contactName;


        public ContactsListViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            contactName = itemView.findViewById(R.id.contact_name);
        }
    }
}
