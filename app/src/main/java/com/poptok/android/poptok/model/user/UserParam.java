package com.poptok.android.poptok.model.user;

/**
 * Created by BIT on 2018-01-15.
 */

public class UserParam {
    public String email;
    public String password;
    public String nickname;
    public int userNo;



    public UserParam(String nickname , int userNo){
        this.nickname = nickname;
        this.userNo = userNo;
    }


    public static UserParam getChangeNickname(String nickname, int userNo)
    {
        UserParam userParam = new UserParam(nickname, userNo);
        return userParam;
    }

    public UserParam(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public UserParam(String nickname){
        this.nickname = nickname;
    }

    public static UserParam getNicknameParam(String nickname)
    {
        UserParam userParam = new UserParam(nickname);
        return userParam;
    }

    public static UserParam getUserLogin(String email, String password){
        UserParam userParam = new UserParam(email, password);
        return userParam;
    }

}