package bosunard.aston.com.finalyearproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bosunard.aston.com.finalyearproject.models.Journey;

public class TrackJourneyFragment extends Fragment {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    private onTrackJourneyFragmentInteractionListener mListener;

    private Journey journey;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        journey = (Journey) getArguments().getSerializable("JOURNEY");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_journey, container, false);

        //mSectionsPageAdapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());



        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        viewPagerAdapter.addFragment(new MapFragment(), "LIVE");
        viewPagerAdapter.addFragment(new UpdatesFragment(), "UPDATES");
        viewPagerAdapter.addFragment(new ShareJourneyFragment(), "SHARE");

        mViewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);


        return view;

    }


    private void setupViewPager(ViewPager viewPager) {

        this.mViewPager = viewPager;

       // SectionsPageAdapter adapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());

//        adapter.addFragment(new MapFragment(), "LIVE");
//        adapter.addFragment(new UpdatesFragment(), "UPDATES");
//        adapter.addFragment(new ShareJourneyFragment(), "SHARE");
    }

    public static TrackJourneyFragment newInstance(Journey journey) {

        Bundle args = new Bundle();
        args.putSerializable("JOURNEY", journey);
        TrackJourneyFragment fragment = new TrackJourneyFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public interface onTrackJourneyFragmentInteractionListener {

        void shareJourneyDetails(Journey journey);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter{


        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm){

            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title){

            fragments.add(fragment);
            titles.add(title);
        }

        //

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
