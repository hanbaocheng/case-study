package com.medion.trakttv.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bhan on 7/13/16.
 */
public final class Constants {

    public static final String RECEIVER = "downloadreceiver";
    public static final String REQUSET = "downloadrequest";
    public static final int REQUSET_NUMBER = 10;

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

    // Defines the value for the status in an Intent
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;
}
