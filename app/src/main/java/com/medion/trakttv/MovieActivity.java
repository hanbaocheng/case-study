package com.medion.trakttv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medion.trakttv.data.MovieInfo;
import com.medion.trakttv.receiver.DownloadResultReceiver;
import com.medion.trakttv.service.MovieService;
import com.medion.trakttv.utils.Constants;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity implements
            MovieListFragment.OnListFragmentInteractionListener,
            DownloadResultReceiver.Receiver{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    //ResultReceiver for receiving the result from MovieService
    private DownloadResultReceiver mDownloadReceiver;

    // The page index of the next fetching
    private int mPageIndex = 1;

    // The query filter
    private String mQueryFilter = "";

    private void requestNextPage() {
        /*
         * Creates a new Intent to start the MovieService
         * IntentService. Passes a request and a ResultReceiver
         * in the Intent's "data" field.
         */
        Intent mServiceIntent = new Intent(this, MovieService.class);
        /* Send optional extras to Download IntentService */
        mServiceIntent.putExtra(Constants.RECEIVER, mDownloadReceiver);
        mServiceIntent.putExtra(Constants.REQUSET_PAGE, mPageIndex);
        mServiceIntent.putExtra(Constants.REQUSET_PAGE_COUNT, Constants.PAGE_COUNT);
        mServiceIntent.putExtra(Constants.REQUSET_FILTER_QUERY, mQueryFilter);

        // Starts the IntentService
        startService(mServiceIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        /* Starting Download Service */
        mDownloadReceiver = new DownloadResultReceiver(new Handler());
        mDownloadReceiver.setReceiver(this);

        requestNextPage();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0:
                    // Show movie list
                    return mMovieListFragment = MovieListFragment.newInstance(1);
                case 1:
                    // Show the detail information of selected movie
                    return PlaceholderFragment.newInstance(2);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Movie List";
                case 1:
                    return "Movie Detail";
            }
            return null;
        }
    }

    @Override
    public void onListFragmentInteraction(MovieInfo item) {
        // Smooth scroll to detail fragment to show the detail
        mViewPager.setCurrentItem(1,true);
    }

    MovieListFragment mMovieListFragment;
    ArrayList<MovieInfo> mMovieInfoList;

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case Constants.STATUS_RUNNING:
                break;
            case Constants.STATUS_FINISHED:
                ArrayList<MovieInfo> movieInfoList = resultData.getParcelableArrayList(Constants.EXTENDED_DATA_RESULT);

                if (mMovieListFragment != null)
                    mMovieListFragment.updateAdapterDateValues(movieInfoList);

                break;
            case Constants.STATUS_ERROR:
                /* Handle the error */
                String error = resultData.getString(Constants.EXTENDED_DATA_ERROR, "Unknown Error");
                Snackbar.make(mViewPager, error, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
        }
    }

    @Override
    public void onLoadNextPage() {
        /**
         * Send request to MovieService to fetch next page
         */

        // One page successfully returned, so we set the mPageIndex++
        mPageIndex = mPageIndex + 1;

        // Load next page
        requestNextPage();
    }
}
