package com.poptok.android.poptok.model.auth;

import com.poptok.android.poptok.model.user.FriendList;

import org.androidannotations.annotations.EBean;

/**
 * Created by BIT on 2018-01-26.
 */


@EBean(scope = EBean.Scope.Singleton)
public class FriendStore {

    FriendList friendList;

    public FriendList getFriendList(){
        return friendList;
    }

    public void setFriendList(FriendList friendList){
        this.friendList = friendList;
    }

    public void setFriendStatus(String status) {
        this.friendList.setStatus(status);
    }
}
