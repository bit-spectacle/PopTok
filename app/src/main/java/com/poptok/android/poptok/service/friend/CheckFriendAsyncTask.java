package com.poptok.android.poptok.service.friend;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.friend.FriendListActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.service.IAsyncResultHandler;

import java.util.List;

/**
 * Created by BIT on 2018-01-26.
 */

//친구 신청이 가능한지 확인하는 곳 --> 이미 거절한 친구신청이라면 다시 신청할 수 없음.
public class CheckFriendAsyncTask extends AsyncTask<Integer, String, JSONResult<Integer>> {

    IFriendFinder friendFinder;

    //FriendList Activity 만들기
    FriendListActivity friendListActivity;

    IAsyncResultHandler<List<Integer>> iAsyncResultHandler;


    public CheckFriendAsyncTask(FriendListActivity activity, IFriendFinder friendFinder){
        this.friendListActivity = activity;
        this.friendFinder = friendFinder;
    }



    @Override
    protected JSONResult<Integer> doInBackground(Integer... userNo) {
        JSONResult<Integer> jsonResult = friendFinder.checkFriend(userNo[0], userNo[1]);
        return jsonResult;

    }

    @Override
    protected void onPostExecute(JSONResult<Integer> jsonResult) {
        super.onPostExecute(jsonResult);
        this.friendListActivity.checkFriendHandler(jsonResult);
//        iAsyncResultHandler.resultHandler(jsonResult);
    }
}
