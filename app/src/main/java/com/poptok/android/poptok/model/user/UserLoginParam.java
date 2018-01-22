package com.poptok.android.poptok.model.user;

/**
 * Created by BIT on 2018-01-15.
 */

public class UserLoginParam {
    public String email;
    public String password;

    public UserLoginParam(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public static UserLoginParam getUserLogin(String email, String password){
        UserLoginParam userLoginParam = new UserLoginParam(email, password);
        return userLoginParam;
    }

}
