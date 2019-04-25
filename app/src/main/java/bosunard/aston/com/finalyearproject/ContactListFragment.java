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

public class ContactListFragment extends Fragment {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    private onContactListFragmentInteractionListener mListener;

    public ContactListFragment() { }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts_list,container,false);

        TabLayout tabLayout = view.findViewById(R.id.contact_tabs);

        mViewPager = (ViewPager) view.findViewById(R.id.contacts_view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        viewPagerAdapter.addFragment(new EmergencyContactsListFragment(), "EMERGENCY");
        viewPagerAdapter.addFragment(new AllContactsListFragment(), "ALL");

        mViewPager.setAdapter(viewPagerAdapter);


        tabLayout.setupWithViewPager(mViewPager);


        return view;
    }

    private void  setupViewPager(ViewPager viewPager){

        this.mViewPager = viewPager;

        SectionsPageAdapter adapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(new EmergencyContactsListFragment(),"EMERGENCY");
        adapter.addFragment(new AllContactsListFragment(),"ALL");

    }


    public static JourneyListFragment newInstance(){

        Bundle args = new Bundle();
        JourneyListFragment fragment = new JourneyListFragment();
        return fragment;
    }


    public interface onContactListFragmentInteractionListener{

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
