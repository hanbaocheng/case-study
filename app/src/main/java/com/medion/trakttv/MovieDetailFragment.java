package com.medion.trakttv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.medion.trakttv.data.MovieInfo;
import com.squareup.picasso.Picasso;

/**
 * Created by bhan on 7/15/16.
 */
public class MovieDetailFragment extends Fragment{

    private ImageView mThumbnail;
    private TextView mTitle;
    private TextView mReleased;
    private TextView mOverview;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.action_search);
        item.setVisible(false);
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MovieDetailFragment newInstance() {
        MovieDetailFragment fragment = new MovieDetailFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

          mThumbnail = (ImageView) rootView.findViewById(R.id.thumbnail);
          mTitle = (TextView) rootView.findViewById(R.id.title);
          mReleased = (TextView) rootView.findViewById(R.id.released);
          mOverview = (TextView) rootView.findViewById(R.id.overview);

        return rootView;
    }

    public void updateMovieDetail(MovieInfo movieInfo){
        if (movieInfo != null){
            //Download image using picasso library
            Picasso.with(getContext()).load(movieInfo.getThumbnail())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(mThumbnail);

            mTitle.setText(movieInfo.getTitle());
            mReleased.setText("Released: " + movieInfo.getReleaseyear());
            mOverview.setText(movieInfo.getOverview());
        }

    }
}
