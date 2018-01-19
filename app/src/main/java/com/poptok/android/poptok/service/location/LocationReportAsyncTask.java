package com.poptok.android.poptok.service.location;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.poptok.android.poptok.model.ApiResult;
import com.poptok.android.poptok.model.location.LocationLocalLog;
import com.poptok.android.poptok.service.localDB.LocalDBOpenHelper;

import java.util.Arrays;
import java.util.List;

public class LocationReportAsyncTask extends AsyncTask<Integer, String, ApiResult> {
    private static final String TAG = "LocationReportAsyncTask";

    LocalDBOpenHelper db;
    ILocationReporter locationReporter;
    List<LocationLocalLog> logList;

    public LocationReportAsyncTask(LocalDBOpenHelper db, List<LocationLocalLog> logList, ILocationReporter locationReporter) {
        this.db = db;
        this.logList = logList;
        this.locationReporter = locationReporter;
    }

    @Override
    protected ApiResult doInBackground(Integer... userNo) {
        ApiResult result = locationReporter.reportLocation(userNo[0], logList);
        return result;
    }

    @Override
    protected void onPostExecute(ApiResult result) {
        Log.d(TAG, String.format("%s %s", result.getCode(), result.getMsg()));
        super.onPostExecute(result);

        if(result.getCode().equals("SUCC")) {
            int rmResult = db.remove(logList);
            Log.d(TAG, String.format("size:%d result:%d", logList.size(), rmResult));
        }
    }
}
