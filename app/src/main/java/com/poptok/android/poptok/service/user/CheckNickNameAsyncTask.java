package com.poptok.android.poptok.service.user;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.user.ChangeNickNameActivity;
import com.poptok.android.poptok.model.JSONResult;

/**
 * Created by BIT on 2018-01-24.
 */

public class CheckNickNameAsyncTask extends AsyncTask<String, String, JSONResult<Integer>> {

    IUserFinder userFinder;

    ChangeNickNameActivity changeNickNameActivity;

    public CheckNickNameAsyncTask(ChangeNickNameActivity activity, IUserFinder userFinder){
        this.changeNickNameActivity = activity;
        this.userFinder = userFinder;
    }



    @Override
    protected JSONResult<Integer> doInBackground(String... nicknames) {
        JSONResult<Integer> jsonResult = userFinder.checkNickname(nicknames[0]);
        return jsonResult;

    }

    @Override
    protected void onPostExecute(JSONResult<Integer> jsonResult) {
        super.onPostExecute(jsonResult);
        this.changeNickNameActivity.nicknameCheckHandler(jsonResult);
    }
}
