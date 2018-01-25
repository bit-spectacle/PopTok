package com.poptok.android.poptok.service.user;

import android.os.AsyncTask;
import android.util.Log;

import com.poptok.android.poptok.controller.user.JoinActivity;
import com.poptok.android.poptok.model.JSONResult;

/**
 * Created by BIT on 2018-01-25.
 */

public class JoinAsyncTask extends AsyncTask<String, String, JSONResult<Integer>> {

    IUserFinder userFinder;

    JoinActivity joinActivity;


    public JoinAsyncTask(JoinActivity activity, IUserFinder userFinder)
    {
        this.joinActivity = activity;
        this.userFinder = userFinder;
    }


    @Override
    protected JSONResult<Integer> doInBackground(String... info) {
        JSONResult<Integer> jsonResult = userFinder.userJoin(info[0], info[1], info[2]);
        return jsonResult;
    }

    @Override
    protected void onPostExecute(JSONResult<Integer> jsonResult) {
        super.onPostExecute(jsonResult);
        Log.i("onPost", "***********************************************");
        this.joinActivity.joinHandler(jsonResult);
    }
}
