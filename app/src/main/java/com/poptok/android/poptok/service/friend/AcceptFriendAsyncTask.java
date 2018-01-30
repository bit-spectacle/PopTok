package com.poptok.android.poptok.service.friend;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.friend.FriendListActivity;
import com.poptok.android.poptok.model.JSONResult;

/**
 * created by bit on 2018-01-27.
 */

public class AcceptFriendAsyncTask extends AsyncTask<Integer, String, JSONResult<Integer>> {
    IFriendFinder friendFinder;

    //FriendList Activity 만들기
    FriendListActivity friendListActivity;


    public AcceptFriendAsyncTask(FriendListActivity activity, IFriendFinder friendFinder){
        this.friendListActivity = activity;
        this.friendFinder = friendFinder;
    }



    @Override
    protected JSONResult<Integer> doInBackground(Integer... userNo) {
        JSONResult<Integer> jsonResult = friendFinder.AcceptFriend(userNo[0], userNo[1]);
        return jsonResult;

    }

    @Override
    protected void onPostExecute(JSONResult<Integer> jsonResult) {
        super.onPostExecute(jsonResult);
        this.friendListActivity.acceptFriendHandler(jsonResult);
    }
}