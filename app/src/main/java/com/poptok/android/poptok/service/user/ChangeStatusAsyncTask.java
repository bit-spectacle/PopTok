package com.poptok.android.poptok.service.user;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.user.ChangeStatusActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.user.UserInfo;

/**
 * Created by BIT on 2018-01-25.
 */

public class ChangeStatusAsyncTask extends AsyncTask<String, String, JSONResult<UserInfo>> {

    IUserFinder userFinder;

    ChangeStatusActivity statusActivity;

    AuthStore authStore;

    public ChangeStatusAsyncTask(AuthStore authStore, ChangeStatusActivity activity, IUserFinder userFinder){
        this.authStore = authStore;
        this.statusActivity = activity;
        this.userFinder = userFinder;
    }

    @Override
    protected JSONResult<UserInfo> doInBackground(String... statuses) {
        JSONResult<UserInfo> jsonResult = userFinder.changeStatus(statuses[0], authStore.getUserInfo().getUserNo());
            return jsonResult;
    }

    @Override
    protected void onPostExecute(JSONResult<UserInfo> jsonResult) {
        super.onPostExecute(jsonResult);
        this.statusActivity.statusChangeHandler(jsonResult);
    }
}
