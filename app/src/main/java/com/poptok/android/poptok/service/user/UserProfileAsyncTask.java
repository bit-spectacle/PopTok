package com.poptok.android.poptok.service.user;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.user.ProfileActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.user.UserInfo;

/**
 * Created by BIT on 2018-01-22.
 */

public class UserProfileAsyncTask extends AsyncTask<String, String, JSONResult<UserInfo>> {

    IUserFinder userFinder;

    ProfileActivity profileActivity;

    public UserProfileAsyncTask(ProfileActivity activity, IUserFinder userFinder) {
        this.profileActivity = activity;
        this.userFinder = userFinder;
    }

    @Override
    protected JSONResult<UserInfo> doInBackground(String... emails) {
        JSONResult<UserInfo> jsonResult = userFinder.findUser(emails[0]);
        return  jsonResult;
    }

    @Override
    protected void onPostExecute(JSONResult<UserInfo> jsonResult) {
        super.onPostExecute(jsonResult);
        this.profileActivity.setView(jsonResult);
    }
}
