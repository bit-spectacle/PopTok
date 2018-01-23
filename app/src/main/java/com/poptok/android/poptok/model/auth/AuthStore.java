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

    public boolean isLogin() {
        if(userInfo != null && userInfo.getUserNo() > 0) {
            return  true;
        }
        return  false;
    }
}
