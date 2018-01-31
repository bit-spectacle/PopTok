package com.poptok.android.poptok.model.user;

/**
 * Created by BIT on 2018-01-26.
 */

public class FriendList {


    private int userNo;
    private String status;
    private String nickname;
    private String profileImage;
    private int userStatus;


    public FriendList(){
    }


    public FriendList( int userNo,  String nickname, String status, String profileImage, int userStatus){
        this.userNo = userNo;
        this.nickname = nickname;
        this.status = status;
        this.profileImage = profileImage;
        this.userStatus = userStatus;
    }
    public FriendList(int userNo, String nickname, String status, String profileImage){
        this.userNo = userNo;
        this.nickname = nickname;
        this.status = status;
        this.profileImage = profileImage;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
}