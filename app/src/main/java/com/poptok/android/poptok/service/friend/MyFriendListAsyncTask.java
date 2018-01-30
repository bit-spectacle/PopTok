package com.poptok.android.poptok.service.friend;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.friend.FriendListActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.user.FriendList;
import com.poptok.android.poptok.service.IAsyncResultHandler;

import java.util.List;

/**
 * Created by BIT on 2018-01-29.
 */

public class MyFriendListAsyncTask extends AsyncTask<Integer, String, JSONResult<List<FriendList>>>  {

    IFriendFinder friendFinder;
    IAsyncResultHandler iAsyncResultHandler;
    FriendListActivity friendListActivity;


    public MyFriendListAsyncTask(FriendListActivity activity, IFriendFinder friendFinder , IAsyncResultHandler iAsyncResultHandler){
        this.friendListActivity = activity;
        this.friendFinder = friendFinder;
        this.iAsyncResultHandler = iAsyncResultHandler;
    }

    @Override
    protected JSONResult<List<FriendList>>  doInBackground(Integer... userNo) {
        JSONResult<List<FriendList>> result = friendFinder.getFriendProfile(userNo[0]);
        return result;
    }
    @Override
    protected void onPostExecute(JSONResult<List<FriendList>> result) {
        super.onPostExecute(result);
        iAsyncResultHandler.resultHandler(result);
    }

}
