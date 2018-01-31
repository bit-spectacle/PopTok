package com.poptok.android.poptok.service.friend;

import android.os.AsyncTask;

import com.poptok.android.poptok.service.IAsyncResultHandler;
import com.poptok.android.poptok.view.friend.FriendItemView;

/**
 * Created by BIT on 2018-01-31.
 */

public class GetFriendNoAsyncTask extends AsyncTask<FriendItemView, String, Integer> {

    FriendItemView friendItemView;

    IAsyncResultHandler iAsyncResultHandler;

    public GetFriendNoAsyncTask(FriendItemView friendItemView){
        this.friendItemView = friendItemView;
    }


    @Override
    protected Integer doInBackground(FriendItemView... friendItemViews) {
//        friendItemView.get
        return null;
    }

    @Override
    protected  void onPostExecute(Integer friendNo){
        super.onPostExecute(friendNo);
//        if(friendLists.getData() == null)

//        friendLists.getData().
//        this.activity.resultHandler(friendLists);

        // iAsyncResultHandler.resultHandler(friendLists);
    }
}
