package bosunard.aston.com.finalyearproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class JourneyListFragment extends Fragment {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager jViewPager;

    private onJourneyListFragmentInteractionListener mListener;

    public JourneyListFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_journey_list,container,false);

       // mSectionsPageAdapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());

        TabLayout tabLayout = view.findViewById(R.id.journey_tabs);
        //tabLayout.setupWithViewPager(jViewPager);



        jViewPager = (ViewPager) view.findViewById(R.id.view_pager);


        //setupViewPager(jViewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        viewPagerAdapter.addFragment(new CurrentJourneyFragment(), "CURRENT");
        viewPagerAdapter.addFragment(new PastJourneyListFragment(), "PAST");

        jViewPager.setAdapter(viewPagerAdapter);


        tabLayout.setupWithViewPager(jViewPager);


        return view;
    }

    private void  setupViewPager(ViewPager viewPager){

        this.jViewPager = viewPager;

        SectionsPageAdapter adapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(new CurrentJourneyFragment(),"CURRENT");
        adapter.addFragment(new PastJourneyListFragment(),"PAST");

    }


    public static JourneyListFragment newInstance(){

        Bundle args = new Bundle();
        JourneyListFragment fragment = new JourneyListFragment();
        return fragment;
    }

    public interface onJourneyListFragmentInteractionListener{

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {


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
