package com.poptok.android.poptok.service.friend;

import android.os.AsyncTask;

import com.poptok.android.poptok.controller.friend.FriendListActivity;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.user.FriendList;
import com.poptok.android.poptok.service.IAsyncResultHandler;

import java.util.List;

/**
 * Created by BIT on 2018-01-30.
 */

public class AddMeFriendAsyncTask extends AsyncTask<Integer, String , JSONResult<List<FriendList>>>{

    IFriendFinder friendFinder;

//    AddMeFriendFragment friendListActivity;

    IAsyncResultHandler iAsyncResultHandler;
    FriendListActivity activity;

//    public AddMeFriendAsyncTask(  IAsyncResultHandler iAsyncResultHandler, IFriendFinder friendFinder){
//        this.iAsyncResultHandler = iAsyncResultHandler;
//        this.friendFinder = friendFinder;
////        this.friendListActivity = activity;
//    }

    public AddMeFriendAsyncTask(FriendListActivity activity, IFriendFinder friendFinder){
        this.activity = activity;
        this.friendFinder = friendFinder;
    }

    @Override
    protected JSONResult<List<FriendList>> doInBackground(Integer... userNo) {
        JSONResult<List<FriendList>> jsonResult = friendFinder.addMeFriend(userNo[0]);
        if(jsonResult.getCode().equals("FAIL"))
            return null;
        return jsonResult;
    }

    @Override
    protected  void onPostExecute(JSONResult<List<FriendList>> friendLists){
        super.onPostExecute(friendLists);
//        if(friendLists.getData() == null)

//        friendLists.getData().
        this.activity.resultHandler(friendLists);

       // iAsyncResultHandler.resultHandler(friendLists);
    }
}
