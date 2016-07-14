package com.medion.trakttv.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.medion.trakttv.data.MovieInfo;
import com.medion.trakttv.utils.Constants;
import com.medion.trakttv.utils.HttpStatus;
import com.medion.trakttv.utils.HttpUtils;

import java.util.ArrayList;

public class MovieService extends IntentService {

    private static final String TAG = "MovieService";

    public MovieService() {
        super(IntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Gets request date from the incoming Intent
        final ResultReceiver receiver = intent.getParcelableExtra(Constants.RECEIVER);
        final int pageIndex = intent.getIntExtra(Constants.REQUSET_PAGE,0);
        final int pageCount = intent.getIntExtra(Constants.REQUSET_PAGE_COUNT,0);
        final String queryFilter = intent.getStringExtra(Constants.REQUSET_FILTER_QUERY);

        Bundle bundle = new Bundle();

        /*
         tell ui we are gonna fetch data from server
         Status Finished
        */
        receiver.send(Constants.STATUS_FINISHED, bundle);

        ArrayList<MovieInfo> movieInfoList = new ArrayList<MovieInfo>();

        //fetch data from server
        int statusCode = HttpUtils.getInstance().getfromTraktTv(pageIndex, pageCount, queryFilter, movieInfoList);

        //check the status and return to activity
        if (HttpStatus.STATUS_200 == HttpStatus.getEnum(statusCode)) {
            /* Status Finished */
            bundle.putParcelableArrayList(Constants.EXTENDED_DATA_RESULT, movieInfoList);

            // Send query filter to UI to verity if the data is out of date
            bundle.putString(Constants.EXTENDED_DATA_ERROR, queryFilter);

            receiver.send(Constants.STATUS_FINISHED, bundle);
        } else {
            /* Sending error message back to activity */
            bundle.putString(Constants.EXTENDED_DATA_ERROR, HttpStatus.getEnum(statusCode).toString());
            receiver.send(Constants.STATUS_ERROR, bundle);
        }

    }
}
