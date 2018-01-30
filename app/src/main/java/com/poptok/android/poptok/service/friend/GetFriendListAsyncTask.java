package com.poptok.android.poptok.service.friend;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.friend.FriendListActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.user.FriendList;
import com.poptok.android.poptok.service.IAsyncResultHandler;

import java.util.List;

/**
 * Created by BIT on 2018-01-26.
 */

public class GetFriendListAsyncTask extends AsyncTask<Integer, String, JSONResult<List<FriendList>>> {

    IFriendFinder friendFinder;

    IAsyncResultHandler iAsyncResultHandler;
    //FriendList Activity 만들기
    FriendListActivity friendListActivity;

    public GetFriendListAsyncTask(FriendListActivity activity , IFriendFinder friendFinder){
        this.friendListActivity = activity;
        this.friendFinder = friendFinder;
    }

    @Override
    protected JSONResult<List<FriendList>> doInBackground(Integer... userNo) {
        JSONResult<List<FriendList>> result = friendFinder.findFriend(userNo[0]);
        return result;

    }

    @Override
    protected void onPostExecute(JSONResult<List<FriendList>> result) {
        super.onPostExecute(result);
        //this.friendListActivity.getFriendHandler(result);
//        iAsyncResultHandler.resultHandler(result);
        this.friendListActivity.resultHandler(result);
    }
}
