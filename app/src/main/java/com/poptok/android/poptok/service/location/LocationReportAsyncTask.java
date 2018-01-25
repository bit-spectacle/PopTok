package com.poptok.android.poptok.service.location;

import android.os.AsyncTask;
import android.util.Log;

import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.location.LocationLocalLog;
import com.poptok.android.poptok.service.localDB.LocalDBOpenHelper;

import java.util.List;

public class LocationReportAsyncTask extends AsyncTask<Integer, String, JSONResult<Integer>> {
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
    protected JSONResult<Integer> doInBackground(Integer... userNo) {
        JSONResult<Integer> result = locationReporter.reportLocation(userNo[0], logList);
        return result;
    }

    @Override
    protected void onPostExecute(JSONResult<Integer> result) {
        Log.d(TAG, String.format("%s %s", result.getCode(), result.getMessage()));
        super.onPostExecute(result);

        if(result.getCode().equals("SUCC")) {
            int rmResult = db.remove(logList);
            Log.d(TAG, String.format("size:%d result:%d", logList.size(), rmResult));
        }
    }
}
