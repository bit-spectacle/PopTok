package com.poptok.android.poptok.model.user;

/**
 * Created by BIT on 2018-01-26.
 */

public class FriendList {


    private int userNo;
//    private int userNo2;
    private String status;
    private String nickname;
    private String profileImage;


    public FriendList(){
    }


    public FriendList( int userNo,  String nickname, String status, String profileImage){
        this.userNo = userNo;
//        this.userNo2 = userNo2;
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
}