package bosunard.aston.com.finalyearproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

public class LiveJourneyFragment extends Fragment {

    private MapView mapView;
    private GoogleMap map;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_journey, container, false);


        ImageView btnMap = (ImageView) view.findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                MapFragment mMapFragment = MapFragment.newInstance();
                bosunard.aston.com.finalyearproject.MapFragment mapFragment = new bosunard.aston.com.finalyearproject.MapFragment();
               FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
               fragmentTransaction.replace(R.id.fragment_container,mapFragment);
//                fragmentTransaction.commit();


            }
        });

        return view;
    }
}
