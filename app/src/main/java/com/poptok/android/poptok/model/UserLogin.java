package com.poptok.android.poptok.model;

/**
 * Created by BIT on 2018-01-15.
 */

public class UserLogin {
    public String email;
    public String password;

    public UserLogin(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public static UserLogin getUserLogin(String email, String password){

        UserLogin userLogin = new UserLogin(email, password);


        return userLogin;
    }

}
