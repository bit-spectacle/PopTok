package com.poptok.android.poptok.service.friend;

import android.os.AsyncTask;
import android.view.View;

import com.poptok.android.poptok.controller.friend.FriendListActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.auth.AuthStore;

/**
 * Created by BIT on 2018-01-26.
 */

public class RejectFriendAsyncTask extends AsyncTask<Integer, String, JSONResult<Integer>> {
    IFriendFinder friendFinder;

    //FriendList Activity 만들기
    FriendListActivity friendListActivity;
    AuthStore authStore;
    View v;



    public RejectFriendAsyncTask(FriendListActivity activity, IFriendFinder friendFinder){
        this.friendListActivity = activity;
        this.friendFinder = friendFinder;
    }

    public RejectFriendAsyncTask(AuthStore authStore, View v, IFriendFinder friendFinder){
        this.authStore = authStore;
        this.v = v;
        this.friendFinder = friendFinder;
    }



    @Override
    protected JSONResult<Integer> doInBackground(Integer... userNo) {
        JSONResult<Integer> jsonResult = friendFinder.RejectFriend(userNo[0], userNo[1]);
        return jsonResult;

    }

    @Override
    protected void onPostExecute(JSONResult<Integer> jsonResult) {
        super.onPostExecute(jsonResult);
        this.friendListActivity.rejectFriendHandler(jsonResult);
    }
}