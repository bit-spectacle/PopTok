package com.poptok.android.poptok.service.user;

import android.os.AsyncTask;
import android.util.Log;

import com.poptok.android.poptok.controller.user.ChangeNickNameActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;
import com.poptok.android.poptok.model.user.UserInfo;

/**
 * Created by BIT on 2018-01-23.
 */

public class ChangeNicknameAsyncTask extends AsyncTask<String, String, JSONResult<UserInfo>> {

    IUserFinder userFinder;

    ChangeNickNameActivity nickNameActivity;

    AuthStore authStore;

    public ChangeNicknameAsyncTask(AuthStore authStore, ChangeNickNameActivity activity, IUserFinder userFinder) {
        this.authStore = authStore;
        this.nickNameActivity = activity;
        this.userFinder = userFinder;
    }

    @Override
    protected JSONResult<UserInfo> doInBackground(String... nicknames) {
        JSONResult<UserInfo> jsonResult = userFinder.changeNickname(nicknames[0], authStore.getUserInfo().getUserNo());
        Log.i("doInBackground", "*******************!!!!!!!!!!!!!!!!!!!!!");
        return  jsonResult;
    }

    @Override
    protected void onPostExecute(JSONResult<UserInfo> jsonResult) {
        super.onPostExecute(jsonResult);
        Log.i("onPost", "***********************************************");
        this.nickNameActivity.nicknameChangeHandler(jsonResult);
    }

}
