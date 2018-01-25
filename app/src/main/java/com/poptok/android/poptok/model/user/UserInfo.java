package com.poptok.android.poptok.model.user;

/**
 * Created by BIT on 2018-01-22.
 */

public class UserInfo {
    private int userNo;
    private String email;
    private String nickname;
    private String status;
    private String profileImage;
    private String role;
    private String joindate;
    private String lastlogin;

    public UserInfo() {
    }




    public UserInfo(int userNo, String email, String nickname, String status , String profileImage, String role, String joindate, String lastlogin) {
        this.userNo = userNo;
        this.email = email;
        this.nickname = nickname;
        this.status = status;
        this.profileImage = profileImage;
        this.role = role;
        this.joindate = joindate;
        this.lastlogin = lastlogin;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

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

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }
}
