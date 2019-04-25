package bosunard.aston.com.finalyearproject;

import android.app.TimePickerDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FindJourneyActivity extends AppCompatActivity {

    private List<AutoCompleteItem> resultList;
  //  private List<AutoCompleteItem> timeList;
    private EditText departingTime;
    private TimePickerDialog timePickerDialog;
    private Button submitButton;
   // private StateProgressBar progressBar;

    private String[] descriptionData = {"Find", "Select", "Add"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_find_journey);

        //fillResultList();
       // fillTimeList();



//        submitButton = findViewById(R.id.submit_button);
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                makeToast("Journey Submitted");
//
//                addFragment(new JourneyResultsFragment());
//
//            }
//        });


      //  departingTime.setAdapter(adapter);
    }


    //Toast Message Maker
    private void makeToast(String message){
        Toast.makeText(FindJourneyActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    private void addFragment(Fragment newFragment) {
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);


        // Commit the transaction
        transaction.commit();
    }
}
