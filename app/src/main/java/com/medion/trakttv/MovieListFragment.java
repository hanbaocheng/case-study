package com.medion.trakttv;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medion.trakttv.data.MovieInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MovieListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener = null;

    private MovieItemRecyclerViewAdapter mMovieItemRecyclerViewAdapter = null;

    ArrayList<MovieInfo> mMovieInfoList;

    RecyclerView mRecyclerView;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MovieListFragment newInstance(int columnCount) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    /**
     * Set new Adapter
     * @param movieItemRecyclerViewAdapter
     */
    public void setAdapter(MovieItemRecyclerViewAdapter movieItemRecyclerViewAdapter){
        if (mRecyclerView != null) {
            mMovieItemRecyclerViewAdapter = movieItemRecyclerViewAdapter;
            mRecyclerView.setAdapter(movieItemRecyclerViewAdapter);

            if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {

                final LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();

                mRecyclerView.addOnScrollListener(new InfiniteRecyclerViewScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int page) {

                        // Load next page
                        mListener.onLoadNextPage();
                    }
                });
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movieitem_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            mRecyclerView = recyclerView;

            // Set LayoutManager, could be LinearLayoutManager or GridLayoutManager
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            // Set the adapter
            mMovieItemRecyclerViewAdapter = new MovieItemRecyclerViewAdapter(new ArrayList<MovieInfo>(), mListener);
            setAdapter(mMovieItemRecyclerViewAdapter);
/*            recyclerView.setAdapter(mMovieItemRecyclerViewAdapter);

            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager){

                final LinearLayoutManager layoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();

                recyclerView.addOnScrollListener(new InfiniteRecyclerViewScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int page) {

                        // Load next page
                        mListener.onLoadNextPage();
                    }
                });
            }*/

        }
        return view;
    }

    public void updateAdapterDateValues(List<MovieInfo> movieDataList)
    {
        mMovieItemRecyclerViewAdapter.insertFeedValues(movieDataList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(MovieInfo item);

        // Load next Page
        void onLoadNextPage();
    }
}
