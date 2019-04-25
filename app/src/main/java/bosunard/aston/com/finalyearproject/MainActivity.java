package bosunard.aston.com.finalyearproject;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.IOException;
import java.util.UUID;

import bosunard.aston.com.finalyearproject.models.ComplaintFormFragment;
import bosunard.aston.com.finalyearproject.models.Contact;
import bosunard.aston.com.finalyearproject.models.Journey;
import bosunard.aston.com.finalyearproject.models.JourneyPlanner;
import bosunard.aston.com.finalyearproject.models.LiveTimeTableItem;
import bosunard.aston.com.finalyearproject.models.LiveTimetable;
import bosunard.aston.com.finalyearproject.models.Timetable;

import static bosunard.aston.com.finalyearproject.Constants.ERROR_DIALOG_REQUEST;
import static bosunard.aston.com.finalyearproject.Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
import static bosunard.aston.com.finalyearproject.Constants.PERMISSIONS_REQUEST_ENABLE_GPS;

public class MainActivity extends AppCompatActivity implements FindJourneyFragment.onFragmentInteractionListener,
                                                                JourneyResultsFragment.onFragmentListInteractionListener,
                                                                AddJourneyFragment.onAddJourneyFragmentInteractionListener,
                                                                TrackJourneyFragment.onTrackJourneyFragmentInteractionListener,
                                                                MapFragment.onMapFragmentInteractionListener,
                                                                UpdatesFragment.onUpdatesFragmentListInteractionListener,
                                                                ShareJourneyFragment.onShareFragmentListInteractionListener,
                                                                UserAccountFragment.onUserAccountFragmentInteractionListener,
                                                                HelpListFragment.onHelpListFragmentInteractionListener,
                                                                HelpCategoryFragment.onHelpCategoryListFragmentInteractionListener,
                                                                HelpContactPreferenceFragment.OnHelpContactPreferenceListFragmentInteractionListener,
                                                                HelpLiveChatFragment.onHelpLiveChatFragmentInteractionListener,
                                                                PoliceHelpCategoryFragment.onPoliceHelpCategoryListFragmentInteractionListener,
                                                                PoliceHelpContactPreferenceFragment.OnPoliceHelpContactPreferenceFragmentInteractionListener,
                                                                AmbulanceHelpCategoryFragment.onAmbulanceHelpCategoryFragmentInteractionListener,
                                                                ContactListFragment.onContactListFragmentInteractionListener,
                                                                EmergencyContactsListFragment.onEmergencyContactsListFragmentInteractionListener,
                                                                AllContactsListFragment.onAllContactsListFragmentInteractionListener,
                                                                ComplaintFormFragment.OnComplaintFormFragmentInteractionListener
{

//    private FirebaseAuth mAuth;
//    private Button signInButton;
//    private EditText mEmail,mPassword;

    public static final String TAG = "MainActivity";

    private boolean mLocationPermissionGranted = false;

    private FloatingActionMenu mFloatingActionMenu;
    private FloatingActionButton mFloatingActionCameraButton,mFloatingActionAudioButton,mFloatingActionAlarmButton;

    private MediaRecorder mMediaRecorder;
    private MediaPlayer mMediaMediaPlayer;

    private String pathSave;

    final int REQUEST_PERMISSION_CODE = 1000;

    private boolean doubleClick = false;

    private Handler doubleHandler;
    private static final long DOUBLE_PRESS_INTERVAL = 250; // in millis
    private long lastPressTime;

    @Override
    protected void onStart() {
        super.onStart();

//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mAuth = FirebaseAuth.getInstance();
//        signInButton = (Button) findViewById(R.id.sign_in_button);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        mFloatingActionMenu = findViewById(R.id.floating_action_menu);
        mFloatingActionAlarmButton = findViewById(R.id.alarm_button);
        mFloatingActionAudioButton = findViewById(R.id.audio_button);
        mFloatingActionCameraButton = findViewById(R.id.camera_button);


        mFloatingActionCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });

        mFloatingActionAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                try {
//                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//                    r.play();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,1500);
            }
        });


//        mFloatingActionAudioButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                doubleClick = false;
//                long pressTime = System.currentTimeMillis();
//
//               // if (doubleClick == false) {
//                if (!(pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL)) {
//
//                    recordAudio();
//                    mFloatingActionAudioButton.setImageResource(R.drawable.ic_stop);
//                    //mFloatingActionAudioButton.setBackgroundColor(Color.WHITE);
//                    doubleClick = false;
//                } else{
//                    doubleClick = true;
//                    mMediaRecorder.stop();
//                   // mFloatingActionAudioButton.setImageResource(R.drawable.ic_mic_black_24dp);
//                   // mFloatingActionAudioButton.setBackgroundColor(Color.WHITE);
//
//                     doubleHandler = new Handler() {
//                        public void handleMessage(Message m) {
//                            if (!doubleClick) {
//                                Toast.makeText(getApplicationContext(), "Stopping Audio Recording", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    };
//                    Message m = new Message();
//                    doubleHandler.sendMessageDelayed(m,DOUBLE_PRESS_INTERVAL);
//
//                }
//
//                // record last time button was pressed
//                lastPressTime = pressTime;
//
//
//            }

