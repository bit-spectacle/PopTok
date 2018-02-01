package com.poptok.android.poptok.model.auth;

import com.poptok.android.poptok.model.user.UserInfo;

import org.androidannotations.annotations.EBean;


@EBean(scope = EBean.Scope.Singleton)
public class AuthStore {

    UserInfo userInfo;


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void setUserStatus(String status){
        this.userInfo.setStatus(status);
    }

    public void setUserImage(String imageUrl){
        this.userInfo.setProfileImage(imageUrl);
    }

    public boolean isLogin() {
        if(userInfo != null && userInfo.getUserNo() > 0) {
            return  true;
        }
        return  false;
    }

    public void clear() {
        this.userInfo = new UserInfo();
    }
}
