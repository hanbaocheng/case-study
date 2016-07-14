package com.medion.trakttv.utils;

/**
 * Created by bhan on 7/13/16.
 */
public final class Constants {

    public static final String RECEIVER = "downloadreceiver";
    public static final String REQUSET_PAGE = "request_page";
    public static final String REQUSET_PAGE_COUNT = "request_page_count";
    public static final int PAGE_COUNT = 10;
    public static final String REQUSET_FILTER_QUERY = "request_filter_query";

    // Defines a custom Intent action
    public static final String BROADCAST_ACTION =
            "com.medion.trakttv.downloadreceiver.BROADCAST";

    // Defines the key for the status in an Intent
    public static final String EXTENDED_DATA_STATUS =
            "com.medion.trakttv.downloadreceiver.STATUS";

    // Defines the key for the result in an Intent
    public static final String EXTENDED_DATA_RESULT =
            "com.medion.trakttv.downloadreceiver.RESULT";

    // Defines the key for the error in an Intent
    public static final String EXTENDED_DATA_ERROR =
            "com.medion.trakttv.downloadreceiver.ERROR";

    // Defines the key for the token in an Intent
    // UI use this property to verify the return data
    // in case there are outdated data not being showing
    public static final String EXTENDED_DATA_TOKEN =
            "com.medion.trakttv.downloadreceiver.TOKEN";

    // Defines the value for the status in an Intent
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;
}