//        });



        FindJourneyFragment fragment = new FindJourneyFragment();
        addFragment(fragment);



//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = mEmail.getText().toString();
//                String password = mPassword.getText().toString();
//
//                if(!email.equals("")&& !password.equals("")){
//                    mAuth.signInWithEmailAndPassword(email,password);
//                }else{
//
//                    makeToast("Login with your email address and password");
//                }
//            }
//        });




    }

    private void setupMediaRecorder(){

        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mMediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mMediaRecorder.setOutputFile(pathSave);
    }

    private void playAudio(){

        mMediaMediaPlayer = new MediaPlayer();
        try {
            mMediaMediaPlayer.setDataSource(pathSave);
            mMediaMediaPlayer.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
        mMediaMediaPlayer.start();
        Toast.makeText(MainActivity.this,"Playing audio....",Toast.LENGTH_SHORT);
    }

    private void stopAudio(){

        if(mMediaMediaPlayer!= null){

            mMediaMediaPlayer.stop();
            mMediaMediaPlayer.release();
            setupMediaRecorder();
        }
    }

    private void recordAudio(){

        if (checkPermissionFromDevice()) {

            pathSave = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/" + UUID.randomUUID().toString() + "_audio_record.3gp";

            setupMediaRecorder();
            try {
                mMediaRecorder.prepare();
                mMediaRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Toast.makeText(MainActivity.this, "Recording Audio...", Toast.LENGTH_SHORT).show();
        } else {

            requestPermission();
        }
    }

    private void requestPermission(){

        ActivityCompat.requestPermissions(this,new String[]{

                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
        }, REQUEST_PERMISSION_CODE);
    }



    private boolean checkPermissionFromDevice() {

        int write_external_storage = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int record_audio_result = ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO);

        return write_external_storage == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkMapServices(){
        if(isServicesOK()){
            if(isMapsEnabled()){
                return true;
            }
        }
        return false;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isMapsEnabled(){
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;

         // carry on with application();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }

            case REQUEST_PERMISSION_CODE:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Granted",Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: called.");
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ENABLE_GPS: {
                if(mLocationPermissionGranted){


                   // carry on with application
                }
                else{
                    getLocationPermission();
                }
            }
        }

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


    private void makeToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                   Fragment selectedFragment = null;

                   switch (menuItem.getItemId()){
//                       case R.id.nav_home:
//                           selectedFragment = new HomeFragment();
//                           break;
                       case R.id.nav_find_journey:
                           //selectedFragment = new PickJourneyFragment();
                           selectedFragment = new FindJourneyFragment();
                           //Intent intent = new Intent(MainActivity.this,FindJourneyActivity.class);
                           //startActivity(intent);
                           break;
                       case R.id.support_journey:
                           selectedFragment = new HelpListFragment();
                           break;
                       case R.id.nav_account_details:
                           selectedFragment = new UserAccountFragment();
                           break;
                   }

                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

                   return true;
                }
            };

    @Override
    public void findJourney(String departingStationCode,String arrivingStationCode) {
//
        JourneyResultsFragment fragment = JourneyResultsFragment.newInstance(departingStationCode,arrivingStationCode);
        addFragment(fragment);
    }


    @Override
    public void addJourney(LiveTimeTableItem journey, String departingStationCode, String arrivingStationCode) {
        AddJourneyFragment fragment = AddJourneyFragment.newInstance(journey,departingStationCode,arrivingStationCode);
        addFragment(fragment);
    }



    @Override
    public void trackJourney(Journey journey) {

        TrackJourneyFragment fragment = TrackJourneyFragment.newInstance(journey);
        addFragment(fragment);

    }

    @Override
    public void journeyDetails(String departingStationCode, String arrivingStationCode) {

    }


    @Override
    public void viewContactDetails(Contact contact) {

        ContactDetailsFragment fragment = ContactDetailsFragment.newInstance(contact);
        addFragment(fragment);
    }

    @Override
    public void viewMoreJourneys() {

        JourneyListFragment fragment = JourneyListFragment.newInstance();
        addFragment(fragment);

    }

    @Override
    public void showHelpList() {

        HelpListFragment fragment = HelpListFragment.newInstance();
        addFragment(fragment);
    }

    @Override
    public void showHelpCategory() {

        HelpCategoryFragment fragment = HelpCategoryFragment.newInstance();
        addFragment(fragment);
    }

    @Override
    public void shareJourneyDetails(Journey journey) {
        ShareJourneyFragment fragment = ShareJourneyFragment.newInstance(journey);
        addFragment(fragment);
    }
}
