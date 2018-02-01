package com.poptok.android.poptok.service.user;

import android.os.AsyncTask;

import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.service.IAsyncResultHandler;

/**
 * Created by BIT on 2018-02-01.
 */

public class UserLogoutAsyncTask extends AsyncTask<Integer, String, JSONResult<Integer>> {

    IUserFinder iUserFinder;
    IAsyncResultHandler iAsyncResultHandler;

    public UserLogoutAsyncTask(IUserFinder iUserFinder, IAsyncResultHandler iAsyncResultHandler) {
        this.iUserFinder = iUserFinder;
        this.iAsyncResultHandler = iAsyncResultHandler;
    }

    @Override
    protected JSONResult<Integer> doInBackground(Integer... userNo) {
        JSONResult<Integer> jsonResult = iUserFinder.userLogout(userNo[0]);
        return jsonResult;
    }

    @Override
    protected void onPostExecute(JSONResult<Integer> integerJSONResult) {
        super.onPostExecute(integerJSONResult);

        this.iAsyncResultHandler.resultHandler(integerJSONResult);
    }
}
