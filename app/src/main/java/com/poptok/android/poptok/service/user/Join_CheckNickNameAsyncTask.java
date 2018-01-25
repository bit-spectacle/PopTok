package com.poptok.android.poptok.service.user;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.user.JoinActivity;
import com.poptok.android.poptok.model.JSONResult;

/**
 * Created by BIT on 2018-01-25.
 */

public class Join_CheckNickNameAsyncTask extends AsyncTask<String, String, JSONResult<Integer>> {

    IUserFinder userFinder;

    JoinActivity joinActivity;

    public Join_CheckNickNameAsyncTask(JoinActivity activity, IUserFinder userFinder){
        this.joinActivity = activity;
        this.userFinder = userFinder;

    } @Override
    protected JSONResult<Integer> doInBackground(String... nicknames) {
        JSONResult<Integer> jsonResult = userFinder.checkNickname(nicknames[0]);
        return jsonResult;

    }

    @Override
    protected void onPostExecute(JSONResult<Integer> jsonResult) {
        super.onPostExecute(jsonResult);
        this.joinActivity.nicknameCheckHandler(jsonResult);
    }
}
