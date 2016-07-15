package com.medion.trakttv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.medion.trakttv.MovieListFragment.OnListFragmentInteractionListener;
import com.medion.trakttv.data.MovieInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MovieInfo} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MovieItemRecyclerViewAdapter extends RecyclerView.Adapter<MovieItemRecyclerViewAdapter.ViewHolder> {

    private static final int FOOTER_VIEW = 1;

    private final List<MovieInfo> mValues;
    private final OnListFragmentInteractionListener mListener;
    private FooterViewHolder mFooterViewHolder;

    public MovieItemRecyclerViewAdapter(List<MovieInfo> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Create Footer
        if (viewType == FOOTER_VIEW) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movielist_footer, parent, false);
            FooterViewHolder footer = new FooterViewHolder(view);

            // By default, if there is no data, show no content footer
            if (mValues.size() == 0) {
                footer.mProgressBar.setVisibility(View.GONE);
                footer.mNoContent.setVisibility(View.VISIBLE);
            }

            return footer;
        }

        // Create ItemView
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movieitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {

            mFooterViewHolder = (FooterViewHolder)holder;

            mFooterViewHolder.mFooter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Click the footer to query next page, although for most time, this action is useless.
                        // Just in case the network get lost or last request get lost
                        // At this point, to click the footer, we get a anther chance to query next page.
                        mListener.onLoadNextPage();
                    }
                }
            });

        } else if (holder instanceof ViewHolder) {
            holder.mMovieInfo = mValues.get(position);

            //Download image using picasso library
            Picasso.with(holder.mMovieListItem.getContext()).load(holder.mMovieInfo.getThumbnail())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.mThumbnail);

            holder.mTitle.setText(mValues.get(position).getTitle());
            holder.mReleased.setText("Released: " + mValues.get(position).getReleaseyear());
            holder.mOverview.setText(mValues.get(position).getOverview());

            holder.mMovieListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(holder.mMovieInfo);
                    }
                }
            });
        }
    }

    public void insertFeedValues(List<MovieInfo> list){
        if( list != null ){

            /**
             * If list.size() greater than 0, we assume there are more data, otherwise
             * we assume there is no more data, therefor we show TextView instead of the
             * ProgressBar in footer
             */
            if (list.size() != 0) {
                int old_length = mValues.size();

                // Add an item to movie list
                mValues.addAll(list);

                // Notify the adapter that an item inserted
                notifyItemRangeInserted(old_length, list.size());

                // Scroll to newly added item position
//                mRecyclerView.scrollToPosition(list.size());

                mFooterViewHolder.mProgressBar.setVisibility(View.VISIBLE);
                mFooterViewHolder.mNoContent.setVisibility(View.GONE);
            } else {
                mFooterViewHolder.mProgressBar.setVisibility(View.GONE);
                mFooterViewHolder.mNoContent.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * Now the critical part. You have return the exact item count of your list
     * I've only one footer. So I returned data.size() + 1
     * If you've multiple headers and footers, you've to return total count like, headers.size() + data.size() + footers.size()
     * */
    @Override
    public int getItemCount() {

        if (mValues == null) {
            return 0;
        }

        if (mValues.size() == 0) {
            //Return 1 here to show nothing
            return 1;
        }

        // Add extra view to show the footer view
        return mValues.size() + 1;
    }

    // Now define getItemViewType.
    @Override
    public int getItemViewType(int position) {
        if (position == mValues.size()) {
            // This is where we'll add footer.
            return FOOTER_VIEW;
        }

        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mMovieListItem;
        public final ImageView mThumbnail;
        public final TextView mTitle;
        public final TextView mReleased;
        public final TextView mOverview;

        public MovieInfo mMovieInfo;

        public ViewHolder(View view) {
            super(view);
            mMovieListItem = view;
            mThumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            mTitle = (TextView) view.findViewById(R.id.title);
            mReleased = (TextView) view.findViewById(R.id.released);
            mOverview = (TextView) view.findViewById(R.id.overview);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }

    // Define a view holder for Footer view

    public class FooterViewHolder extends ViewHolder {
        public final View mFooter;
        public final ProgressBar mProgressBar;
        public final TextView mNoContent;

        public FooterViewHolder(View view) {
            super(view);
            mFooter = view;
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
            mNoContent = (TextView) view.findViewById(R.id.noContent);
        }

    }
}
