package com.poptok.android.poptok.model.auth;

import com.poptok.android.poptok.model.user.User;

import org.androidannotations.annotations.EBean;



@EBean(scope = EBean.Scope.Singleton)
public class AuthStore {

    String sessionID;
    User user;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
