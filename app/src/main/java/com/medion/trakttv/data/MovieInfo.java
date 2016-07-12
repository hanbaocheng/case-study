package com.medion.trakttv.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bhan on 7/12/16.
 */
public class MovieInfo implements Parcelable {
    private String mThumbnail;
    private String mTitle;
    private String mReleaseyear;
    private String mOverview;

    public MovieInfo() {
        super();
    }

    public MovieInfo(String mThumbnail, String mTitle, String mReleaseyear, String mOverview) {
        this.mThumbnail = mThumbnail;
        this.mTitle = mTitle;
        this.mReleaseyear = mReleaseyear;
        this.mOverview = mOverview;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getReleaseyear() {
        return mReleaseyear;
    }

    public void setReleaseyear(String mReleaseyear) {
        this.mReleaseyear = mReleaseyear;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieInfo movieInfo = (MovieInfo) o;

        if (!mThumbnail.equals(movieInfo.mThumbnail)) return false;
        if (!mTitle.equals(movieInfo.mTitle)) return false;
        if (!mReleaseyear.equals(movieInfo.mReleaseyear)) return false;
        return mOverview.equals(movieInfo.mOverview);

    }

    @Override
    public int hashCode() {
        int result = mThumbnail.hashCode();
        result = 31 * result + mTitle.hashCode();
        result = 31 * result + mReleaseyear.hashCode();
        result = 31 * result + mOverview.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(mThumbnail);
        dest.writeString(mTitle);
        dest.writeString(mReleaseyear);
        dest.writeString(mOverview);
    }

    public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {

        @Override
        public MovieInfo createFromParcel(Parcel source) {
            MovieInfo info = new MovieInfo();
            info.mThumbnail = source.readString();
            info.mTitle = source.readString();
            info.mReleaseyear = source.readString();
            info.mOverview = source.readString();
            return info;
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };
}
