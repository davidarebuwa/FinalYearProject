package bosunard.aston.com.finalyearproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bosunard.aston.com.finalyearproject.models.StationShort;

public class AutocompleteResultAdapter extends ArrayAdapter<AutoCompleteItem>{


    private List<AutoCompleteItem> itemListFull;
  //  private List<StationShort> stationList;

    public AutocompleteResultAdapter(@NonNull Context context, @NonNull List<AutoCompleteItem> itemList) {
        super(context, 0, itemList);



        itemListFull = new ArrayList<>(itemList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return stationFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.journey_autocomplete_row,parent,false);
        }
        TextView stationName = convertView.findViewById(R.id.result_name);

       // StationShort station = new StationShort();

        AutoCompleteItem autoCompleteItem = getItem(position);

        if(autoCompleteItem != null){


            stationName.setText(autoCompleteItem.getResultName());
        }
        return convertView;
    }

    private Filter stationFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            List<AutoCompleteItem> suggestions = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                suggestions.addAll(itemListFull);
            }else{

                String filterPattern = constraint.toString().toLowerCase().trim();

                for(AutoCompleteItem item : itemListFull){

                    if(item.getResultName().toLowerCase().contains(filterPattern)){

                        suggestions.add(item);
                    }

                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            clear();

            addAll((List)results.values);

            notifyDataSetChanged();

        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((AutoCompleteItem) resultValue).getResultName();
        }
    };
}
