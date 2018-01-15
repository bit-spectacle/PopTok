package com.poptok.android.poptok.model.user;

/**
 * Created by BIT on 2018-01-12.
 */

public class User {

    private int userNo;
    private String email;
    private String nickname;
//    private String password;
    private String role;
    private String joindate;
    private String lastLogin;

    public User(){

    }

    public User(int userNo, String email, String nickname,/* String password , */String role, String joindate , String lastLogin){
        this.userNo = userNo;
        this.email = email;
        this.nickname = nickname;
        //this.password = password;
        this.role = role;
        this.joindate = joindate;
        this.lastLogin = lastLogin;
    }


    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}

