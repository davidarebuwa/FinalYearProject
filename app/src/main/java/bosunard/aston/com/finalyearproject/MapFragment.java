package bosunard.aston.com.finalyearproject;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.List;

import bosunard.aston.com.finalyearproject.models.JourneyPlanner;
import bosunard.aston.com.finalyearproject.models.JourneyPlannerUtility;
import bosunard.aston.com.finalyearproject.models.LiveTimetable;
import bosunard.aston.com.finalyearproject.models.RouteParts;
import bosunard.aston.com.finalyearproject.models.Routes;
import bosunard.aston.com.finalyearproject.models.Stops;
import bosunard.aston.com.finalyearproject.models.Timetable;
import bosunard.aston.com.finalyearproject.models.User;
import bosunard.aston.com.finalyearproject.models.UserClient;
import bosunard.aston.com.finalyearproject.models.UserLocation;

import static bosunard.aston.com.finalyearproject.Constants.ERROR_DIALOG_REQUEST;
import static bosunard.aston.com.finalyearproject.Constants.MAPVIEW_BUNDLE_KEY;
import static bosunard.aston.com.finalyearproject.Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
import static bosunard.aston.com.finalyearproject.Constants.PERMISSIONS_REQUEST_ENABLE_GPS;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "MapFragment";

    private onMapFragmentInteractionListener mListener;

    private MapView mMapView;

    private GoogleMap map;
    private LatLngBounds mMapBoundary;

    private FusedLocationProviderClient mFusedLocationClient;

    private boolean mLocationPermissionGranted = false;

    private FirebaseFirestore mDb;
    private UserLocation mUserLocation;

    private float zoom;


    private LatLngBounds mBoundary;
   // private List<Routes> routes;

   // private LiveTimetable mTimetable;
    //private JourneyPlanner routePlan;
   // private Timetable stopsList;
   // private ArrayList<Coordinates>stopCoordinates;
    //private ArrayList<Stops> stopsInfo;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        routePlan = (JourneyPlanner) getArguments().getSerializable("ROUTEDATA");
  //      stopsList = (Timetable) getArguments().getSerializable("STOPSLIST");

