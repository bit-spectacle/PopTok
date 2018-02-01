package com.poptok.android.poptok.service.user;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.AppBaseActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.service.IAsyncResultHandler;

public class UserLogoutAsyncTask extends AsyncTask<Integer, String, JSONResult<Integer>> {

    IUserFinder iUserFinder;
    AppBaseActivity appBaseActivity;

    public UserLogoutAsyncTask(IUserFinder iUserFinder, AppBaseActivity appBaseActivity) {
        this.iUserFinder = iUserFinder;
        this.appBaseActivity = appBaseActivity;
    }

    @Override
    protected JSONResult<Integer> doInBackground(Integer... userNo) {
        JSONResult<Integer> jsonResult = iUserFinder.userLogout(userNo[0]);
        return jsonResult;
    }

    @Override
    protected void onPostExecute(JSONResult<Integer> integerJSONResult) {
        super.onPostExecute(integerJSONResult);
        this.appBaseActivity.logoutResultHandler(integerJSONResult);
    }
}