//        //TODO: Add coordinates of routes into list
//        if (stopCoordinates.size() == 0) {
//            if (getArguments() != null) {
//
//                for (int i = 0; i < routePlan.getRoutes().size(); i++) {
//
//                    List<Coordinates> stops = routePlan.getRoutes().get(i).route_parts.get(i).getCoordinates();
//
//                    for (Coordinates stop : stops) {
//
//                        stopCoordinates.add(stop);
//                    }
//                }
//            }
//
//
//        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_map, container, false);

        // initGoogleMap(savedInstanceState);


        //mMapView = (MapView) view.findViewById(R.id.mapView);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().
                findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mDb = FirebaseFirestore.getInstance();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());


        return view;
    }

    private void setStopPositions(){



    }

    private void getUserDetails(){

        if(mUserLocation == null){ //Retrieve user details from Firestore
            mUserLocation = new UserLocation();

            DocumentReference userRef = mDb. collection(getString(R.string.collection_users))
                    .document(FirebaseAuth.getInstance().getUid());

            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){}
                    Log.d(TAG,"onComplete: successfully get the user details");

                    User user = task.getResult().toObject(User.class);
                    mUserLocation.setUser(user);
                    ((UserClient)getActivity().getApplicationContext()).setUser(user);
                    getLastKnownLocation();

                }
            });
        } else {

            getLastKnownLocation();


            //just get the location by calling getLastKnownLocation method
        }
    }

    private void saveUserLocation(){

        if(mUserLocation != null){
            DocumentReference locationRef = mDb.collection(getString(R.string.collection_user_locations)).
                    document(FirebaseAuth.getInstance().getUid());

            locationRef.set(mUserLocation).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d(TAG,"saveUserLocation(): inserted user location into database" +
                    "\n latitude:" + mUserLocation.getGeo_point().getLatitude() +
                    "\n longitude: " + mUserLocation.getGeo_point().getLongitude());
                }
            });
        }
    }

    private void getLastKnownLocation() {

        Log.d(TAG, "getLastKnownLocation() called");

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                if(task.isSuccessful()){

                    Location location = task.getResult();
                    GeoPoint geoPoint = new GeoPoint(location.getLatitude(),location.getLongitude());

                    Log.d(TAG,"onComplete: latitude " + geoPoint.getLatitude());
                    Log.d(TAG,"onComplete: longitude " + geoPoint.getLongitude());

                    mUserLocation.setGeo_point(geoPoint);
                    mUserLocation.setTimestamp(null);

                    saveUserLocation();
                }
            }
        });
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getContext().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;

            //getUserDetails();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
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
        }
    }

    private void initGoogleMap(Bundle savedInstanceState) {

        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);


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
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Permission to enable GPS?")
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
        final LocationManager manager = (LocationManager) getActivity().getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(getContext(), "Map request denied", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void getStationsLatLngRequest(){

//      String url =  "https://transportapi.com/v3/uk/places.json?app_id=2ed72192" +
//                    "&app_key=8f82c455f34e9bc13a29571912d6920a&lat=52.3555&lon=1.1743" +
//                    "&query=birmingham&type=train_station,tube_station";
//
//        PlacesReadFeed process = new PlacesReadFeed();
//        process.execute(new String[]{url});


    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
//        if (mapViewBundle == null) {
//            mapViewBundle = new Bundle();
//            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
//        }
//
//        mMapView.onSaveInstanceState(mapViewBundle);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//       // mMapView.onResume();
//        if(checkMapServices()){
//            if(mLocationPermissionGranted){
//              //  getUserDetails();
//            }else {
//                getLocationPermission();
//            }
//        }
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mMapView.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mMapView.onStop();
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        LatLng defaultLocation = new LatLng(52.477658,-1.898667);
        map.addMarker(new MarkerOptions().position(defaultLocation).title("My Location")
                .snippet("Next Stop:")
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));


        );
        map.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation));
//        map.addMarker(new MarkerOptions().position(new LatLng(52.477658, -1.898667)).title("Marker"));
//        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

//        }
//        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

         inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.map_options, menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public void onPause() {
//        mMapView.onPause();
//        super.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        mMapView.onDestroy();
//        super.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mMapView.onLowMemory();
//    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode){
//            case PERMISSIONS_REQUEST_ENABLE_GPS: {
//               if(mLocationPermissionGranted){
//                  // getUserDetails();
//               } else {
//                   getLocationPermission();
//               }
//            }
//        }
//    }

    public interface onMapFragmentInteractionListener{

    }

    protected void reportBack(JourneyPlanner stations) {

       // this.routes.addAll(stations.getRoutes());

        // tell adapter we have data.
        Log.i(TAG,  stations.getRoutes().size() + " stations found.");

     //   resultsAdapter.notifyDataSetChanged();
    }



    //------------------------------------TransportAPI-------------------------------------------//

//    private class PlacesReadFeed extends AsyncTask<String, Void, JourneyPlanner> {
//        //private final ProgressDialog dialog = new ProgressDialog();
//
//        @Override
//        protected JourneyPlanner doInBackground(String... urls) {
//            try {
//                String referer = null;
//                //dialog.setMessage("Fetching Journey Data");
//                if (urls.length == 1) {
//                    referer = null;
//                } else {
//                    referer = urls[1];
//                }
//                JourneyPlanner stations = JourneyPlannerUtility.planJourney(urls[0], referer);
//
//                Log.i(TAG, "Number of stations found " + stations.getRoutes().size());
//                return stations;
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.i(TAG, e.getMessage());
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            //this.dialog.show();
//            Log.i(TAG, "Getting stations...");
//        }
//
//        @Override
//        protected void onPostExecute(JourneyPlanner stations) {
//            //this.dialog.dismiss();
//            Log.i(TAG, "Got stations...");
//          //  reportBack(stations);
//        }
//
//
//    }

}
